package xyz.eazywu.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.eazywu.music.enums.MusicStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 音乐实体类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music extends AbstractEntity{
    /**
     * 音乐名
     */
    private String name;
    /**
     * 音乐简介
     */
    private String description;
    /**
     * 音乐上架状态
     */
    @Enumerated(EnumType.STRING)
    private MusicStatus status;
}
