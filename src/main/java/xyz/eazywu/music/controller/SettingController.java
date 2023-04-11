package xyz.eazywu.music.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.eazywu.music.mapper.SiteSettingMapper;
import xyz.eazywu.music.object.vo.SiteSettingVo;
import xyz.eazywu.music.service.SettingService;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    private final SiteSettingMapper siteSettingMapper;

    @GetMapping("/site")
    public SiteSettingVo getSiteSetting() {
        return siteSettingMapper.toVo(settingService.getSiteSetting());
    }
}
