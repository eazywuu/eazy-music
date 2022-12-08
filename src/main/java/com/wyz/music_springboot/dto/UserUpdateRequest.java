package com.wyz.music_springboot.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {
    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 64, message = "密码长度应该在4个字符到64个字符之间")
    private String password;
    @NotBlank(message = "别名不能为空")
    @Size(min = 4, max = 64, message = "别名长度应该在4个字符到64个字符之间")
    private String nickname;

    private String gender;
}
