package com.training360.cafebabe.webshop.product.controller;

import com.training360.cafebabe.webshop.product.entities.Product;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import com.training360.cafebabe.webshop.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @RequestMapping("/products")
    public List<Product> listProducts(@RequestParam(required = false) Integer start,
                                      @RequestParam(required = false) Integer size) {
        if (start == null) {
            start = 0;
        }
        if (size == null) {
            size = 10;
        }
        return service.listProduct(start, size);
    }
}
