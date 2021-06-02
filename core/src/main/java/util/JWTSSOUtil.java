package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Objects;

/**
 * @Description: SSOJWT工具类生成token sessionId
 * @author: ljy
 * @date: 2021年06月02日 10:42
 * @email 15810874514@163.com
 */

public class JWTSSOUtil {

    // 过期时间12天
    private static final long EXPIRE_TIME = 12 * 24 * 60 * 68 * 1000;

    /**
     * jwt表明用户身份的数据
     */
    private static final String JWT_CLAIM_MOBILE = "mobile";


    /*
     *@Description: JWTSSOUtil.verify 校验token是否正确
     *@param token  密钥
     *@param mobile 手机号
     *@param password 密码
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 10:43
     *@email: 15810874514@163.com
     *
     **/
    public static boolean verify(String token, String mobile, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).
                    withClaim(JWT_CLAIM_MOBILE, mobile).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     *@Description: JWTSSOUtil.getUsername 获取登录名
     *@Param:  token 用户token
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 10:43
     *@email: 15810874514@163.com
     *
     **/
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            if(Objects.isNull(jwt.getClaim(JWT_CLAIM_MOBILE))){
                return null;
            }
            String userName = jwt.getClaim(JWT_CLAIM_MOBILE).asString();
            return userName;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /*
     *@Description: JWTSSOUtil.sign  生成签名
     *@Param: mobile password
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 10:44
     *@email: 15810874514@163.com
     *
     **/

    public static String sign(String mobile, String password) {
        try {
            // 指定过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password); //MD5SSOUtil.MD5(
            return JWT.create()
                    .withClaim(JWT_CLAIM_MOBILE, mobile)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

}

    