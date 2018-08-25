package com.training360.cafebabe.webshop.product.entities;

public class Product {
    private String productKey;
    private String name;
    private String url;
    private String manufacturer;
    private int price;

    public Product(String productKey, String name, String url, String manufacturer, int price) {
        if (price <= 0 || isEmpty(productKey, name, url, manufacturer)) {
            //TODO: hibadobálás?
        }
        this.productKey = productKey;
        this.name = name;
        this.url = url;
        this.manufacturer = manufacturer;
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

    private boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || str.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
