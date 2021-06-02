package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: 用户中心 用户类
 * @author: ljy
 * @date: 2021年06月02日 10:58
 * @email 15810874514@163.com
 */

public class SsoUser implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private String password;
    private String mobile;

    private Integer id;
    private String name;
    private String nickname;
    private  String  userInfo;
    private String unique ;
    private int expireMinute;
    private long expireFreshTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public int getExpireMinute() {
        return expireMinute;
    }

    public void setExpireMinute(int expireMinute) {
        this.expireMinute = expireMinute;
    }

    public long getExpireFreshTime() {
        return expireFreshTime;
    }

    public void setExpireFreshTime(long expireFreshTime) {
        this.expireFreshTime = expireFreshTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SsoUser ssoUser = (SsoUser) o;
        return expireMinute == ssoUser.expireMinute &&
                expireFreshTime == ssoUser.expireFreshTime &&
                Objects.equals(password, ssoUser.password) &&
                Objects.equals(mobile, ssoUser.mobile) &&
                Objects.equals(id, ssoUser.id) &&
                Objects.equals(name, ssoUser.name) &&
                Objects.equals(nickname, ssoUser.nickname) &&
                Objects.equals(userInfo, ssoUser.userInfo) &&
                Objects.equals(unique, ssoUser.unique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, mobile, id, name, nickname, userInfo, unique, expireMinute, expireFreshTime);
    }
}

    