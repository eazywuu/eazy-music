package xyz.eazywu.music.object.request;

import lombok.Data;
import xyz.eazywu.music.object.dto.FileDto;

import javax.validation.constraints.NotBlank;

@Data
public class MusicCreateReq {
    @NotBlank(message = "音乐名不能为空")
    private String name;

    private String description;

    private FileDto file;
}
