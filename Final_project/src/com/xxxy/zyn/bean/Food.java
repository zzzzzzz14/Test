package com.xxxy.zyn.bean;

/**
 * @author zyn
 * @date 2022-06-28-10:46
 */
public class Food {
    private String id ;
    private String fname;
    private float price;
    private String info;
    private String menu;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Food(String id, String fname, float price, String info, String menu) {
        this.id = id;
        this.fname = fname;
        this.price = price;
        this.info = info;
        this.menu = menu;
    }

    public Food() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", menu='" + menu + '\'' +
                '}';
    }
}
