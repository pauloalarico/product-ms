package org.example.productmicrosservice.appllication.service;

import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.shared.RegisterProductDto;
import org.example.productmicrosservice.domain.entitie.Product;
import org.example.productmicrosservice.infra.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product registerNewProduct(RegisterProductDto dto) {
        var product = new Product(dto);
        return repository.save(product);
    }
}
