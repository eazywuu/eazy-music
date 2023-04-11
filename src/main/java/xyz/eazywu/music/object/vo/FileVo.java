package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.FileStatusType;
import xyz.eazywu.music.object.enums.FileType;
import xyz.eazywu.music.object.enums.StorageType;

@Data
public class FileVo extends BaseVo{
    private String name;

    private String key;

    private String ext;

    private String url;

    private Long size;

    private FileType type;

    private StorageType storage;

    private FileStatusType status;

}
