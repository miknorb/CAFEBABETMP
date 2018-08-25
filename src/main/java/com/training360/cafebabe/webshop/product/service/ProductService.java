package com.training360.cafebabe.webshop.product.service;

import com.training360.cafebabe.webshop.product.repository.ProductRepository;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse getProductByUrl(String url) {
        return repository.getProductByUrl(url);
    }
}
