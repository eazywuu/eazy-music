package xyz.eazywu.music.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.entity.File;
import xyz.eazywu.music.object.request.FileUploadReq;
import xyz.eazywu.music.object.vo.FileVo;

@Mapper(componentModel = "spring")
@DecoratedWith(FileMapperDecorator.class)
public interface FileMapper {
    FileVo toVo(FileDto fileDto);

    FileDto toDto(File file);

    File createEntity(FileUploadReq fileUploadReq);
}
