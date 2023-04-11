package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.eazywu.music.object.dto.PlaylistDto;

import javax.annotation.Resource;

@SpringBootTest

@Slf4j
class PlaylistServiceTest {
    @Resource
    private PlaylistService playlistService;

    @Test
    void get() {
        PlaylistDto playlistDto = playlistService.get("11");
    }
}
