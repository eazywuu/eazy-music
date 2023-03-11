package xyz.eazywu.music.exception;

/**
 * 自定义运行时异常
 */
public class BizException extends RuntimeException {
    /**
     * 异常状态码
     */
    private final Integer code;

    public BizException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
