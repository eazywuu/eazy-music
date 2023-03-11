package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.enums.FileStatusEnum;
import xyz.eazywu.music.enums.FileTypeEnum;
import xyz.eazywu.music.enums.StorageEnum;

import java.util.Date;

@Data
public class FileDto {
    private String id;

    private String name;

    private String key;

    private String ext;

    private FileTypeEnum type;

    private StorageEnum storage;

    private FileStatusEnum status;

    private String lastLoginIp;

    private Date lastLoginTime;
}
