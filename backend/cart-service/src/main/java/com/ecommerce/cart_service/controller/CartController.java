package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.entity.Cart;
import com.ecommerce.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/fetch/{userId}")
    public List<Cart> fetchCartByUserId(@PathVariable("userId") Long id){
        return cartService.fetchCartByUserId(id);
    }

    @PutMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") Long id,@RequestBody Map<String, Integer> requestBody){
        Long quantity = Long.valueOf(requestBody.get("quantity"));
        cartService.updateQuantity(id,quantity);
        return "Cart Updated Successfully";
    }

    @DeleteMapping("/remove/{productId}")
    public String removeProduct(@PathVariable("productId") Long id){
        cartService.removeProduct(id);
        return "Product Removed from Cart Successfully";
    }
}
