package xyz.eazywu.music.object.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.eazywu.music.object.enums.FileStatusType;
import xyz.eazywu.music.object.enums.FileType;
import xyz.eazywu.music.object.enums.StorageType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class File extends TraceableEntity {
    /**
     * 文件名
     */
    private String name;

    /**
     * hash-key做文件唯一标识
     */
    @Column(name = "file_key")
    private String key;

    /**
     * Extended file system: 延伸文件系统，文件后缀名
     */
    private String ext;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 上传文件类型
     * Enumerated注解：持久层（数据库）存储类型，默认 ORDINAL
     */
    @Enumerated(EnumType.STRING)
    private FileType type;

    /**
     * 文件存储方式
     */
    @Enumerated(EnumType.STRING)
    private StorageType storage;

    /**
     * 文件上传状态
     */
    @Enumerated(EnumType.STRING)
    private FileStatusType status = FileStatusType.UPLOADING;

}
