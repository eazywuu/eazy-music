package xyz.eazywu.music.object.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArtistUpdateReq {
    @NotBlank(message = "歌手名不能为空")
    private String name;

    private String remark;

    @NotBlank(message = "请上传歌手照片")
    private String photoId;
}
