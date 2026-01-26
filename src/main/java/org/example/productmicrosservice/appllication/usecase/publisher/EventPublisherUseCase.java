package org.example.productmicrosservice.appllication.usecase.publisher;

import org.example.productmicrosservice.appllication.dto.result.ResultDecreaseStock;

public interface EventPublisherUseCase {
    void publish (ResultDecreaseStock result);
}
