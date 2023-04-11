package xyz.eazywu.music.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.eazywu.music.object.dto.SiteSettingDto;
import xyz.eazywu.music.object.enums.StorageType;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class SettingServiceTest {

    @Resource
    SettingService settingService;

    @Test
    void getSiteSetting() {
        SiteSettingDto siteSetting = settingService.getSiteSetting();
        Assertions.assertNotNull(siteSetting.getBucket());
        Assertions.assertNotNull(siteSetting.getRegion());
        Assertions.assertInstanceOf(StorageType.class, siteSetting.getStorage());
    }
}
