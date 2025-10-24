package com.ecommerce.cart_service.service;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.entity.Cart;

public interface CartService {
    public Cart createCart(CartReq cart);
}
