package org.example.productmicrosservice.infra.messaging.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productmicrosservice.appllication.dto.order.OrderIdDto;
import org.example.productmicrosservice.appllication.usecase.publisher.EventPublisherUseCase;
import org.example.productmicrosservice.appllication.usecase.stock.DecreaseStockOrderCanceledUseCase;
import org.example.productmicrosservice.appllication.usecase.stock.StockManagementUseCase;
import org.example.productmicrosservice.utils.CommandMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {
    private final StockManagementUseCase stockManagementUseCase;
    private final DecreaseStockOrderCanceledUseCase decreaseStock;
    private final EventPublisherUseCase publisher;

    @RabbitListener(queues = "orders-created.queue")
    public void getProductId(@Payload OrderIdDto dto) {
        var command = CommandMapper.toGetProdAndDecreaseStock(dto);
        var result = stockManagementUseCase.execute(command);
        publisher.publish(result);
    }

    @RabbitListener(queues = "order-canceled.queue")
    public void replaceProductStock(OrderIdDto dto) {
        var command = CommandMapper.toGetProdAndDecreaseStock(dto);
        decreaseStock.execute(command);
    }
}
