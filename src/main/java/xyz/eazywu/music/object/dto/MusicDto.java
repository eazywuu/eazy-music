package xyz.eazywu.music.object.dto;

import lombok.Data;
import lombok.ToString;
import xyz.eazywu.music.object.enums.MusicStatusType;

@Data
@ToString(callSuper = true)
public class MusicDto extends BaseDto {

    private String name;

    private String description;

    private MusicStatusType status = MusicStatusType.DRAFT;

    private FileDto file;
}
