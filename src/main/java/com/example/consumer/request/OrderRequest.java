package com.example.consumer.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private int userId;

    private List<ProductInCartRequest> productInCartRequests;
}
