# ljy-sso

## java sso 单点登录系统 支持分布式  

## 集成步骤
## 1、客户端集成  SsoAuthenticationInterceptor拦截器实现登录拦截
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
        