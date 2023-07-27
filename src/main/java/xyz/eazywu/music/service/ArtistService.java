package xyz.eazywu.music.service;

import org.springframework.data.domain.Page;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.filter.ArtistSearchFilter;

public interface ArtistService extends GeneralService<Artist, ArtistDto> {

    Page<ArtistDto> search(ArtistSearchFilter filter);
}
