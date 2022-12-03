package com.wyz.music_springboot.service;

import com.wyz.music_springboot.dto.UserCreateDto;
import com.wyz.music_springboot.dto.UserDto;

import com.wyz.music_springboot.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    User loadUserByUsername(String username);
}
