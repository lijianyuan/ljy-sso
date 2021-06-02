package util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.SsoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: sso  工具类
 * @author: ljy
 * @date: 2021年06月02日 9:14
 * @email 15810874514@163.com
 */
@Component
public class SsoUtils<T>   {
    private static Logger logger = LoggerFactory.getLogger(SsoUtils.class);
    private T t;
    // sso 已经登陆  未登录
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;


    public static void initDefault(String ssoServer ) {
        SsoUtils.ssoServer = ssoServer;

    }

    public static String ssoServer ;


    /*
     *@Description: SsoUtils.loginCheck 登录状态检查
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 14:48
     *@email: 15810874514@163.com
     *
     **/

    public static Boolean loginCheck(String tokenId ,String unique ) throws IOException {
        long startMilliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // logout url
        String logincheckUrl = ssoServer + "app/loginCheck";
        // logout param
        Map<String, String> logincheckParam = new HashMap<>();
        logincheckParam.put("sessionId", tokenId);
        logincheckParam.put("unique", unique);
        logger.info("http sso-server loginCheck==>{}", logincheckUrl);
        logger.info("http sso-server loginCheck params==>{}", logincheckParam);

        try {
            String logincheckResultJson = SsoHttpClientUtil.post(logincheckUrl, logincheckParam, null);
            if(logincheckResultJson==null || logincheckResultJson.equals("")){
                return false;
            }
            Map<String, Object> logincheckResult = new ObjectMapper().readValue(logincheckResultJson, Map.class);
            if(Objects.isNull(logincheckResult)){
                return false;
            }
            int code = (int) logincheckResult.get("code");
            if (code == SUCCESS_CODE) {
                Map user = (Map) logincheckResult.get("data");
                if(Objects.isNull(user) || user.isEmpty()){
                    logger.info("当前为登录状态，登陆用户 = " + user.toString());
                   return true;
                }
            } else {
                logger.info("当前为注销状态");
                return null;
            }
        }catch (Exception ex){
            throw ex;
        }finally {
            long endMilliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            logger.info("http sso-server loginCheck execution time second==>{}", (endMilliSecond - startMilliSecond) / 1000);
        }
        return false;
    }


    /*
     *@Description: SsoUtils.loginCheck 登录状态检查
     *@Param:
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 14:48
     *@email: 15810874514@163.com
     *
     **/

    public  T getUser(String tokenId ,String unique ,Class<T> clazz) throws IOException {
        long startMilliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // logout url
        String logincheckUrl = ssoServer + "app/getUser";
        // logout param
        Map<String, String> getUserParam = new HashMap<>();
        getUserParam.put("sessionId", tokenId);
        getUserParam.put("unique", unique);
        logger.info("http sso-server loginCheck==>{}", logincheckUrl);
        logger.info("http sso-server loginCheck params==>{}", getUserParam);

        try {
            String logincheckResultJson = SsoHttpClientUtil.post(logincheckUrl, getUserParam, null);
            if(logincheckResultJson==null || logincheckResultJson.equals("")){
                return null;
            }
            Map<String, Object> logincheckResult = new ObjectMapper().readValue(logincheckResultJson, Map.class);
            if(Objects.isNull(logincheckResult)){
                return null;
            }
            int code = (int) logincheckResult.get("code");
            if (code == SUCCESS_CODE) {
                Map user = (Map) logincheckResult.get("data");
                if(Objects.isNull(user) || user.isEmpty()){
                    logger.info("当前为登录状态，登陆用户 = " + user.toString());
                    t = JSON.toJavaObject(JSONObject.parseObject((String)user.get("userInfo")),clazz);
                    return t;
                }
            } else {
                logger.info("当前为注销状态");
                return null;
            }
        }catch (Exception ex){
            throw ex;
        }finally {
            long endMilliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            logger.info("http sso-server loginCheck execution time second==>{}", (endMilliSecond - startMilliSecond) / 1000);
        }
        return null;
    }


}
