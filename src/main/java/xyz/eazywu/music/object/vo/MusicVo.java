package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.MusicStatusType;

import java.util.List;

@Data
public class MusicVo extends BaseVo {
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
    private MusicStatusType status;

    private FileVo file;

    private List<ArtistVo> artistList;

}
