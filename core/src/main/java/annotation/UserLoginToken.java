package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *@Description: UserLoginToken 需要验证
 *@Param: 
 *@return: 
 *@Author: ljy
 *@Date: 2021/6/2 11:20
 *@email: 15810874514@163.com
 *
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {

    boolean required() default true;
}
