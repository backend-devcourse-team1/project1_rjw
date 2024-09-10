package com.grepp.cafe.service;

import com.grepp.cafe.domain.Category;
import com.grepp.cafe.domain.Product;
import com.grepp.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 커피 메뉴 등록
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 커피 메뉴 수정
    @Transactional
    public Product updateProduct(UUID productId, Product updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(updatedProduct.getProductName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    // 커피 메뉴 삭제
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    // 전체 상품 조회
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductsByName(String name) {
        return productRepository.findByProductName(name);
    }

    public Optional<Product> getProductById(UUID productId) {
        return productRepository.findById(productId);
    }
}
