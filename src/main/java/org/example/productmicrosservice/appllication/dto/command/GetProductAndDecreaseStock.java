package org.example.productmicrosservice.appllication.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.productmicrosservice.appllication.dto.shared.OrderIdDto;

public record GetProductAndDecreaseStock(
        @NotBlank
        String correlationId,
        @NotBlank
        String id,
        @NotNull
        @Positive
        Integer quantity
) {
    public GetProductAndDecreaseStock(OrderIdDto dto) {
        this(dto.correlationId(), dto.id(), dto.quantity());
    }
}
