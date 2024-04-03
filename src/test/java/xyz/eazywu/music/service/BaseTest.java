package xyz.eazywu.music.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.eazywu.music.object.enums.GenderType;
import xyz.eazywu.music.object.request.UserCreateReq;

import javax.annotation.Resource;

@SpringBootTest
public abstract class BaseTest {
    @Resource
    private UserService userService;

    public static final String MOCK_NAME = "user_wyz";

    @BeforeEach
     void setDefaultUser() {
        UserCreateReq userCreateReq = new UserCreateReq();
        userCreateReq.setUsername("user_wyz");
        userCreateReq.setNickname("user_wyz");
        userCreateReq.setPassword("user_wyz");
        userCreateReq.setGender(GenderType.UNKNOWN);
        userService.create(userCreateReq);
    }
}
