package com.wyz.music_springboot.controller;

import com.wyz.music_springboot.dto.UserCreateDto;
import com.wyz.music_springboot.mapper.UserMapper;
import com.wyz.music_springboot.service.UserService;
import com.wyz.music_springboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    UserMapper userMapper;

    @GetMapping("/list")
    public List<UserVo> list() {
        return userService.list()
                .stream().map(userMapper::toVo).collect(Collectors.toList());
    }

    @PostMapping("/")
    UserVo create(@RequestBody UserCreateDto userCreateDto) {
        return userMapper.toVo(userService.create(userCreateDto));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
