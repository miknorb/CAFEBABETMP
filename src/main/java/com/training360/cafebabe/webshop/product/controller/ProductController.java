package com.training360.cafebabe.webshop.product.controller;

import com.training360.cafebabe.webshop.product.entities.Product;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import com.training360.cafebabe.webshop.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/products", params = "url")
    public ProductResponse getProductByUrl(@RequestParam("url") String url) {
        return service.getProductByUrl(url);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> listAllProducts() {
        return service.listAllProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public boolean deleteProduct(@PathVariable long id){
        return service.deleteProduct(id);
    }
}
