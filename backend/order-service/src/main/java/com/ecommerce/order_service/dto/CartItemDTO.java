package com.ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Long quantity;
    private Double totalPrice;
}
