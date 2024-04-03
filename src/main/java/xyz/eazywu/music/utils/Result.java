package xyz.eazywu.music.utils;

import lombok.Data;
import xyz.eazywu.music.exception.ExceptionType;

/**
 * @author wyz
 * @since 2021/05/12
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    /**
     * 成功
     */
    public static Result<Void> success() {
        Result<Void> result = new Result<>();
        result.setCode(ExceptionType.SUCCESS.getCode());
        result.setMessage(ExceptionType.SUCCESS.getMessage());
        return result;
    }

    /**
     * 成功，有返回数据
     */
    public static <V> Result<V> success(V data) {
        Result<V> result = new Result<>();
        result.code = ExceptionType.SUCCESS.getCode();
        result.message = ExceptionType.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    /**
     * 失败
     */
    public static Result<Void> failure() {
        Result<Void> result = new Result<>();
        result.setCode(ExceptionType.FAILURE.getCode());
        result.setMessage(ExceptionType.FAILURE.getMessage());
        return result;
    }

    /**
     * 失败，自定义失败信息
     */
    public static Result<Void> failure(String message) {
        Result<Void> result = new Result<>();
        result.setCode(ExceptionType.FAILURE.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 失败，使用已定义枚举
     */
    public static Result<Void> failure(ExceptionType exceptionType) {
        Result<Void> result = new Result<>();
        result.setCode(exceptionType.getCode());
        result.setMessage(exceptionType.getMessage());
        return result;
    }
}
