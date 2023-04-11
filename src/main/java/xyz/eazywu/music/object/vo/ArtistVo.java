package xyz.eazywu.music.object.vo;

import lombok.Data;
import xyz.eazywu.music.object.enums.ArtistStatusType;

import java.util.List;

@Data
public class ArtistVo extends BaseVo{
    private String name;

    private String remark;

    private FileVo photo;

    private List<MusicVo> musicList;

    private ArtistStatusType status;
}
