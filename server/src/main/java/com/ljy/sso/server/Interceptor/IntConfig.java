package com.ljy.sso.server.Interceptor;

import intercepter.InterceptorConfig;
import intercepter.SsoAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器配置
 * @author: ljy
 * @date: 2021年06月02日 13:29
 * @email 15810874514@163.com
 */
//@Configuration
public class IntConfig extends InterceptorConfig {
    /**
     * 拦截所有包含api路径（不限深度）的请求，但还需要通过判断是否有 @LoginRequired 注解 决定是否需要登录
     * 在方法上使用注解@UserLoginToken，表示该方法调用时需要验证是否登录
     * 在方法上使用注解@PassToken（或不加@UserLoginToken），
     *    表示该方法调用时不需要验证（尽量少用，毕竟这些方法在api路径下，理应验证平登录才正常）
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
       // registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**/api/**","/**/META-INF/**");
    }
   /* @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }*/

    @Bean
    public SsoAuthenticationInterceptor authenticationInterceptor() {
     // return   super.authenticationInterceptor();
      //  return new SsoAuthenticationInterceptor();
        return null;
    }
}


    