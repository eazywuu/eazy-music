package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.dto.MusicCreateRequestDto;
import xyz.eazywu.music.dto.MusicDto;
import xyz.eazywu.music.dto.MusicUpdateRequestDto;
import xyz.eazywu.music.entity.Music;
import xyz.eazywu.music.vo.MusicVo;

@Mapper(componentModel = "spring")
public interface MusicMapper {
    MusicVo toVo(MusicDto musicDto);

    MusicDto toDto(Music music);

    Music createEntity(MusicCreateRequestDto musicCreateRequestDto);

    Music updateEntity(@MappingTarget Music music,  MusicUpdateRequestDto musicUpdateRequestDto);

}
