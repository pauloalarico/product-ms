package org.example.productmicrosservice.domain.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.productmicrosservice.appllication.dto.shared.RegisterProductDto;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Table(name = "ms_products")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_idnt_pdt")
    private UUID productId;
    @Column(name = "nm_product")
    private String name;
    @Column(name = "vl_price")
    private BigDecimal price;
    @Column(name = "stock_qty")
    @Positive
    private Integer stockQuantity;

    public Product(RegisterProductDto dto) {
        this.name = dto.name();
        if((dto.price()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Price got to be greater than zero");
        }
        this.price = dto.price();
        if (dto.stockQuantity() < 0) {throw new RuntimeException("Stock Quantity got to be greater than 0");}
        this.stockQuantity = dto.stockQuantity();
    }
}
