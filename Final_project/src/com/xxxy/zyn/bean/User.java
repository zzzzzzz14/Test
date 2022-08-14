package com.xxxy.zyn.bean;

/**
 * @author zyn
 * @date 2022-06-03-15:58
 */
public class User {
    private String username;
    private String userpass;
    private String phone;
    private float money;

    public User(String username, String userpass, String phone, float money) {
        this.username = username;
        this.userpass = userpass;
        this.phone = phone;
        this.money = money;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                '}';
    }
}
