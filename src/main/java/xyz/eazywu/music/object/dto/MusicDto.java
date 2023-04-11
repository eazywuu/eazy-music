package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.object.enums.MusicStatusType;

@Data
public class MusicDto extends BaseDto {

    private String name;

    private String description;

    private MusicStatusType status;

    private FileDto file;
}
