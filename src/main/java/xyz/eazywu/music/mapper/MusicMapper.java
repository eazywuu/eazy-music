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

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {FileMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MusicMapper extends MapperInterface<Music, MusicDto>{

    /**
     * 将req中的fileId映射到dto中file字段的id，转换时，在dto中通过fileId创建file字段
     */
    @Mapping(source = "fileId", target = "file.id")
    @Mapping(source = "artistIds", target = "artistList")
    MusicDto toDto(MusicCreateReq musicCreateReq);

    @Mapping(source = "fileId", target = "file.id")
    @Mapping(source = "artistIds", target = "artistList")
    MusicDto toDto(MusicUpdateReq musicUpdateReq);

    MusicVo toVo(MusicDto musicDto);

    MusicDto toDto(Music music);

    default List<ArtistDto> artistListMapping(List<String> artistIds) {
        List<ArtistDto> artistList = new ArrayList<>();
        artistIds.forEach((id) -> {
            ArtistDto artistDto = new ArtistDto();
            artistDto.setId(id);
            artistList.add(artistDto);
        });
        return artistList;
    }
}
