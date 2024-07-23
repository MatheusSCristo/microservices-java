package com.matheus.product_service.service;

import com.matheus.product_service.dto.ProductRequest;
import com.matheus.product_service.dto.ProductResponse;
import com.matheus.product_service.model.Product;
import com.matheus.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(item -> ProductResponse.builder().name(item.getName())
                        .price(item.getPrice())
                        .description(item.getDescription())
                        .id(item.getId())
                        .build()).toList();
    }

}
