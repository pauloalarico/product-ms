package org.example.productmicrosservice.appllication.usecase.stock;

import org.example.productmicrosservice.appllication.dto.command.GetProductAndDecreaseStock;
import org.example.productmicrosservice.appllication.dto.result.ResultDecreaseStock;

public interface StockManagementUseCase {
    ResultDecreaseStock execute(GetProductAndDecreaseStock command);
}
