package com.ecommerce.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartReq {
    private Long userId;
    private Long productId;
    private Long quantity;
}
