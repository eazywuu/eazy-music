package com.wyz.music_springboot.service.impl;

import com.wyz.music_springboot.dto.UserCreateRequest;
import com.wyz.music_springboot.dto.UserDto;
import com.wyz.music_springboot.dto.UserUpdateRequest;
import com.wyz.music_springboot.entity.User;
import com.wyz.music_springboot.exception.BizException;
import com.wyz.music_springboot.exception.ExceptionType;
import com.wyz.music_springboot.mapper.UserMapper;
import com.wyz.music_springboot.repository.UserRepository;
import com.wyz.music_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;
    // 密码加密器
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUsername(userCreateRequest.getUsername());
        User user = userMapper.createEntity(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }


    @Override
    public UserDto get(String id) {
        User user = checkUserExist(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        User user = checkUserExist(id);
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(user, userUpdateRequest)));
    }

    @Override
    public void delete(String id) {
        User user = checkUserExist(id);
        userRepository.delete(user);
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
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

    private User checkUserExist(String id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
