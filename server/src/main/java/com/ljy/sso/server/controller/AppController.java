package com.ljy.sso.server.controller;

import annotation.PassToken;
import annotation.UserLoginToken;
import com.alibaba.fastjson.JSONObject;
import com.ljy.sso.server.dto.UserDto;
import com.ljy.sso.server.service.LoginService;
import entity.SsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import store.SsoLoginStore;
import store.SsoSessionIdHelper;
import store.SsoTokenLoginHelper;
import util.ReturnT;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: login 认证 sso api
 * @author: ljy
 * @date: 2021年06月02日 13:19
 * @email 15810874514@163.com
 */
@Controller
@RestController
@RequestMapping("/api")
public class AppController {



    @Autowired
    private LoginService loginService;


    @PassToken
    @PostMapping("/register")
    public ReturnT register(@RequestBody UserDto registerDTO) {
        return loginService.register(registerDTO);
    }


    @PassToken
    @PostMapping("/login")
    public ReturnT login(@RequestBody UserDto loginDTO) {
        return loginService.login(loginDTO);
    }

    @PassToken
    @PostMapping("/forgetPwdSubmit")
    public ReturnT forgetPwdSubmit(@RequestBody UserDto resetPasswordDTO) {
        return loginService.forgetPwdSubmit(resetPasswordDTO);
    }


    @PassToken
    @PostMapping("/resetPwdSubmit")
    public ReturnT resetPwdSubmit(@RequestBody UserDto resetPasswordDTO) {
        return loginService.resetPwdSubmit(resetPasswordDTO);
    }

    @UserLoginToken
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ReturnT logout(String sessionId) {
        return loginService.ssoLogout( sessionId);
    }

    @RequestMapping("/loginCheck")
    @ResponseBody
    public ReturnT<SsoUser> loginCheck(String sessionId,String unique) {
        return loginService.loginCheck(sessionId,unique);
    }


    @RequestMapping("/getUser")
    @ResponseBody
    public ReturnT<SsoUser> getUser(String sessionId,String unique) {
        return loginService.loginCheck(sessionId,unique);
    }


}

    