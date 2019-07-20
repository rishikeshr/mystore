package com.shopping.mystore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<String> productList;

    public Cart() {
        productList = new ArrayList<String>();
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }
}
