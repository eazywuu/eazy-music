package xyz.eazywu.music.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对于一些第三方回调之类的接口,可能要求我们的接口响应格式符合第三方的规定
 * 所以这些接口不需要进行统一包装, 我们可以通过在这些接口上添加一个自定义注解来绕过统一包装类的处理。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotResultWrap {
}
