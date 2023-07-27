package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.object.enums.ArtistStatusType;

import java.util.List;

@Data
public class ArtistDto extends TraceableDto{
    private String name;

    private String remark;

    private FileDto photo;

    private List<MusicDto> musicList;

    private ArtistStatusType status = ArtistStatusType.DRAFT;
}
