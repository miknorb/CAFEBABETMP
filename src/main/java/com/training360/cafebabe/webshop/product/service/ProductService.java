package com.training360.cafebabe.webshop.product.service;

import com.training360.cafebabe.webshop.product.repository.PageableProductsResponse;
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

    public PageableProductsResponse listProduct(int start, int size) {
        PageableProductsResponse response= repository.listProducts(start, size);
        int numberOfProducts=repository.numberOfProducts();
        if(numberOfProducts%size!=0){
            response.setTotalNumberOfPages((numberOfProducts/size)+1);
        }else{
            response.setTotalNumberOfPages(numberOfProducts/size);
        }

        response.setCurrentPage(start/size+1);

        return response;
    }


}
