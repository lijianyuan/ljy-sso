package annotation;

/*
 *@Description: UserLoginToken 需要验证
 *@Param: 
 *@return: 
 *@Author: ljy
 *@Date: 2021/6/2 11:20
 *@email: 15810874514@163.com
 *
 **/

public @interface UserLoginToken {

    boolean required() default true;
}
