package xyz.eazywu.music.controller;

        import io.swagger.annotations.Api;
        import lombok.RequiredArgsConstructor;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import xyz.eazywu.music.object.request.TokenCreateReq;
        import xyz.eazywu.music.service.UserService;

/**
 * token管理
 */
@Api(tags = "token管理接口")
@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final UserService userService;

    @PostMapping
    public String create(@Validated @RequestBody TokenCreateReq tokenCreateReq) {
        return userService.createToken(tokenCreateReq);
    }
}
