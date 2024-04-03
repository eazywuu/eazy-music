package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.entity.Artist;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.ArtistUpdateReq;
import xyz.eazywu.music.object.vo.ArtistVo;

@Mapper(componentModel = "spring", uses = {FileMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArtistMapper extends MapperInterface<Artist, ArtistDto> {
    ArtistVo toVo(ArtistDto artistDto);

    @Mapping(source = "photoId", target = "photo.id")
    ArtistDto toDto(ArtistCreateReq artistCreateReq);

    @Mapping(source = "photoId", target = "photo.id")
    ArtistDto toDto(ArtistUpdateReq artistUpdateReq);
}
