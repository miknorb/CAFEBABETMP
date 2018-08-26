package com.training360.cafebabe.webshop.product.controller;

public class ProductRequest {

    private String productKey;
    private String name;
    private String url;
    private String manufacturer;
    private int price;

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductKey() {
        return productKey;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPrice() {
        return price;
    }
}
