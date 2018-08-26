package com.training360.cafebabe.webshop.product.service;

import com.training360.cafebabe.webshop.product.controller.ProductRequest;
import com.training360.cafebabe.webshop.product.entities.Product;
import com.training360.cafebabe.webshop.product.repository.ProductRepository;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse getProductByUrl(String url) {
        return repository.getProductByUrl(url);
    }

    public List<Product> listAllProducts() {
        return repository.listAllProducts();
    }

    public boolean deleteProduct(long id) {
        return repository.deleteProduct(id);
    }

    public boolean updateProduct(ProductRequest productRequest, long id) {
        if (!productRequestValidator(productRequest)) {
            return false;
        }
        return repository.updateProduct(productRequest, id);
    }

    public boolean createProduct(ProductRequest productRequest) {
        if (!productRequestValidator(productRequest)) {
            return false;
        }
        return repository.createProduct(productRequest);
    }

    private boolean productRequestValidator(ProductRequest productRequest) {
        if (productRequest.getProductKey() == null
                || productRequest.getProductKey() == ""
                || productRequest.getProductKey().length() != 8
                || repository.isProductKeyUnique(productRequest.getProductKey())) {
            return false;
        }
        if (productRequest.getName() == null
                || productRequest.getName() == ""
                || repository.isNameUnique(productRequest.getName())) {
            return false;
        }
        if (productRequest.getUrl() == null
                || productRequest.getUrl() == ""
                || repository.isUrlUnique(productRequest.getUrl())) {
            return false;
        }
        if (productRequest.getManufacturer() == null
                || productRequest.getManufacturer() == "") {
            return false;
        }
        if (productRequest.getPrice() <= 0) {
            return false;
        }
        return true;
    }
}
