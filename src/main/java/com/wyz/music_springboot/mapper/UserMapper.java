package com.wyz.music_springboot.mapper;

import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.entity.User;
import com.wyz.music_springboot.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);
    UserVo toVo(UserDto userDto);
}
