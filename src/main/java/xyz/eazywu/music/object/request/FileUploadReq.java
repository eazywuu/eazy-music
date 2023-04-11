package xyz.eazywu.music.object.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FileUploadReq {

    @NotBlank(message = "文件名不能为空！")
    private String name;

    @NotBlank(message = "key不能为空！")
    private String key;

    @NotBlank(message = "后缀名不能为空！")
    private String ext;

    private Long size;
}
