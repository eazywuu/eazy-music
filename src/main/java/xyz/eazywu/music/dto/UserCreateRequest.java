package xyz.eazywu.music.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 创建用户请求数据
 */
@Data
public class UserCreateRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 64, message = "密码长度应该在4个字符到64个字符之间")
    private String password;

    private String nickname;

    private String gender;
}
