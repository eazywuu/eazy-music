package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.request.MusicCreateRequestDto;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.request.MusicUpdateRequestDto;
import xyz.eazywu.music.object.entity.MusicEntity;
import xyz.eazywu.music.object.vo.MusicVo;

@Mapper(componentModel = "spring")
public interface MusicMapper {
    MusicVo toVo(MusicDto musicDto);

    MusicDto toDto(MusicEntity musicEntity);

    MusicEntity createEntity(MusicCreateRequestDto musicCreateRequestDto);

    MusicEntity updateEntity(@MappingTarget MusicEntity musicEntity, MusicUpdateRequestDto musicUpdateRequestDto);

}
