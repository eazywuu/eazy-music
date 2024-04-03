package xyz.eazywu.music.object.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class MusicUpdateReq {
    @NotBlank(message = "音乐名不能为空")
    private String name;

    private String description;

    private String fileId;

    @NotEmpty(message = "歌手未选择")
    private List<String> artistIds;
}
