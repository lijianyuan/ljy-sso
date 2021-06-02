package store;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import entity.SsoUser;
import util.SsoHttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: SSO token 验证核心类
 * @author: ljy
 * @date: 2021年06月02日 10:56
 * @email 15810874514@163.com
 */

public class SsoTokenLoginHelper {

    /**
     * client login
     *
     * @param tokenId
     * @param ssoUser
     */
    public static void login(String tokenId, SsoUser ssoUser) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(tokenId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, tokenId:" + tokenId);
        }
        SsoLoginStore.put(storeKey, ssoUser);
    }

    /**
     * client logout
     *
     * @param sessionId
     */
    public static void logout(String sessionId) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }

        SsoLoginStore.remove(storeKey);
    }

    /*
     *@Description: SsoTokenLoginHelper.loginCheck 检查用户是否登录 单纯的根据tokenid 验证
     *@Param: sessionId:tokenId  unique:客户端设备唯一标识 unique
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 11:01
     *@email: 15810874514@163.com
     *
     **/

    public static SsoUser loginCheck(String  sessionId , String unique ){

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }
        SsoUser ssoUser = SsoLoginStore.get(storeKey);
        if (ssoUser != null) {
            // After the expiration time has passed half, Auto refresh
            if ((System.currentTimeMillis() - ssoUser.getExpireFreshTime()) > ssoUser.getExpireMinute()/5) {
                ssoUser.setExpireFreshTime(System.currentTimeMillis());
                SsoLoginStore.put(storeKey, ssoUser);
            }
            // 验证 token MD5SSOUtil.MD5()
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ssoUser.getPassword().trim())).build();
            try {
                jwtVerifier.verify(sessionId);
            } catch (JWTVerificationException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    /*
     *@Description: SsoTokenLoginHelper.getUser 获取用户信息
     *@Param:  sessionId
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:57
     *@email: 15810874514@163.com
     *
     **/

    public static SsoUser getUser(String  sessionId  ){

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }
        return SsoLoginStore.get(storeKey);
    }


    /*
     *@Description: SsoTokenLoginHelper.update 修改用户信息
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 11:04
     *@email: 15810874514@163.com
     *
     **/

    public static void update(String mobile,String clientAddress ) {

        Map<String, String> loginParam = new HashMap<String, String>();
        loginParam.put("mobile", mobile);
        String  [] clientAddresss = clientAddress.split(";");
        for (String addr:clientAddresss
        ) {
            SsoHttpClientUtil.post(addr, loginParam, null);
        }
    }

}

    