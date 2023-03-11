package xyz.eazywu.music.object.dto;

import lombok.Data;
import xyz.eazywu.music.enums.MusicStatusEnum;

import java.util.Date;

@Data
public class MusicDto {
    private String id;

    private String name;

    private String description;

    private MusicStatusEnum status;

    private Date createdTime;

    private Date updatedTime;
}
