package org.example.productmicrosservice.appllication.usecase.stock;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.command.GetProductAndDecreaseStock;
import org.example.productmicrosservice.appllication.dto.result.ResultDecreaseStock;
import org.example.productmicrosservice.infra.repository.ProductRepository;
import org.example.productmicrosservice.model.exception.ProductNotFound;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StockManagementService implements StockManagementUseCase {

    private final ProductRepository repository;

    @Override
    public ResultDecreaseStock execute(final GetProductAndDecreaseStock command) {
        var product = repository.findByUid(UUID.fromString(command.id()))
                .orElseThrow(() -> new ProductNotFound("Product Id invalid or do not exist!"));
        if(command.quantity() > product.getStockQuantity()) {
            throw new RuntimeException("Order with more quantity to product than the stock");
        }
        repository.updateStockByQuantity(UUID.fromString(command.id()), command.quantity());
        return new ResultDecreaseStock(
                command.correlationId(),
                product.getProductId().toString(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }
}
