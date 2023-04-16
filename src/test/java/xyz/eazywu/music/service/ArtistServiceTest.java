package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import xyz.eazywu.music.object.request.ArtistCreateReq;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class ArtistServiceTest extends BaseTest {
    @Resource
    private ArtistService artistService;

    @Test
    @WithMockUser(username = "test-data")
    void create() {
        ArtistCreateReq artistCreateReq = new ArtistCreateReq();
        artistCreateReq.setName("陶喆");
        artistCreateReq.setRemark("David Tao");
        artistService.create(artistCreateReq);
    }
}
