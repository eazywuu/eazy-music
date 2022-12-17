package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @PostMapping
    @ApiOperation("create")
    UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    @ApiOperation("get")
    @GetMapping("/{id}")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("update")
    UserVo update(@PathVariable String id,
                  @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userMapper.toVo(userService.update(id, userUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/me")
    @ApiOperation("me")
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
