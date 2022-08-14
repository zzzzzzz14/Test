package com.xxxy.zyn.bean;

/**
 * @author zyn
 * @date 2022-06-28-17:43
 */
public class Orders {
    private String id;
    private String username;
    private String fname;
    private int num;
    private int price;
    private int total_prices;

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", total_prices=" + total_prices +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Orders(String id, String username, String fname, int num, int price, int total_prices) {
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.num = num;
        this.price = price;
        this.total_prices = total_prices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_prices() {
        return total_prices;
    }

    public void setTotal_prices(int total_prices) {
        this.total_prices = total_prices;
    }

    public Orders() {
    }
}
