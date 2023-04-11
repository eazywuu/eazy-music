package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.object.enums.StorageType;

@Data
public class SiteSettingDto {
    private String bucket;

    private String region;

    private StorageType storage;
}
