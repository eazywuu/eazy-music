package com.wyz.music_springboot.service;

import com.wyz.music_springboot.dto.UserCreateRequest;
import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.dto.UserUpdateRequest;
import com.wyz.music_springboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateRequest userCreateRequest);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    Page<UserDto> search(Pageable pageable);

    User loadUserByUsername(String username);
}
