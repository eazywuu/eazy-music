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

    @BeforeEach
     void setDefaultUser() {
        UserCreateReq userCreateRequestDto = new UserCreateReq();
        userCreateRequestDto.setUsername("test-data");
        userCreateRequestDto.setNickname("test-data");
        userCreateRequestDto.setPassword("test-data");
        userCreateRequestDto.setGender(GenderType.UNKNOWN);
        userService.create(userCreateRequestDto);
    }
}
