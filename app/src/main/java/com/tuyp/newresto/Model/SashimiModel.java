package com.tuyp.newresto.Model;

public class SashimiModel {
    String name,descName,image;
    int price;

    public SashimiModel(String name, String descName, String image, int price) {
        this.name = name;
        this.descName = descName;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
