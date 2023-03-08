package xyz.eazywu.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.dto.MusicCreateRequestDto;
import xyz.eazywu.music.dto.MusicDto;
import xyz.eazywu.music.dto.MusicUpdateRequestDto;
import xyz.eazywu.music.entity.Music;
import xyz.eazywu.music.enums.MusicStatus;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ExceptionType;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.repository.MusicRepository;
import xyz.eazywu.music.service.MusicService;

import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {

    private MusicRepository musicRepository;

    private MusicMapper musicMapper;

    @Override
    public MusicDto create(MusicCreateRequestDto musicCreateRequestDto) {
        Music music = musicMapper.createEntity(musicCreateRequestDto);
        music.setStatus(MusicStatus.DRAFT);
        return musicMapper.toDto(musicRepository.save(music));
    }

    @Override
    public MusicDto update(String id, MusicUpdateRequestDto musicUpdateRequestDto) {
        Music music = checkMusicExist(id);
        return musicMapper.toDto(musicRepository.save(musicMapper.updateEntity(music, musicUpdateRequestDto)));
    }

    @Override
    public Page<MusicDto> search(Pageable pageable) {
        return musicRepository.findAll(pageable).map(musicMapper::toDto);
    }

    @Override
    public void publish(String id) {
        Music music = checkMusicExist(id);
        music.setStatus(MusicStatus.PUBLISHED);
        musicRepository.save(music);
    }

    @Override
    public void close(String id) {
        Music music = checkMusicExist(id);
        music.setStatus(MusicStatus.CLOSED);
        musicRepository.save(music);
    }

    private Music checkMusicExist(String id) {
        Optional<Music> music = musicRepository.findById(id);
        if (!music.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        return music.get();
    }
    @Autowired
    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }
}
