package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.entity.Music;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.request.MusicUpdateReq;
import xyz.eazywu.music.object.vo.MusicVo;

@Mapper(componentModel = "spring", uses = {FileMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MusicMapper extends MapperInterface<Music, MusicDto>{
    MusicVo toVo(MusicDto musicDto);

    @Mapping(source = "fileId", target = "file.id")
    MusicDto toDto(MusicCreateReq musicCreateReq);

    MusicDto toDto(Music music);

    MusicDto toDto(MusicUpdateReq musicUpdateReq);

    ArtistDto convert(String id);

    String convert(ArtistDto artistDto);
}
