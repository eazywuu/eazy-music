package com.wyz.music_springboot.repository;

import com.wyz.music_springboot.entity.User;
import com.wyz.music_springboot.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("依力");
        user.setNickname("程序猿依力");
        user.setEnabled(true);
        user.setLocked(false);
        user.setPassword("9095155");
        user.setGender(Gender.MALE);
        user.setLastLoginIp("127.0.0.1");
        user.setLastLoginTime(new Date());
        User savedUser = userRepository.save(user);
        User result = userRepository.getByUsername("依力");
        System.out.println(result.toString());
    }
}
