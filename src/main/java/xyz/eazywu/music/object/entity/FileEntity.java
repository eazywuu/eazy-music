package xyz.eazywu.music.object.entity;

import lombok.Data;
import xyz.eazywu.music.enums.FileStatusEnum;
import xyz.eazywu.music.enums.FileTypeEnum;
import xyz.eazywu.music.enums.StorageEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class FileEntity extends AbstractEntity{
    /**
     * 文件名
     */
    private String name;

    /**
     * hash-key做文件唯一标识
     */
    private String key;

    /**
     * Extended file system: 延伸文件系统
     */
    private String ext;

    /**
     * 上传文件类型
     * Enumerated注解：持久层（数据库）存储类型，默认 ORDINAL
     */
    @Enumerated(EnumType.STRING)
    private FileTypeEnum type;

    /**
     * 文件存储方式
     */
    private StorageEnum storage;

    /**
     * 文件上传状态
     */
    private FileStatusEnum status;

}
