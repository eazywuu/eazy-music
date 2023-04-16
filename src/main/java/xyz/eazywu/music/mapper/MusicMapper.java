package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.entity.Music;
import xyz.eazywu.music.object.request.MusicCreateReq;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.request.MusicUpdateReq;
import xyz.eazywu.music.object.vo.MusicVo;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface MusicMapper extends MapperInterface<Music, MusicDto>{
    MusicVo toVo(MusicDto musicDto);

    Music createEntity(MusicCreateReq musicCreateReq);

    Music updateEntity(@MappingTarget Music music, MusicUpdateReq musicUpdateReq);

    MusicDto toDto(MusicCreateReq musicCreateReq);

    MusicDto toDto(MusicUpdateReq musicUpdateReq);
}
