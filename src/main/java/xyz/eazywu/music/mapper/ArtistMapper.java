package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.object.vo.ArtistVo;

@Mapper(componentModel = "spring", uses= {FileMapper.class, MusicMapper.class })
public interface ArtistMapper {

    Artist createEntity(ArtistCreateReq artistCreateReq);

    Artist updateEntity(@MappingTarget Artist artist,  ArtistUpdateReq artistUpdateReq);

    ArtistDto toDto(Artist artist);

    ArtistVo toVo(ArtistDto artistDto);
}
