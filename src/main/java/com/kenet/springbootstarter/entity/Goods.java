package com.kenet.springbootstarter.entity;

import java.io.Serializable;

public class Goods implements Serializable{
    private Long id;
    private String title;
    private String price;
    private String image;
    private String brand;

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
