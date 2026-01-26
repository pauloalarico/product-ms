package org.example.productmicrosservice.utils;

import org.example.productmicrosservice.appllication.dto.command.GetProductAndDecreaseStock;
import org.example.productmicrosservice.appllication.dto.order.OrderIdDto;

public class CommandMapper {

    public static GetProductAndDecreaseStock toGetProdAndDecreaseStock(OrderIdDto dto) {
        return new GetProductAndDecreaseStock(dto);
    }
}
