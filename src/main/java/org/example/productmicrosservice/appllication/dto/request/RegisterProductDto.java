package org.example.productmicrosservice.appllication.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RegisterProductDto(
        @NotBlank
        @JsonAlias("productName")
        String name,
        @NotNull
        @Positive
        BigDecimal price,
        @NotNull
        @Positive
        Integer stockQuantity
) {
}
