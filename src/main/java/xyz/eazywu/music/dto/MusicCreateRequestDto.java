package xyz.eazywu.music.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MusicCreateRequestDto {
    @NotBlank(message = "音乐名不能为空")
    private String name;

    private String description;
}
