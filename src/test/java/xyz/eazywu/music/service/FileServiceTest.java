package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import xyz.eazywu.music.object.dto.FileDto;
import xyz.eazywu.music.object.dto.FileUploadDto;
import xyz.eazywu.music.object.enums.FileStatusType;
import xyz.eazywu.music.object.enums.StorageType;
import xyz.eazywu.music.object.request.FileUploadReq;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class FileServiceTest extends BaseTest {

    @Resource
    private FileService fileService;

    @Test
    void initUpload() {
        FileUploadReq fileUploadReq = new FileUploadReq();
        fileUploadReq.setName("test-filename");
        fileUploadReq.setKey("c20ad4d76fe97759aa27a0c99bff6710");
        fileUploadReq.setExt("mp3");
        fileUploadReq.setSize(30000L);
        FileUploadDto fileUploadDto = fileService.initUpload(fileUploadReq);
        Assertions.assertNotNull(fileUploadDto.getFileId());
        Assertions.assertNotNull(fileUploadDto.getFileKey());
        Assertions.assertNotNull(fileUploadDto.getSecretId());
        Assertions.assertNotNull(fileUploadDto.getSecretKey());
        Assertions.assertNotNull(fileUploadDto.getSessionToken());
        Assertions.assertEquals(fileUploadDto.getFileKey(), fileUploadReq.getKey());
        log.info(fileUploadDto.toString());
    }

    @Test
    @WithMockUser(username = "test-data")
    void finishUpload() {
        FileUploadReq fileUploadReq = new FileUploadReq();
        fileUploadReq.setName("test-filename");
        fileUploadReq.setKey("c20ad4d76fe97759aa27a0c99bff6710");
        fileUploadReq.setExt("mp3");
        fileUploadReq.setSize(30000L);
        FileUploadDto fileUploadDto = fileService.initUpload(fileUploadReq);
        FileDto fileDto = fileService.finishUpload(fileUploadDto.getFileId());
        log.info(fileDto.toString());
        Assertions.assertEquals(fileUploadDto.getFileId(), fileDto.getId());
        Assertions.assertEquals(FileStatusType.UPLOADED, fileDto.getStatus());
    }

    @Test
    void getDefaultStorage() {
        StorageType defaultStorage = fileService.getDefaultStorage();
        Assertions.assertEquals(StorageType.COS, defaultStorage);
    }
}
