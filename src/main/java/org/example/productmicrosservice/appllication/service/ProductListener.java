package org.example.productmicrosservice.appllication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productmicrosservice.appllication.dto.order.OrderIdDto;
import org.example.productmicrosservice.appllication.dto.response.CompleteRequestProductDto;
import org.example.productmicrosservice.appllication.dto.response.ProductDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductListener {
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "orders-created.queue")
    public void getProductId(@Payload OrderIdDto dto){
        log.info("Product ID: {}", productService.getProductById(dto.id()));
        var product = productService.findProductById(dto.id());
        log.info("Product located: {}, with stock amount of {}", product.getProductId(), product.getStockQuantity());
        var productFound = productService.decreaseStockQuantity(String.valueOf(product.getProductId()), dto.quantity());
        var productDto =  new ProductDTO(product, dto.correlationId());
        rabbitTemplate.convertAndSend("products-decreased.ex","", new CompleteRequestProductDto(productDto));
        log.info("Queue sent for orders application for the product: {}, correlationId: {}", product.getProductId()
        , dto.correlationId());
    }

    @RabbitListener(queues = "order-canceled.queue")
    public void replaceProductStock(OrderIdDto dto) {
        productService.resetStockByQuantity(dto.id(), dto.quantity());
        log.info("Product replaced with {} more quantities.", dto.quantity());
    }
}
