package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.dto.UserCreateRequest;
import xyz.eazywu.music.dto.UserUpdateRequest;
import xyz.eazywu.music.mapper.UserMapper;
import xyz.eazywu.music.service.UserService;
import xyz.eazywu.music.vo.UserVo;

@RestController
@RequestMapping("/users")
@Api(tags = "user管理接口")
public class UserController {

    UserService userService;
    UserMapper userMapper;

    @GetMapping
    @ApiOperation("用户检索")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @ApiOperation("create")
    @PostMapping
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    @GetMapping("/{id}")
    @ApiOperation("get")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @ApiOperation("update")
    @PutMapping("/{id}")
    UserVo update(@PathVariable String id,
                  @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @ApiOperation("delete")
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @ApiOperation("me")
    @GetMapping("/me")
    UserVo me() {
        return userMapper.toVo(userService.getCurrentUser());
    }

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
