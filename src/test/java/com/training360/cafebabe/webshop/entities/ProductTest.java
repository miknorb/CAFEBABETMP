package com.training360.cafebabe.webshop.entities;

import com.training360.cafebabe.webshop.product.entities.Product;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductTest {
    @Test
    public void TestConstructor(){
        Product product = new Product("abc","tükör","tukor","valami",500);

        assertThat(product.getProductKey(),equalTo("abc"));
        assertThat(product.getName(),equalTo("tükör"));
        assertThat(product.getUrl(),equalTo("tukor"));
        assertThat(product.getManufacturer(),equalTo("valami"));
        assertThat(product.getPrice(),equalTo(500));
    }
}
