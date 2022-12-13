package xyz.eazywu.music.mapper;

import xyz.eazywu.music.dto.UserCreateRequest;
import xyz.eazywu.music.dto.UserDto;
import xyz.eazywu.music.dto.UserUpdateRequest;
import xyz.eazywu.music.entity.User;
import xyz.eazywu.music.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * vo，dto，entity转换
 */
@Mapper(componentModel = "spring")
//@Component
public interface UserMapper {
    UserVo toVo(UserDto userDto);

    UserDto toDto(User user);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
