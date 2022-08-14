package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author zyn
 * @date 2022-06-28-23:25
 */
public class Orderhistory {
    private String id;
    private String username;
    private String info;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date ordertime;
    private String money;
    private String evaluate;
    private float evaluateValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Orderhistory() {
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "Orderhistory{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", info='" + info + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", money='" + money + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", evaluateValue=" + evaluateValue +
                '}';
    }

    public Orderhistory(String id, String username, String info, Date ordertime, String money, String evaluate, float evaluateValue) {
        this.id = id;
        this.username = username;
        this.info = info;
        this.ordertime = ordertime;
        this.money = money;
        this.evaluate = evaluate;
        this.evaluateValue = evaluateValue;
    }

    public float getEvaluateValue() {
        return evaluateValue;
    }

    public void setEvaluateValue(float evaluateValue) {
        this.evaluateValue = evaluateValue;
    }
}
