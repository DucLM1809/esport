package com.example.esport.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private long id;
    private String name;
    private String tag;
    private long price;
    private long quantity;
    private String image;
    private long cartQuantity;

    public OrderItem(String name, String tag, long price, long quantity, String image, long cartQuantity) {
        this.name = name;
        this.tag = tag;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.cartQuantity = cartQuantity;
    }

    public OrderItem(long id, String name, String tag, long price, long quantity, String image, long cartQuantity) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.cartQuantity = cartQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(long cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}

