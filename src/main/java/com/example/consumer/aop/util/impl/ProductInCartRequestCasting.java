package com.example.consumer.aop.util.impl;

import com.ipi.framework.zsdk.log.LogMe;
import com.kido.framework.network.dto.ProductInCartRequest;
import com.vinhhoang.cmsservice.aop.util.CollectionObjectCasting;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ProductInCartRequestCasting extends CollectionObjectCasting<ProductInCartRequest> {

    public ProductInCartRequestCasting(String key, BufferedReader reader) {
        super(key, reader);
    }

    @Override
    public List<ProductInCartRequest> cast() {
        try {
            if (this.key.equals("list_product")){
                String line;
                List<ProductInCartRequest> response = new ArrayList<>();
                int productId = 0;
                int quantity = 0;
                while((line = reader.readLine()) != null || line.equals("]")){
                    if (productId == 0){
                        productId = this.getValueParameter(line, "id");
                    }
                    if(quantity == 0){
                        quantity = this.getValueParameter(line, "quantity");
                    }
                    if (productId > 0 && quantity > 0){
                        response.add(new ProductInCartRequest(productId, quantity));
                        productId = 0;
                        quantity = 0;
                    }
                }
                return response;
            }
        }catch (Exception ex){
            LogMe.exception(ex);
        }
        return new ArrayList<>();
    }
}
