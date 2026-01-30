package org.example.productmicrosservice.appllication.dto.command;

import org.example.productmicrosservice.appllication.dto.shared.OrderIdDto;

public record GetProductAndDecreaseStock(
        String correlationId,
        String id,
        Integer quantity
) {
    public GetProductAndDecreaseStock(OrderIdDto dto) {
        this(dto.correlationId(), dto.id(), dto.quantity());
    }
}
