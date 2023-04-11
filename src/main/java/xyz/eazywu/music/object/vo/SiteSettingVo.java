package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.StorageType;

@Data
public class SiteSettingVo {
    private String bucket;

    private String region;

    private StorageType storage;
}
