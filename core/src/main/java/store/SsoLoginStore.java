package store;

import entity.SsoUser;
import util.Conf;
import util.JedisUtil;

/**
 * @Description: sso核心类
 * @author: ljy
 * @date: 2021年06月02日 10:47
 * @email 15810874514@163.com
 */

public class SsoLoginStore {

    private static int redisExpireMinute = 30;    // 30 minute, 0.5 hour
    public static void setRedisExpireMinute(int redisExpireMinute) {
        if (redisExpireMinute < 30) {
            redisExpireMinute = 30;
        }
        SsoLoginStore.redisExpireMinute = redisExpireMinute;
    }
    public static int getRedisExpireMinute() {
        return redisExpireMinute;
    }

    /*
     *@Description: SsoLoginStore.get jedis 获取得想
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:50
     *@email: 15810874514@163.com
     *
     **/

    public static SsoUser get(String storeKey) {

        String redisKey = redisKey(storeKey);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            return (SsoUser)objectValue;
        }
        return null;
    }

    /*
     *@Description: SsoLoginStore.remove 删除对象
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:50
     *@email: 15810874514@163.com
     *
     **/

    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    /*
     *@Description: SsoLoginStore.put 存对象
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 10:50
     *@email: 15810874514@163.com
     *
     **/

    public static void put(String storeKey, SsoUser ssoUser) {
        String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey, ssoUser, redisExpireMinute * 60);  // minute to second
    }

    private static String redisKey(String sessionId){
        return Conf.SSO_SESSIONID.concat("#").concat(sessionId);
    }
}

    