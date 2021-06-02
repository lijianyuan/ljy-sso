package com.ljy.sso.server.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: 用户中心 用户类
 * @author: ljy
 * @date: 2021年06月02日 10:58
 * @email 15810874514@163.com
 */

public class UserDto implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private Integer id;
    private String name;
    private String nickname;
    private String mobile;
    private String password;
    private String unique;
    //其余字段自行完善


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }
}

    