package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户中心 用户类
 * @author: ljy
 * @date: 2021年06月02日 10:58
 * @email 15810874514@163.com
 */

public class SsoUser implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Integer id;
    private String name;
    private String nickname;
    private String mobile;
    private Integer gender;
    private String qq;
    private String email;
    private String password;
    private String headUrl;
    private Integer creatorId;
    private Date createTime;
    private Integer modifierId;
    private Date modifyTime;
    private Integer del;
    private Integer source;
    private Integer activeStatus;
    private  String  userInfo;
    private  int status;
    private String unique ;
    private int expireMinute;
    private long expireFreshTime;


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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}

    