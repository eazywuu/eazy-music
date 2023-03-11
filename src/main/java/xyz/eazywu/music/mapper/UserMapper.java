package xyz.eazywu.music.mapper;

import xyz.eazywu.music.object.request.UserCreateRequestDto;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.request.UserUpdateRequestDto;
import xyz.eazywu.music.object.entity.UserEntity;
import xyz.eazywu.music.object.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * vo，dto，entity转换
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserVo toVo(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    UserEntity createEntity(UserCreateRequestDto userCreateRequestDto);

    UserEntity updateEntity(@MappingTarget UserEntity userEntity, UserUpdateRequestDto userUpdateRequestDto);
}
