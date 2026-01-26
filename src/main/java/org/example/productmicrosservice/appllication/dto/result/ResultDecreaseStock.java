package org.example.productmicrosservice.appllication.dto.result;

import java.math.BigDecimal;

public record ResultDecreaseStock(
        String correlationId,
        String productId,
        BigDecimal price,
        Integer stockQuantity
) {
}
