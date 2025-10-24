package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.entity.Cart;
import com.ecommerce.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/get/{id}")
//    public List<Cart> getCartByUserId(){
//
//    }
}
