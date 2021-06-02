package com.ljy.sso.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljy.sso.server.dto.UserDto;
import com.ljy.sso.server.service.LoginService;
import entity.SsoUser;
import org.springframework.stereotype.Service;
import store.SsoLoginStore;
import store.SsoSessionIdHelper;
import store.SsoTokenLoginHelper;
import util.IPUtil;
import util.JWTSSOUtil;
import util.ReturnT;

import java.beans.Transient;

/**
 * @Description: LoginService
 * @author: ljy
 * @date: 2021年06月02日 16:31
 * @email 15810874514@163.com
 */

@Service
public class LoginServiceImpl implements LoginService {


    @Override
    @Transient
    public ReturnT register(UserDto userDto) {
            // 自己公司的业务逻辑 注册验证
        return ssoLogin(userDto);
    }

    @Override
    @Transient
    public ReturnT login(UserDto userDto) {
        // 自己公司的业务逻辑 登录验证
        return ssoLogin(userDto);
    }

    @Override
    @Transient
    public ReturnT ssoLogin(UserDto userDto) {
        // 1、make sso user
        SsoUser u = new SsoUser();
        u.setName(userDto.getName());
        u.setMobile(userDto.getMobile());
        u.setPassword(userDto.getPassword());
        u.setUserInfo(JSONObject.toJSONString(userDto));
        u.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        u.setExpireFreshTime(System.currentTimeMillis());
        u.setUnique(userDto.getUnique());
        String sessionId = SsoSessionIdHelper.makeTokenId(u.getMobile(),u.getPassword());
        SsoTokenLoginHelper.login(sessionId, u);
        return new ReturnT<String>(sessionId);
    }

    @Override
    @Transient
    public ReturnT forgetPwdSubmit(UserDto userDto) {
        // 自己公司的业务逻辑

        // userDto 为最新的用户信息
        return ssoLogin(userDto);
    }

    @Override
    @Transient
    public ReturnT resetPwdSubmit(UserDto userDto) {
        // 自己公司的业务逻辑

        // userDto 为最新的用户信息
        return ssoLogin(userDto);
    }

    @Override
    @Transient
    public ReturnT ssoLogout(String sessionId) {
        SsoTokenLoginHelper.logout(sessionId);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT loginCheck(String sessionId, String unique) {
        //
        // 获取 token 中的 username
        String mobile = JWTSSOUtil.getUsername(sessionId); // 先获取出手机号
        // 根据手机好获取用户信息
        UserDto uuu = new UserDto(); //;userMapper.selectByMobile(AesUtil.encrypt(mobile));
        uuu.setPassword("123456"); // 假设123456 真实环境请查询数据库
        SsoUser ssoUser = SsoTokenLoginHelper.loginCheck(sessionId,unique,uuu.getPassword());
        if (ssoUser == null) {
            return new ReturnT<SsoUser>(ReturnT.FAIL_CODE, "sso not login.");
        }
        return new ReturnT<SsoUser>(ssoUser);

    }


}

    