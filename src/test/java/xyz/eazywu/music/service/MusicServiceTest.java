package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.mapper.MusicMapper;
import xyz.eazywu.music.object.dto.*;
import xyz.eazywu.music.object.enums.MusicStatusType;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.FileUploadReq;
import xyz.eazywu.music.object.request.MusicCreateReq;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class MusicServiceTest extends BaseTest{

    @Resource
    MusicService musicService;

    @Resource
    MusicMapper musicMapper;

    @Resource
    private FileService fileService;

    @Resource
    ArtistService artistService;

    @Resource
    ArtistMapper artistMapper;

    String fileId;

    List<String> artistIds = new ArrayList<>();

    @Test
    @WithMockUser(username = BaseTest.MOCK_NAME)
    void create() {
        MusicCreateReq musicCreateReq = new MusicCreateReq();
        musicCreateReq.setArtistIds(artistIds);
        musicCreateReq.setFileId(fileId);
        musicCreateReq.setName("沙滩");
        MusicDto savedMusicDto = musicService.create(musicMapper.toDto(musicCreateReq));
        Assertions.assertEquals(musicCreateReq.getName(), savedMusicDto.getName());
        Assertions.assertEquals(musicCreateReq.getDescription(), savedMusicDto.getDescription());
        Assertions.assertEquals(MusicStatusType.DRAFT, savedMusicDto.getStatus());
        Assertions.assertEquals(musicCreateReq.getFileId(), savedMusicDto.getFile().getId());
        Assertions.assertEquals(
                musicCreateReq.getArtistIds(),
                savedMusicDto.getArtistList().stream().map(BaseDto::getId).collect(Collectors.toList()));
        log.info(savedMusicDto.toString());
    }

    @BeforeEach
    public void setDefaultFileId() {
        FileUploadReq fileUploadReq = new FileUploadReq();
        fileUploadReq.setName("测试文件名");
        fileUploadReq.setExt("mp3");
        fileUploadReq.setKey("835741aba850778a5b06bfd57f55c98c");
        fileUploadReq.setSize(3000L);
        FileUploadDto fileUploadDto = fileService.initUpload(fileUploadReq);
        FileDto fileFinished = fileService.finishUpload(fileUploadDto.getFileId());
        fileId = fileFinished.getId();
    }

    @BeforeEach
    public void setDefaultArtistIds() {
        ArtistCreateReq artistCreateReq = new ArtistCreateReq();
        artistCreateReq.setPhotoId(fileId);
        artistCreateReq.setName("陶喆");
        artistCreateReq.setRemark("David Tao");
        ArtistDto artistDto = artistService.create(artistMapper.toDto(artistCreateReq));
        artistIds.add(artistDto.getId());
    }

}
