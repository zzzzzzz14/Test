package com.xxxy.zhangyaning.model;

import java.io.Serializable;

/**
 * @author zyn
 * @date 2022-06-23-17:40
 */
public class Book implements Serializable {
    private String name;
    private String author;
    private String press;
    private float price;
    private float num;

    public Book() {
    }

    public Book(String name, String author, String press, float price, float num) {
        this.name = name;
        this.author = author;
        this.press = press;
        this.price = price;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }
}
