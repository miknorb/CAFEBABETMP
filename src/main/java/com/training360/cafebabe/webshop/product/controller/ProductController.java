package com.training360.cafebabe.webshop.product.controller;

import com.training360.cafebabe.webshop.product.repository.PageableProductsResponse;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import com.training360.cafebabe.webshop.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value= "/products", params = "url")
    public ProductResponse getProductByUrl(@RequestParam("url") String url) {
        return service.getProductByUrl(url);
    }

    @RequestMapping(value= "/products", params = {"start","size"})
    public PageableProductsResponse listProducts(@RequestParam("start") Integer start,
                                                 @RequestParam("size") Integer size) {
        if(size<1){
            throw new IllegalArgumentException("Size must be positive!");
        }
        if(start<0){
            throw new IllegalArgumentException("Start must be 0 or more than 0");
        }

        return service.listProduct(start, size);
    }
}
