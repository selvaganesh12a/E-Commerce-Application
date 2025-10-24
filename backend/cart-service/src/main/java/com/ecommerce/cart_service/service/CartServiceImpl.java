package com.ecommerce.cart_service.service;

import com.ecommerce.cart_service.dto.CartReq;
import com.ecommerce.cart_service.dto.ProductStockResponse;
import com.ecommerce.cart_service.entity.Cart;
import com.ecommerce.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Cart createCart(CartReq cartreq) {
        Cart cart = new Cart();
        cart.setUserId(cartreq.getUserId());
        cart.setProductId(cartreq.getProductId());
        cart.setQuantity(cartreq.getQuantity());
        ProductStockResponse stock = webClientBuilder.build()
                .get()
                .uri("http://product-service/product/stock/" + cartreq.getProductId())
                .retrieve()
                .bodyToMono(ProductStockResponse.class)
                .block();
        if (stock != null && stock.isAvailability() && stock.getQuantity() >= cartreq.getQuantity()){
            Double price = webClientBuilder.build()
                    .get()
                    .uri("http://product-service/product/fetch/price/" + cartreq.getProductId())
                    .retrieve()
                    .bodyToMono(Double.class)
                    .block();
            Double totalPrice = price * cartreq.getQuantity();
            cart.setTotalPrice(totalPrice);
            return cartRepository.save(cart);
        }else{
            throw new RuntimeException("Product not available or insufficient stock!");
        }
    }

    @Override
    public List<Cart> fetchCartByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }
}
