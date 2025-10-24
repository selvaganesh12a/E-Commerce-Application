package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.entity.Cart;
import com.ecommerce.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public String createCart(@RequestBody CartReq cart){
        Cart storedCart = cartService.createCart(cart);
        if(storedCart != null) return "Product Added to Cart Successfully";
        else return "Error when creating the cart";
    }

    @GetMapping("/fetch/{id}")
    public List<Cart> fetchCartByUserId(@PathVariable("id") Long id){
        return cartService.fetchCartByUserId(id);
    }
}
