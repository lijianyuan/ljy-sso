package config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description: 配置文件
 * @author: ljy
 * @date: 2021年06月01日 18:10
 * @email 15810874514@163.com
 */

public class SsoConfig implements InitializingBean, DisposableBean {
    
    /*
     *@Description: SsoConfig.destroy 注销方法
     *@Param: 
     *@return: 
     *@Author: ljy
     *@Date: 2021/6/2 9:34
     *@email: 15810874514@163.com
     *
     **/

    public void destroy() throws Exception {

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


    public void afterPropertiesSet() throws Exception {

    }
}

    