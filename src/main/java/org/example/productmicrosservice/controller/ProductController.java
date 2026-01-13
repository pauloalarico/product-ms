package org.example.productmicrosservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.dto.request.RegisterProductDto;
import org.example.productmicrosservice.dto.request.UpdateProductDto;
import org.example.productmicrosservice.dto.response.CompleteRequestProductDto;
import org.example.productmicrosservice.dto.response.ProductDTO;
import org.example.productmicrosservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct (@PathVariable String id) {
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
}
