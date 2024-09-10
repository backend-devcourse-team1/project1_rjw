package com.grepp.cafe.repository;

import com.grepp.cafe.domain.Category;
import com.grepp.cafe.domain.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory(Category category);
    Optional<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.productName IN :productNames")
    List<Product> findByProductNameIn(@Param("productNames") List<String> productNames);

    void deleteAll();
}
