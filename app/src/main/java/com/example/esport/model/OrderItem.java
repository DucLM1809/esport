package com.example.esport.model;

import java.io.Serializable;


public class OrderItem implements Serializable{
    private String name, description, tag, image;
    private long price, quantity, id;
    private long cartQuantity;


    public OrderItem(String name, String description, String tag, String image, long price, long quantity, long id, long cartQuantity) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
        this.cartQuantity = cartQuantity;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
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

    public long getCartQuantity() {
        return cartQuantity;
    }


    public void setCartQuantity(long cartQuantity) {

        this.cartQuantity = cartQuantity;
    }
}

