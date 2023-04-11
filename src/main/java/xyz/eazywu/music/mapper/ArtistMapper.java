package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.object.vo.ArtistVo;

@Mapper(componentModel = "spring", uses = {FileMapper.class, MusicMapper.class})
public interface ArtistMapper {
    @Mapping(target = "photo.id", source = "photoId")
    Artist createEntity(ArtistCreateReq artistCreateReq);

    @Mapping(target = "photo.id", source = "photoId")
    Artist updateEntity(@MappingTarget Artist artist,  ArtistUpdateReq artistUpdateReq);

    ArtistDto toDto(Artist artist);

    ArtistVo toVo(ArtistDto artistDto);
}
