package org.example.productmicrosservice.appllication.usecase.publisher;

import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.result.ResultDecreaseStock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisherService implements EventPublisherUseCase {

    private final RabbitTemplate template;
    private static final String PRODUCT_DECREASE_EX = "products-decreased.ex";

    @Override
    public void publish(ResultDecreaseStock result) {
        template.convertAndSend(PRODUCT_DECREASE_EX, "", result);
    }
}
