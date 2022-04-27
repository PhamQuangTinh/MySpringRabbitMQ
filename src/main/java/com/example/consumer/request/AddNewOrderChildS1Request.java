package com.example.consumer.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddNewOrderChildS1Request {
    private int user_agency_id;
    private List<Product> list_product;

    @Getter
    @Setter
    public static class Product{
        private int id;
        private int quantity;
    }
}
