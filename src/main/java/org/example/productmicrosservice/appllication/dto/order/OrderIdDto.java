package org.example.productmicrosservice.appllication.dto.order;

public record OrderIdDto(
        String correlationId,
        String id,
        Integer quantity
) {
}
