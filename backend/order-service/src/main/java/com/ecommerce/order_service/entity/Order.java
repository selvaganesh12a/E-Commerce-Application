package com.ecommerce.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "generator",
            sequenceName = "sequencegenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequencegenerator")
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private String status;
    private LocalDateTime orderDate;
}
