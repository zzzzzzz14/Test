package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author zyn
 * @date 2022-06-29-9:42
 */
public class Rate {
    private String id;
    private String username;
    private String evaluate;
    private String evaluateValue;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date ratedate;



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

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluateValue() {
        return evaluateValue;
    }

    public void setEvaluateValue(String evaluateValue) {
        this.evaluateValue = evaluateValue;
    }

    public Date getRatedate() {
        return ratedate;
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", evaluateValue='" + evaluateValue + '\'' +
                ", ratedate=" + ratedate +
                '}';
    }

    public Rate(String id, String username, String evaluate, String evaluateValue, Date ratedate) {
        this.id = id;
        this.username = username;
        this.evaluate = evaluate;
        this.evaluateValue = evaluateValue;
        this.ratedate = ratedate;
    }

    public Rate() {
    }
}
