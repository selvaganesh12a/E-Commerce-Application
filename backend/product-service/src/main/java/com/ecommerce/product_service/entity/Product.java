package com.ecommerce.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name = "generator",
            sequenceName = "sequencegenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequencegenerator")
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long quantity;
}
