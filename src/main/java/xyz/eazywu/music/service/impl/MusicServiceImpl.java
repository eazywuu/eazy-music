package xyz.eazywu.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.object.request.MusicCreateRequestDto;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.request.MusicUpdateRequestDto;
import xyz.eazywu.music.object.entity.MusicEntity;
import xyz.eazywu.music.enums.MusicStatusEnum;
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
        MusicEntity musicEntity = musicMapper.createEntity(musicCreateRequestDto);
        musicEntity.setStatus(MusicStatusEnum.DRAFT);
        return musicMapper.toDto(musicRepository.save(musicEntity));
    }

    @Override
    public MusicDto update(String id, MusicUpdateRequestDto musicUpdateRequestDto) {
        MusicEntity musicEntity = checkMusicExist(id);
        return musicMapper.toDto(musicRepository.save(musicMapper.updateEntity(musicEntity, musicUpdateRequestDto)));
    }

    @Override
    public Page<MusicDto> search(Pageable pageable) {
        return musicRepository.findAll(pageable).map(musicMapper::toDto);
    }

    @Override
    public void publish(String id) {
        MusicEntity musicEntity = checkMusicExist(id);
        musicEntity.setStatus(MusicStatusEnum.PUBLISHED);
        musicRepository.save(musicEntity);
    }

    @Override
    public void close(String id) {
        MusicEntity musicEntity = checkMusicExist(id);
        musicEntity.setStatus(MusicStatusEnum.CLOSED);
        musicRepository.save(musicEntity);
    }

    private MusicEntity checkMusicExist(String id) {
        Optional<MusicEntity> music = musicRepository.findById(id);
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
