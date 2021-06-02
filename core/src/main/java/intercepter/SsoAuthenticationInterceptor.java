package intercepter;

import annotation.PassToken;
import annotation.UserLoginToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import store.SsoTokenLoginHelper;
import util.Conf;
import util.IPUtil;
import util.ResponseMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Description: SSO  拦截器
 * @author: ljy
 * @date: 2021年06月02日 11:08
 * @email 15810874514@163.com
 */

public class SsoAuthenticationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SsoAuthenticationInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //设置跨域
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Authorization,Real-IP");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");


        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(Conf.SSO_SESSIONID);

        // 1，如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

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
                // 没有token，直接回到登录页， 不进行提示
                if (token == null || token.equals("")) {
                    throw new RuntimeException(ResponseMsg.TOKEN_NOT_EXIST_CODE);
                }
                Object ssoCacheUserInfo = SsoTokenLoginHelper.loginCheck(token, IPUtil.getIpAddr(httpServletRequest));
                logger.info("loginCheck===>{}", ssoCacheUserInfo);
                if (ssoCacheUserInfo ==null  ) {
                    throw new RuntimeException(ResponseMsg.TOKEN_NOT_EXIST_CODE);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }

}

    