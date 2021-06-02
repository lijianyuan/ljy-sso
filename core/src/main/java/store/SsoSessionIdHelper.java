package store;

import util.JWTSSOUtil;

/**
 * @Description: SSO HelPer类
 * @author: ljy
 * @date: 2021年06月02日 10:55
 * @email 15810874514@163.com
 */

public class SsoSessionIdHelper {

    /*
     *@Description: SsoSessionIdHelper.makeTokenId 根据用户名 密码获取token 
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:55
     *@email: 15810874514@163.com
     *
     **/

    public static String makeTokenId(String  mobile, String password){
        String token = JWTSSOUtil.sign(mobile, password);
        return token;
    }

    /*
     *@Description: SsoSessionIdHelper.parseStoreKey 根据token获取用户名
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:56
     *@email: 15810874514@163.com
     *
     **/

    public static String parseStoreKey(String tokenId){
        String token =  JWTSSOUtil.getUsername(tokenId);
        return token;
    }
}

    