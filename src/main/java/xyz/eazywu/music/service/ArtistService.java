package xyz.eazywu.music.service;

import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;

import java.util.List;

public interface ArtistService {

    List<ArtistDto> list();

    ArtistDto create(ArtistCreateReq artistCreateReq);

    ArtistDto update(String id, ArtistUpdateReq artistUpdateReq);
}
