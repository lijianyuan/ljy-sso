package com.example.demo002.controller;

import annotation.UserLoginToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/test")
    @ResponseBody
    @UserLoginToken
    public String  login(String  name , String mobile , String password , String userInfo , String unique ) {
      return "";
    }






}

    