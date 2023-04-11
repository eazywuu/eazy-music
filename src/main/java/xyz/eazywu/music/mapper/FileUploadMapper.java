package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import xyz.eazywu.music.object.dto.FileUploadDto;
import xyz.eazywu.music.object.vo.FileUploadVo;

@Mapper(componentModel = "spring")
public interface FileUploadMapper {
    FileUploadVo toVo(FileUploadDto fileUploadDto);
}
