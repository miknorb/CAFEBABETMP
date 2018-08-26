package com.training360.cafebabe.webshop.product.repository;

import com.training360.cafebabe.webshop.product.entities.Product;

import java.util.List;

public class PageableProductsResopnse {

    private int totalNumberOfPages;

    private int pageNumber;

    private List<Product> page;

    public PageableProductsResopnse(List<Product> page) {
        this.page = page;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
