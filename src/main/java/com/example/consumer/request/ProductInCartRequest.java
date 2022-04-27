package com.example.consumer.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInCartRequest {
    private int productId;

    private int quantity;

    public ProductInCartRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
