package config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import store.SsoLoginStore;
import util.JedisUtil;

/**
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class XxlSsoConfig implements InitializingBean, DisposableBean {

    @Value("${sso.redis.address}")
    private String redisAddress;

    @Value("${sso.redis.expire.minute}")
    private int redisExpireMinute;

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
        JedisUtil.initDefault(redisAddress);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

}
