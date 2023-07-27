package xyz.eazywu.music.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.eazywu.music.exception.BizException;
import xyz.eazywu.music.exception.ErrorResponse;
import xyz.eazywu.music.exception.ResultType;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 自定义异常处理器
     * code: 500
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse bizExceptionHandler(BizException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTrace(e.getStackTrace());
        e.printStackTrace();
        return errorResponse;
    }

    /**
     * get请求参数校验错误
     * code 417
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public List<ErrorResponse> bindExceptionHandler(BindException e) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(HttpStatus.EXPECTATION_FAILED.value());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponseList.add(errorResponse);
        });
        e.printStackTrace();
        return errorResponseList;
    }

    /**
     * 访问权限异常处理器
     * code: 403
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ResultType.FORBIDDEN.getCode());
        errorResponse.setMessage(ResultType.FORBIDDEN.getMessage());
        e.printStackTrace();
        return errorResponse;
    }

    /**
     * 请求错误异常处理器
     * code: 400
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        /**
         * 出现多个异常时的处理
         */
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(ResultType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponseList.add(errorResponse);
        });
        e.printStackTrace();
        return errorResponseList;
    }

    /**
     * 其他异常处理器
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ResultType.INNER_ERROR.getCode());
        errorResponse.setMessage(ResultType.INNER_ERROR.getMessage());
        e.printStackTrace();
        return errorResponse;
    }
}
