package com.ljy.sso.server.controller;

import annotation.UserLoginToken;
import com.alibaba.fastjson.JSONObject;
import entity.SsoUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.SsoLoginStore;
import store.SsoSessionIdHelper;
import store.SsoTokenLoginHelper;
import util.ReturnT;

/**
 * @Description: login 认证 sso api
 * @author: ljy
 * @date: 2021年06月02日 13:19
 * @email 15810874514@163.com
 */
@Controller
@RequestMapping("/api")
public class AppController {


    /**
     * Login
     * @param name
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    @UserLoginToken
    public ReturnT<String> login(String  name , String mobile , String password , String userInfo , String unique ) {
        // 1、make sso user
        SsoUser u = new SsoUser();
        u.setName(name);
        u.setMobile(mobile);
        u.setPassword(password);
        u.setUserInfo(userInfo);
        u.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        u.setExpireFreshTime(System.currentTimeMillis());
        u.setUnique(unique);
        String sessionId = SsoSessionIdHelper.makeTokenId(u.getMobile(),u.getPassword());
        SsoTokenLoginHelper.login(sessionId, u);
        return new ReturnT<String>(sessionId);
    }


    /**
     * Logout
     *
     * @param sessionId
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ReturnT<String> logout(String sessionId) {
        // logout, remove storeKey
        SsoTokenLoginHelper.logout(sessionId);
        return ReturnT.SUCCESS;
    }

    /**
     * logincheck
     *
     * @param sessionId
     * @return
     */
    @RequestMapping("/logincheck")
    @ResponseBody
    public ReturnT<SsoUser> logincheck(String sessionId,String unique) {
        // logout
        SsoUser ssoUser = SsoTokenLoginHelper.loginCheck(sessionId,unique);
        if (ssoUser == null) {
            return new ReturnT<SsoUser>(ReturnT.FAIL_CODE, "sso not login.");
        }
        return new ReturnT<SsoUser>(ssoUser);
    }



    /**
     * Login
     * @param
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ReturnT<String> updateUserInfo(String userInfo , String sessionId) {
        // 1、make sso user
        SsoUser u =SsoTokenLoginHelper.getUser(sessionId);
        String newMobile = JSONObject.parseObject(userInfo).getString("mobile");
        String mobile = JSONObject.parseObject(u.getUserInfo()).getString("mobile");
        String newPassword = JSONObject.parseObject(userInfo).getString("password");
        String password = JSONObject.parseObject(u.getUserInfo()).getString("password");
        u.setUserInfo(userInfo);
        u.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        u.setExpireFreshTime(System.currentTimeMillis());

        if (!newMobile.equals(mobile)||!newPassword.equals(password))
        {
            String sessionId1 = SsoSessionIdHelper.makeTokenId(newMobile,newPassword);
            u.setMobile(newMobile);
            u.setPassword(newPassword);
            SsoTokenLoginHelper.login(sessionId1, u);
            return new ReturnT<String>(sessionId1);
        }else
        {
            SsoTokenLoginHelper.login(sessionId, u);
            return new ReturnT<String>("");
        }
    }


}

    