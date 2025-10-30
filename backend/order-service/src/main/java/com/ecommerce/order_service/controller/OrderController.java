package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{id}/{quantity}")
    public String addOrder(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity){
        orderService.addOrder(id,quantity);
        return "Ordered Successfully";
    }

    @GetMapping("/fetch")
    public List<Order> fetchOrders(){
        return orderService.fetchOrders();
    }

    @GetMapping("/fetch/{id}")
    public Optional<Order> fetchOrderById(@PathVariable("id") Long id){
        return orderService.fetchOrderById(id);
    }

    @DeleteMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long id){
        orderService.cancelOrder(id);
        return "order cancelled successfully";
    }

    @PostMapping("/checkout/{userId}")
    public String checkout(@PathVariable("userId") Long id){
        return orderService.checkout(id);
    }
}
