package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.eazywu.music.dto.MusicCreateRequestDto;
import xyz.eazywu.music.dto.MusicDto;
import xyz.eazywu.music.dto.MusicUpdateRequestDto;

public interface MusicService {
    MusicDto create(MusicCreateRequestDto musicCreateRequestDto);

    MusicDto update(String id, MusicUpdateRequestDto musicUpdateRequestDto);

    Page<MusicDto> search(Pageable pageable);

    void publish(String id);

    void close(String id);
}
