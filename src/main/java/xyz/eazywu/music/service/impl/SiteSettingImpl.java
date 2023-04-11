package xyz.eazywu.music.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.eazywu.music.object.dto.SiteSettingDto;
import xyz.eazywu.music.service.FileService;
import xyz.eazywu.music.service.SettingService;

@Service
@RequiredArgsConstructor
public class SiteSettingImpl implements SettingService {

    @Value("${cos.bucket}")
    private String bucket;

    @Value("${cos.region}")
    private String region;

    private final FileService fileService;

    @Override
    public SiteSettingDto getSiteSetting() {
        SiteSettingDto siteSettingDto = new SiteSettingDto();
        siteSettingDto.setBucket(bucket);
        siteSettingDto.setRegion(region);
        siteSettingDto.setStorage(fileService.getDefaultStorage());
        return siteSettingDto;
    }
}
