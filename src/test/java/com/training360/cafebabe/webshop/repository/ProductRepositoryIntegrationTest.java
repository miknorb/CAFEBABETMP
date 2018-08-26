package com.training360.cafebabe.webshop.repository;


import com.training360.cafebabe.webshop.product.entities.Product;
import com.training360.cafebabe.webshop.product.repository.PageableProductsResponse;
import com.training360.cafebabe.webshop.product.repository.ProductRepository;
import com.training360.cafebabe.webshop.product.repository.ProductResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("classpath:/clean.sql")
public class ProductRepositoryIntegrationTest {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testGetProductByUrl(){
        ProductResponse response = repo.getProductByUrl("corfu_coffee");

        assertEquals(true,response.getPresent());
        Product product = response.getProduct();

        assertEquals("Tchibo",product.getManufacturer());
        assertEquals(6900,product.getPrice());
        assertEquals("Corfu Coffee",product.getName());

    }
    @Test
    public void testListProductsDefault() {

        PageableProductsResponse list = repo.listProducts(0, 5);
        assertThat(list.getPage().size(), equalTo(5));
        list = repo.listProducts(11, 10);
        assertThat(list.getPage().size(), equalTo(10));
    }



}
