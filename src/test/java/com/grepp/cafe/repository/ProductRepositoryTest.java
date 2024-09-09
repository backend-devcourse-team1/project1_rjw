package com.grepp.cafe.repository;

import com.grepp.cafe.domain.Category;
import com.grepp.cafe.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save() {
        Product product = productRepository.save(new Product(UUID.randomUUID(), "test3", Category.COFFEE_BEAN, 10000L, "test"));
    }

    @Test
    void findByCategory() {
        List<Product> byCategory = productRepository.findByCategory(Category.COFFEE_BEAN);
        for (Product product : byCategory) {
            System.out.println(product);
        }
    }

    @Test
    void findByProductName() {
        Optional<Product> test = productRepository.findByProductName("test");
        System.out.println(test.get());
    }

    @Test
    void findByProductNameIn() {
    }

    @Test
    void deleteAll() {
    }
}