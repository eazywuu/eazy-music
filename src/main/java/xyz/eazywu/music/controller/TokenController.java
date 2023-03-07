package xyz.eazywu.music.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eazywu.music.dto.TokenCreateRequestDto;
import xyz.eazywu.music.service.UserService;

/**
 * @author wyz
 */
@Api(tags = "token管理接口")
@RestController
@RequestMapping("/tokens")
public class TokenController {

    UserService userService;

    @PostMapping
    public String create(@RequestBody TokenCreateRequestDto tokenCreateRequestDto) {
        return userService.createToken(tokenCreateRequestDto);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
