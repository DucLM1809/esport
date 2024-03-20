package com.example.esport.model.orderRequest;

public class OrderItemRequest {
    private long productId, quantity, price;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public OrderItemRequest(long productId, long quantity, long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;


    }
}
