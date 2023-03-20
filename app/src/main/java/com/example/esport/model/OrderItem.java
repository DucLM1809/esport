package com.example.esport.model;

import java.io.Serializable;

public class OrderItem implements Serializable{
    private String name, description, tag, image;
    private long price, quantity, id;
    private int cartQuantity;


    public OrderItem(String name, String description, String tag, String image, long price, long quantity, long id, int cartQuantity) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
        this.cartQuantity = cartQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}



