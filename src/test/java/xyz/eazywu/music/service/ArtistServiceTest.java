package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import xyz.eazywu.music.mapper.ArtistMapper;
import xyz.eazywu.music.object.dto.ArtistDto;
import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.dto.FileUploadDto;
import xyz.eazywu.music.object.request.ArtistCreateReq;
import xyz.eazywu.music.object.request.FileUploadReq;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class ArtistServiceTest extends BaseTest {
    @Resource
    private ArtistService artistService;

    @Resource
    private ArtistMapper artistMapper;

    @Resource
    private FileService fileService;

    private String photoId;

    List<String> artistIds;

    @Test
    @WithMockUser(username = BaseTest.MOCK_NAME)
    void create() {
        ArtistCreateReq artistCreateReq = new ArtistCreateReq();
        artistCreateReq.setName("陶喆");
        artistCreateReq.setRemark("David Tao");
        ArtistDto artistDto = artistService.create(artistMapper.toDto(artistCreateReq));
        Assertions.assertEquals(artistCreateReq.getName(), artistDto.getName());
        Assertions.assertEquals(artistCreateReq.getRemark(), artistDto.getRemark());
        Assertions.assertEquals("user_wyz", artistDto.getCreatedBy().getUsername());
        log.info(artistDto.toString());
    }

    @BeforeEach
    public void setDefaultPhotoId() {
        FileUploadReq fileUploadReq = new FileUploadReq();
        fileUploadReq.setName("测试文件名");
        fileUploadReq.setExt("mp3");
        fileUploadReq.setKey("835741aba850778a5b06bfd57f55c98c");
        fileUploadReq.setSize(3000L);
        FileUploadDto fileUploadDto = fileService.initUpload(fileUploadReq);
        FileDto fileFinished = fileService.finishUpload(fileUploadDto.getFileId());
        photoId = fileFinished.getId();
    }

}
