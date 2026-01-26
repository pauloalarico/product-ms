package org.example.productmicrosservice.appllication.usecase.stock;

import org.example.productmicrosservice.appllication.dto.command.GetProductAndDecreaseStock;

public interface DecreaseStockOrderCanceledUseCase {
    void execute (GetProductAndDecreaseStock command);
}
