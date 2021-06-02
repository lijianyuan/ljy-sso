# ljy-sso

## java sso 单点登录系统 支持分布式  

## 集成步骤
## 1、客户端集成  SsoAuthenticationInterceptor拦截器实现登录拦截 测试的InterceptorConfig 
## 2、拦截器通过   PassToken      UserLoginToken 注释方式实现登录和不登陆的拦截
           // 2，检查是否有passtoken注释，有则跳过认证
          if (method.isAnnotationPresent(PassToken.class)) {
              PassToken passToken = method.getAnnotation(PassToken.class);
              if (passToken.required()) {
                  return true;
              }
          }
  
          // 3，检查Token是否有效的注解
          if (method.isAnnotationPresent(UserLoginToken.class)) {
              UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
              if (userLoginToken.required()) {
              
##3、客户端pom 引入的包
         sso核心jar
         <dependency>
            <groupId>com.ljy.sso</groupId>
            <artifactId>sso.core</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!-- httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>RELEASE</version>
        </dependency>
##3、服务端pom 引入的包
<!-- SSO 需要的jar   -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.3.7</version>
        </dependency>

        <!-- slf4j -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>

        <!-- jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.2.0</version>
        </dependency>
        <!-- jwt  -->
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.4.0</version>
        </dependency>
        <!-- spring Boot   -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>RELEASE</version>
        </dependency>
        <!-- SSO 需要的jar   -->

#测试代码 

    https://github.com/lijianyuan/ljy-sso/blob/main/demo001/src/main/java/com/example/demo001/controller/http/user.http
    https://github.com/lijianyuan/ljy-sso/blob/main/demo002/src/main/java/com/example/demo002/controller/http/user.http
    
###  集成SsoConfig 类型加载启动参数
#### 核心配置  application.properties 中配置 4个都要配置没用的可以为空
    ### sso
    sso.redis.address=redis://127.0.0.1:6379 服务端必须配置值 客户端可以不填写值
    sso.redis.expire.minute=1440 redis 过期时间
    sso.server=http://localhost:8080/sso-server/  单点登录地址 服务端不需要填写 服务端可以不填写值
    sso.type=1 服务端客户端类型区别  1服务端2客户端
##### 如果有疑问可以加微信  yuanyuanhhhjjj