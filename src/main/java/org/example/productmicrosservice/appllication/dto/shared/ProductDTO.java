package org.example.productmicrosservice.appllication.dto.shared;

import org.example.productmicrosservice.domain.entitie.Product;

import java.math.BigDecimal;

public record ProductDTO(
        String productId,
        String name,
        BigDecimal price,
        Integer stockQuantity
) {
    public ProductDTO(Product product) {
        this(product.getProductId().toString(), product.getName(),
                product.getPrice(), product.getStockQuantity());
    }
}
