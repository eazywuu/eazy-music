package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.entity.Music;
import xyz.eazywu.music.object.enums.MusicStatusType;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.request.MusicUpdateReq;
import xyz.eazywu.music.repository.MusicRepository;
import xyz.eazywu.music.service.MusicService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;

    private final MusicMapper musicMapper;

    @Override
    public Page<MusicDto> search(Pageable pageable) {
        return musicRepository.findAll(pageable).map(musicMapper::toDto);
    }

    @Override
    public MusicDto create(MusicCreateReq musicCreateReq) {
        Music music = musicMapper.createEntity(musicCreateReq);
        music.setStatus(MusicStatusType.DRAFT);
        return musicMapper.toDto(musicRepository.save(music));
    }

    @Override
    public MusicDto update(String id, MusicUpdateReq musicUpdateReq) {
        Music music = checkMusicExist(id);
        return musicMapper.toDto(musicRepository.save(musicMapper.updateEntity(music, musicUpdateReq)));
    }

    @Override
    public void publish(String id) {
        Music music = checkMusicExist(id);
        music.setStatus(MusicStatusType.PUBLISHED);
        musicRepository.save(music);
    }

    @Override
    public void close(String id) {
        Music music = checkMusicExist(id);
        music.setStatus(MusicStatusType.CLOSED);
        musicRepository.save(music);
    }

    private Music checkMusicExist(String id) {
        Optional<Music> music = musicRepository.findById(id);
        if (!music.isPresent()) {
            throw new BizException(ResultType.MUSIC_NOT_FOUND);
        }
        return music.get();
    }
}
