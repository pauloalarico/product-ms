package org.example.productmicrosservice.appllication.usecase.stock;

import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.command.GetProductAndDecreaseStock;
import org.example.productmicrosservice.infra.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DecreaseStockOrderCanceledService implements DecreaseStockOrderCanceledUseCase {

    private final ProductRepository repository;

    @Override
    public void execute(final GetProductAndDecreaseStock command) {
        var product = repository.findByUid(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Product Id invalid or do not exist!"));
        if(command.quantity() > product.getStockQuantity()) {
            throw new RuntimeException("Order with more quantity to product than the stock");
        }
        repository.updateStockByQuantity(UUID.fromString(command.id()), command.quantity());
    }
}
