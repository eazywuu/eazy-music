package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ResultType;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.enums.ArtistStatusType;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.repository.ArtistRepository;
import xyz.eazywu.music.service.ArtistService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtistServiceImpl extends BaseService implements ArtistService {

    private final ArtistMapper artistMapper;

    private final ArtistRepository artistRepository;

    @Override
    public List<ArtistDto> list() {
        return artistRepository.findAll().stream().map(artistMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ArtistDto create( ArtistCreateReq artistCreateReq) {
        Artist artist = artistMapper.createEntity(artistCreateReq);
        artist.setStatus(ArtistStatusType.PUBLISHED);
        artist.setCreatedBy(getCurrentUserEntity());
        artist.setUpdatedBy(getCurrentUserEntity());
        return artistMapper.toDto(artist);
    }

    @Override
    public ArtistDto update(String id, ArtistUpdateReq artistUpdateReq) {
        return artistMapper.toDto(artistRepository.save(artistMapper.updateEntity(checkUserExist(id), artistUpdateReq)));
    }

    private Artist checkUserExist(String id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (!artist.isPresent()) {
            throw new BizException(ResultType.ARTIST_NOT_FOUND);
        }
        return artist.get();
    }
}
