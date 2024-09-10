package com.grepp.cafe.service;

import com.grepp.cafe.domain.Category;
import com.grepp.cafe.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void createProduct() {
        Product product1 = new Product(UUID.randomUUID(), "Coffee Bean1", Category.COFFEE_BEAN, 10000L, "High-quality coffee beans");
        Product product2 = new Product(UUID.randomUUID(), "Coffee Bean2", Category.COFFEE_BEAN, 10000L, "High-quality coffee beans");
        Product product3 = new Product(UUID.randomUUID(), "Coffee Bean3", Category.COFFEE_BEAN, 10000L, "High-quality coffee beans");
        productService.createProduct(product1);  // 제품 등록
        productService.createProduct(product2);  // 제품 등록
        productService.createProduct(product3);  // 제품 등록
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductsByCategory() {
        List<Product> coffeeProducts = productService.getProductsByCategory(Category.COFFEE_BEAN);
        for (Product coffeeProduct : coffeeProducts) {
            System.out.println(coffeeProduct);
        }
    }

    @Test
    void getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        for (Product allProduct : allProducts) {
            System.out.println(allProduct);
        }
    }
    @Test
    void getProductsByName() {
    }

    @Test
    void getProductById() {
    }
}