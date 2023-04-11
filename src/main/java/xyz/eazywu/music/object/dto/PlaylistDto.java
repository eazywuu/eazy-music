package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.object.enums.PlaylistStatusType;

import java.util.List;

@Data
public class PlaylistDto extends BaseDto {
    private String name;

    private String description;

    private FileDto cover;

    private PlaylistStatusType status;

    private UserDto creator;

    private List<MusicDto> musicList;
}
