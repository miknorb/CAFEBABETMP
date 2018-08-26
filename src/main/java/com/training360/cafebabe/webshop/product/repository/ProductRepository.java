package com.training360.cafebabe.webshop.product.repository;

import com.training360.cafebabe.webshop.product.entities.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private JdbcTemplate template;

    //ez allítja elő a product objektumot egy rekord sorából
    private RowMapper<Product> mapper = (rs, i) -> {
        String productKey = rs.getString("product_key");
        String name = rs.getString("name");
        String url = rs.getString("url");
        String manufacturer = rs.getString("manufacturer");
        int price = rs.getInt("price");
        return new Product(productKey, name, url, manufacturer, price);
    };

    public ProductRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    //ha talál adott URL-ű productot, visszaad egy response-t present=true értékkel és a talált producttal,
    //ellenkező esetben false / null értékkel
    public ProductResponse getProductByUrl(String url) {
        ProductResponse response = new ProductResponse();
        try {
            response.setProduct(template.queryForObject(
                    "SELECT product_key, name, url, manufacturer, price FROM products WHERE url = ?",
                    mapper,
                    url
            ));
            response.setPresent(true);
            return response;
        } catch (EmptyResultDataAccessException erdae) {
            return response;
        }
    }

    private class MapperWithoutUrl implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            String productKey = rs.getString("product_key");
            String name = rs.getString("name");
            String manufacturer = rs.getString("manufacturer");
            int price = rs.getInt("price");
            return new Product(productKey, name, manufacturer, price);
        }
    }

    public List<Product> listProducts(int start, int size) {
        return template.query("select product_key, name, url, manufacturer, price from products order by name, manufacturer limit ?, ?",
                mapper, start, size);
    }
}
