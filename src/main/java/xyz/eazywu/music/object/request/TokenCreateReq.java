package xyz.eazywu.music.object.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 创建token请求携带的数据格式
 */
@Data
public class TokenCreateReq {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 24, message = "用户名长度应该在6个字符到24个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 24, message = "密码长度应该在6个字符到24个字符之间")
    private String password;
}
