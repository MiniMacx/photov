package org.tustcs.photov.dto;

/**
 * Created by Airmacx on 2017/10/19.
 */
public class WechatUser {

    private String nickname;

    private String sex;

    private String headimgurl;

    private String openid;

    private String province;

    private int recId;

    private int userId;

    private String token;

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public WechatUser(String nickname, String sex, String headimgurl, String openid, String province, int recId, int userId, String token) {
        this.nickname = nickname;
        this.sex = sex;
        this.headimgurl = headimgurl;
        this.openid = openid;
        this.province = province;
        this.recId = recId;
        this.userId = userId;
        this.token = token;
    }

    public WechatUser() {
    }
}
