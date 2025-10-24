package com.ecommerce.cart_service.service;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.entity.Cart;

import java.util.List;

public interface CartService {
    public Cart createCart(CartReq cart);

    public List<Cart> fetchCartByUserId(Long id);
}
