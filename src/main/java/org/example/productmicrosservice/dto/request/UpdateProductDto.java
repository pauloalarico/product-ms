package org.example.productmicrosservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateProductDto(
        @NotNull
        Integer stockQuantity
) {
}
