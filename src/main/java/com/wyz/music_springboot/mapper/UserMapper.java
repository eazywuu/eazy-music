package com.wyz.music_springboot.mapper;

import com.wyz.music_springboot.dto.UserCreateRequest;
import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.dto.UserUpdateRequest;
import com.wyz.music_springboot.entity.User;
import com.wyz.music_springboot.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
//@Component
public interface UserMapper {
    UserVo toVo(UserDto userDto);

    UserDto toDto(User user);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
