package com.wyz.music_springboot.service.impl;

import com.wyz.music_springboot.dto.UserCreateDto;
import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.entity.User;
import com.wyz.music_springboot.exception.BizException;
import com.wyz.music_springboot.exception.ExceptionType;
import com.wyz.music_springboot.mapper.UserMapper;
import com.wyz.music_springboot.repository.UserRepository;
import com.wyz.music_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;
    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUsername(userCreateDto.getUsername());
        User user = userMapper.createEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
