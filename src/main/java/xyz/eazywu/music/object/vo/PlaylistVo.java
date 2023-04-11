package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.PlaylistStatusType;

import java.util.List;

@Data
public class PlaylistVo extends BaseVo{
    private String name;

    private String description;

    private FileVo cover;

    private PlaylistStatusType status;

    private UserVo creator;

    private List<MusicVo> musicList;
}
