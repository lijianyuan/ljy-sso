package com.example.demo001.conf;

import config.SsoConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置文件
 * @author: ljy
 * @date: 2021年06月01日 18:10
 * @email 15810874514@163.com
 */
@Configuration
public class Config extends SsoConfig {

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
        super.destroy();
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
        super.afterPropertiesSet();
    }
}

    