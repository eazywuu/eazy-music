package com.wyz.music_springboot.service.impl;

import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.mapper.UserMapper;
import com.wyz.music_springboot.repository.UserRepository;
import com.wyz.music_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
