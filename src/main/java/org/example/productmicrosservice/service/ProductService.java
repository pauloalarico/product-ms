package org.example.productmicrosservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.dto.request.RegisterProductDto;
import org.example.productmicrosservice.entitie.Product;
import org.example.productmicrosservice.infra.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public Product getProductById(String id) {
       return findProductById(id);
    }

    public Product registerNewProduct(RegisterProductDto dto) {
        var product = new Product(dto);
        return repository.save(product);
    }

    @Transactional
    public Product decreaseStockQuantity(String id, Integer quantity) {
        var product = findProductById(id);
        if(quantity > product.getStockQuantity()) {
            throw new RuntimeException("Order with more quantity to product than the stock");
        }
        repository.updateStockByQuantity(UUID.fromString(id), quantity);
        return product;
    }

    private Product findProductById(String id) {
        return repository.findByUid(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Product Id invalid or do not exist!"));
    }
}
