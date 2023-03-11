package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.enums.MusicStatusEnum;

import java.util.Date;

@Data
public class MusicVo {
    private String id;
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
    private MusicStatusEnum status;

    private Date createdTime;

    private Date updatedTime;
}
