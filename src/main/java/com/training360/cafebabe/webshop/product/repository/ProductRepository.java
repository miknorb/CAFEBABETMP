package com.training360.cafebabe.webshop.product.repository;

import com.training360.cafebabe.webshop.product.controller.ProductRequest;
import com.training360.cafebabe.webshop.product.entities.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private JdbcTemplate template;

    //ez allítja elő a product objektumot egy rekord sorából
    //MAD: id és isactive added
    private RowMapper<Product> mapper = (rs, i) -> {
        Long id = rs.getLong("id");
        String productKey = rs.getString("product_key");
        String name = rs.getString("name");
        String url = rs.getString("url");
        String manufacturer = rs.getString("manufacturer");
        int price = rs.getInt("price");
        boolean isActive = rs.getBoolean("isactive");
        return new Product(id, productKey, name, url, manufacturer, price, isActive);
    };

    public ProductRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    //ha talál adott URL-ű productot, visszaad egy response-t present=true értékkel és a talált producttal,
    //ellenkező esetben false / null értékkel
    //MAD: hozzáadva id field plusz isactive feltétel
    public ProductResponse getProductByUrl(String url) {
        ProductResponse response = new ProductResponse();
        try {
            response.setProduct(template.queryForObject(
                    "SELECT id, product_key, name, url, manufacturer, price, isactive FROM products WHERE isactive = 1 AND url = ?",
                    mapper,
                    url
            ));
            response.setPresent(true);
            return response;
        } catch (EmptyResultDataAccessException erdae) {
            return response;
        }
    }

    public List<Product> listAllProducts() {   //TODO: logolni ha eldöglik általánosságban is?
        return template.query("SELECT id, product_key, name, url, manufacturer, price, isactive FROM products WHERE isactive = 1", mapper);
    }

    public boolean deleteProduct(long id) {
        try {
            int modifiedRowNumber = template.update("UPDATE products SET isactive = 0 WHERE isactive = 1 AND id = ?", id);
            return modifiedRowNumber != 0;
        } catch (DataAccessException dae) {
            return false;
        }
    }

    public boolean updateProduct(ProductRequest productRequest, long id) {
        try {
            int modifiedRowNumber = template.update("UPDATE products SET product_key = ?, name = ?, url = ?, manufacturer = ?, price = ? WHERE isactive = 1 AND id = ?"
                    , productRequest.getProductKey()
                    , productRequest.getName()
                    , productRequest.getUrl()
                    , productRequest.getManufacturer()
                    , productRequest.getPrice()
                    , id);
            return modifiedRowNumber != 0;
        } catch (DataAccessException dae) {
            return false;

        }
    }

    public boolean createProduct(ProductRequest productRequest) {
        try {
            int modifiedRowNumber = template.update("INSERT INTO products (product_key, name, url, manufacturer, price) VALUES (?, ?, ?, ?, ?)"
                    , productRequest.getProductKey()
                    , productRequest.getName()
                    , productRequest.getUrl()
                    , productRequest.getManufacturer()
                    , productRequest.getPrice()
            );
            return modifiedRowNumber != 0;
        } catch (DataAccessException dae) {
            return false;

        }
    }

    private boolean isValueUniqe(String columnName, String value) {
        int rowCount = template.update("SELECT COUNT(id) FROM products WHERE " + columnName + " = ?", value);
        return rowCount == 0;
    }

    public boolean isNameUnique(String value) {
        return isValueUniqe("name", value);
    }

    public boolean isProductKeyUnique(String value) {
        return isValueUniqe("product_key", value);
    }

    public boolean isUrlUnique(String value) {
        return isValueUniqe("url", value);
    }
}