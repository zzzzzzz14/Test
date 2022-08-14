package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zyn
 * @date 2022-06-07-17:42
 */
public class Logins implements Serializable {
    private String logins_id;
    private String userinfo_id;
    private String roles_id;
    private String loginsName;
    private String loginsPwd;
    private String loginsFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginsCDate;
    private String rolesName;
    private String uiName;

    public Logins() {
    }

    public Logins(String logins_id, String userinfo_id, String roles_id, String loginsName, String loginsPwd, String loginsFlag, Date loginsCDate, String rolesName, String uiName) {
        this.logins_id = logins_id;
        this.userinfo_id = userinfo_id;
        this.roles_id = roles_id;
        this.loginsName = loginsName;
        this.loginsPwd = loginsPwd;
        this.loginsFlag = loginsFlag;
        this.loginsCDate = loginsCDate;
        this.rolesName = rolesName;
        this.uiName = uiName;
    }

    public String getLogins_id() {
        return logins_id;
    }

    public void setLogins_id(String logins_id) {
        this.logins_id = logins_id;
    }

    public String getUserinfo_id() {
        return userinfo_id;
    }

    public void setUserinfo_id(String userinfo_id) {
        this.userinfo_id = userinfo_id;
    }

    public String getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(String roles_id) {
        this.roles_id = roles_id;
    }

    public String getLoginsName() {
        return loginsName;
    }

    public void setLoginsName(String loginsName) {
        this.loginsName = loginsName;
    }

    public String getLoginsPwd() {
        return loginsPwd;
    }

    public void setLoginsPwd(String loginsPwd) {
        this.loginsPwd = loginsPwd;
    }

    public String getLoginsFlag() {
        return loginsFlag;
    }

    public void setLoginsFlag(String loginsFlag) {
        this.loginsFlag = loginsFlag;
    }

    public Date getLoginsCDate() {
        return loginsCDate;
    }

    public void setLoginsCDate(Date loginsCDate) {
        this.loginsCDate = loginsCDate;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    @Override
    public String toString() {
        return "Logins{" +
                "logins_id='" + logins_id + '\'' +
                ", userinfo_id='" + userinfo_id + '\'' +
                ", roles_id='" + roles_id + '\'' +
                ", loginsName='" + loginsName + '\'' +
                ", loginsPwd='" + loginsPwd + '\'' +
                ", loginsFlag='" + loginsFlag + '\'' +
                ", loginsCDate=" + loginsCDate +
                ", rolesName='" + rolesName + '\'' +
                ", uiName='" + uiName + '\'' +
                '}';
    }
}
