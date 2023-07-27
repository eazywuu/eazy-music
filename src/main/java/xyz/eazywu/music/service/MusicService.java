package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.entity.Music;
import xyz.eazywu.music.object.filter.MusicSearchFilter;

public interface MusicService extends GeneralService<Music, MusicDto> {

    Page<MusicDto> search(MusicSearchFilter filter);

    void publish(String id);

    void close(String id);
}
