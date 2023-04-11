package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.request.MusicUpdateReq;

public interface MusicService {
    MusicDto create(MusicCreateReq musicCreateReq);

    MusicDto update(String id, MusicUpdateReq musicUpdateReq);

    Page<MusicDto> search(Pageable pageable);

    void publish(String id);

    void close(String id);
}
