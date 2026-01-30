package org.example.productmicrosservice.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.shared.RegisterProductDto;
import org.example.productmicrosservice.appllication.dto.shared.CompleteRequestProductDto;
import org.example.productmicrosservice.appllication.dto.shared.ProductDTO;
import org.example.productmicrosservice.appllication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/new")
    public ResponseEntity<CompleteRequestProductDto> newProduct(@RequestBody @Valid RegisterProductDto dto) {
        var product = productService.registerNewProduct(dto);
        var productDto = new ProductDTO(product);
        return ResponseEntity.ok(new CompleteRequestProductDto(productDto));
    }
}
