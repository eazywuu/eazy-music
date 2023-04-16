package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import xyz.eazywu.music.object.dto.UserDto;
import xyz.eazywu.music.object.entity.User;
import xyz.eazywu.music.object.request.UserCreateReq;
import xyz.eazywu.music.object.request.UserUpdateReq;
import xyz.eazywu.music.object.vo.UserVo;

/**
 * vo，dto，entity转换
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    User createEntity(UserCreateReq userCreateReq);

    User updateEntity(@MappingTarget User user, UserUpdateReq userUpdateReq);

    UserVo toVo(UserDto userDto);

    UserDto toDto(User user);
}
