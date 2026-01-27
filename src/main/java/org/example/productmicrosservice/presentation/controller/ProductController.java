package org.example.productmicrosservice.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.request.RegisterProductDto;
import org.example.productmicrosservice.appllication.dto.request.UpdateProductDto;
import org.example.productmicrosservice.appllication.dto.response.CompleteRequestProductDto;
import org.example.productmicrosservice.appllication.dto.response.ProductDTO;
import org.example.productmicrosservice.appllication.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductDTO(product));
    }

    @PostMapping("/new")
    public ResponseEntity<CompleteRequestProductDto> newProduct(@RequestBody @Valid RegisterProductDto dto) {
        var product = productService.registerNewProduct(dto);
        var productDto = new ProductDTO(product);
        return ResponseEntity.ok(new CompleteRequestProductDto(productDto));
    }

    @PutMapping("/{id}/decrease-stock")
    public ResponseEntity<CompleteRequestProductDto> decreaseStock(@PathVariable String id, @RequestBody @Valid UpdateProductDto dto) {
        var product = productService.decreaseStockQuantity(id, dto.stockQuantity());
        var productDto = new ProductDTO(product);
        return ResponseEntity.ok(new CompleteRequestProductDto(productDto));
    }

    @PutMapping("/{id}/reset-stock/{quantity}")
    public ResponseEntity<CompleteRequestProductDto> resetStock(@PathVariable String id, @PathVariable Integer quantity) {
        var product = productService.resetStockByQuantity(id, quantity);
        var productDto = new ProductDTO(product);
        return ResponseEntity.ok(new CompleteRequestProductDto(productDto));
    }
}
