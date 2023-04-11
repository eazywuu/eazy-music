package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.eazywu.music.mapper.UserMapper;
import xyz.eazywu.music.object.request.UserCreateReq;
import xyz.eazywu.music.object.request.UserUpdateReq;
import xyz.eazywu.music.object.vo.UserVo;
import xyz.eazywu.music.service.UserService;

@Api(tags = "user管理接口")
@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ApiOperation("用户检索")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable).map(userMapper::toVo);
    }

    @ApiOperation("get")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

    @PostMapping
    @ApiOperation("create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo create(@Validated @RequestBody UserCreateReq userCreateReq) {
        return userMapper.toVo(userService.create(userCreateReq));
    }

    @PutMapping("/{id}")
    @ApiOperation("update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    UserVo update(@PathVariable String id,
                  @Validated @RequestBody UserUpdateReq userUpdateReq) {
        return userMapper.toVo(userService.update(id, userUpdateReq));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/me")
    @ApiOperation("me")
    UserVo me() {
        return userMapper.toVo(userService.getCurrentUser());
    }
}
