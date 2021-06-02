package config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import store.SsoLoginStore;
import util.Conf;
import util.JedisUtil;
import util.SsoUtils;

/**
 * @Description: 配置文件
 * @author: ljy
 * @date: 2021年06月01日 18:10
 * @email 15810874514@163.com
 */
public class SsoConfig  implements InitializingBean, DisposableBean{

    @Value("${sso.redis.address}")
    private String redisAddress;
    @Value("${sso.redis.expire.minute}")
    private int redisExpireMinute;
    @Value("${sso.server}")
    private  String ssoServer;
    @Value("${sso.type}")
    private  String type;


    /*
     *@Description: SsoConfig.destroy 注销方法
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 9:34
     *@email: 15810874514@163.com
     *
     **/
    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

    /*
     *@Description: SsoConfig.afterPropertiesSet  加载Properties参数后设置的
     *@Param:
     *@return:
     *@Author: ljy
     *@Date: 2021/6/2 9:22
     *@email: 15810874514@163.com
     *
     **/

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Conf.SSO_SERVER.equals(type))
        {
            SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
            JedisUtil.initDefault(redisAddress);
        }else if (Conf.SSO_ClIENT.equals(type))
        {
            SsoUtils.initDefault(ssoServer);
        }
    }
}

    