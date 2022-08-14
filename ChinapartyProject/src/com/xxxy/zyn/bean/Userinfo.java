package com.xxxy.zyn.bean;

import java.io.Serializable;

/**
 * @author zyn
 * @date 2022-06-08-9:04
 */
public class Userinfo implements Serializable {
    private String userinfo_id;
    private String uiName;

    public Userinfo(String userinfo_id, String uiName) {
        this.userinfo_id = userinfo_id;
        this.uiName = uiName;
    }

    public Userinfo() {
    }

    public String getUserinfo_id() {
        return userinfo_id;
    }

    public void setUserinfo_id(String userinfo_id) {
        this.userinfo_id = userinfo_id;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userinfo_id='" + userinfo_id + '\'' +
                ", uiName='" + uiName + '\'' +
                '}';
    }
}
