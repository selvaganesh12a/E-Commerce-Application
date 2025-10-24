package com.ecommerce.cart_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @SequenceGenerator(
            name = "generator",
            sequenceName = "sequencegenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequencegenerator"
    )
    private Long id;
    private Long userId;
    private Long productId;
    private Long quantity;
    private Double totalPrice;
}
