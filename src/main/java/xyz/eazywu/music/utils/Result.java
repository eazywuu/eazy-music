package xyz.eazywu.music.utils;

import lombok.Data;
import xyz.eazywu.music.exception.ResultType;

/**
 * @author wyz
 * @date 2021/05/12
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
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(ResultType.SUCCESS.getMessage());
        return result;
    }

    /**
     * 成功，有返回数据
     */
    public static <V> Result<V> success(V data) {
        Result<V> result = new Result<>();
        result.code = ResultType.SUCCESS.getCode();
        result.message = ResultType.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    /**
     * 失败
     */
    public static Result<Void> failure() {
        Result<Void> result = new Result<>();
        result.setCode(ResultType.FAILURE.getCode());
        result.setMessage(ResultType.FAILURE.getMessage());
        return result;
    }

    /**
     * 失败，自定义失败信息
     */
    public static Result<Void> failure(String message) {
        Result<Void> result = new Result<>();
        result.setCode(ResultType.FAILURE.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 失败，使用已定义枚举
     */
    public static Result<Void> failure(ResultType ResultType) {
        Result<Void> result = new Result<>();
        result.setCode(ResultType.getCode());
        result.setMessage(ResultType.getMessage());
        return result;
    }
}
