package xyz.eazywu.music.exception;

import lombok.Data;

/**
 * 异常响应体
 */
@Data
public class ErrorResponse {
    private Integer code;

    private String message;

    // 错误栈信息
    private Object trace;
}
