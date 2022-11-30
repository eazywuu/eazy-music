package com.wyz.music_springboot.service;

import com.wyz.music_springboot.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> list();
}
