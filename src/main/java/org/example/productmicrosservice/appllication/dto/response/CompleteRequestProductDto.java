package org.example.productmicrosservice.appllication.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.Instant;

public record CompleteRequestProductDto(
        @JsonAlias("creationMessage")
        String message,
        @JsonAlias("creationDate")
        Instant date,
        ProductDTO[] products
) {
    public CompleteRequestProductDto(ProductDTO product) {
        this("Complete request for: " + product.name(), Instant.now(), new ProductDTO[] {product});
    }
}
