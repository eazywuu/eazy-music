package xyz.eazywu.music.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code;

    private String message;

    // 错误栈信息
    private Object trace;
}
