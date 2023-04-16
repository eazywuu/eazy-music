package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.eazywu.music.object.dto.MusicDto;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class MusicServiceTest {

    @Resource
    MusicService musicService;

    @Test
    void list() {

    }

    @Test
    void create() {
        MusicDto musicDto = new MusicDto();
        musicDto.setName("测试音乐");
        musicDto.setDescription("测试音乐描述");
        MusicDto musicDto1 = musicService.create(musicDto);
        log.info(musicDto1.toString());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }

}
