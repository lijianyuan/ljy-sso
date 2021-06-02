package com.ljy.sso.server.service;


/*
 *@Description: LoginService
 *@Param: 
 *@return: 
 *@Author: ljy
 *@Date: 2021/6/2 16:31
 *@email: 15810874514@163.com
 *
 **/

import com.alibaba.fastjson.JSONObject;
import com.ljy.sso.server.dto.UserDto;
import entity.SsoUser;
import util.ReturnT;

public interface LoginService {



    ReturnT register(UserDto userDto);
    ReturnT login(UserDto userDto);
    ReturnT ssoLogin(UserDto userDto);
    ReturnT forgetPwdSubmit(UserDto userDto);
    ReturnT resetPwdSubmit(UserDto userDto);
    ReturnT ssoLogout(String sessionId);
    ReturnT loginCheck(String sessionId,String unique);
}
