package com.zyn.bean;

/**
 * @author zyn
 * @date 2022-05-25-18:46
 */
public class Goods {
    private  int id ;
    private String username;
    private String Goodname;
    private int num;
    private int price;
    private int total_prices;

    public Goods() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodname() {
        return Goodname;
    }

    public void setGoodname(String goodname) {
        Goodname = goodname;
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

    public Goods(int id, String username, String goodname, int num, int price, int total_prices) {
        this.id = id;
        this.username = username;
        Goodname = goodname;
        this.num = num;
        this.price = price;
        this.total_prices = total_prices;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", Goodname='" + Goodname + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", total_prices=" + total_prices +
                '}';
    }
}
