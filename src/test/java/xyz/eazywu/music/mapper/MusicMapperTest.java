package xyz.eazywu.music.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.eazywu.music.object.dto.MusicDto;
import xyz.eazywu.music.object.request.MusicCreateReq;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class MusicMapperTest {

    @Resource
    private MusicMapper musicMapper;

    @Test
    void toDto() {
        MusicCreateReq req = new MusicCreateReq();
        req.setName("wyz");
        req.setFileId("11111");
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        req.setArtistIds(list);
        MusicDto musicDto = musicMapper.toDto(req);
        log.info(musicDto.getFile().getId());
        log.info(musicDto.toString());
    }
}
