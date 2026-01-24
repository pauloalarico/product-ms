package org.example.productmicrosservice.appllication.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateProductDto(
        @NotNull
        Integer stockQuantity
) {
}
