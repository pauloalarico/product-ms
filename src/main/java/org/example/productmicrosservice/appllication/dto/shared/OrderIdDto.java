package org.example.productmicrosservice.appllication.dto.shared;

public record OrderIdDto(
        String correlationId,
        String id,
        Integer quantity
) {
}
