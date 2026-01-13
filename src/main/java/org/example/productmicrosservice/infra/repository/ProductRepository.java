package org.example.productmicrosservice.infra.repository;

import org.example.productmicrosservice.entitie.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select p from Product p where p.productId = :id")
    Optional<Product> findByUid(@Param("id") UUID id);

    @Modifying
    @Query("""
           update Product p
           set p.stockQuantity = p.stockQuantity - :quantity
           where p.productId = :id
           and p.stockQuantity >= :quantity
           \s""")
    void updateStockByQuantity(@Param("id") UUID id, @Param("quantity") Integer quantity);
}
