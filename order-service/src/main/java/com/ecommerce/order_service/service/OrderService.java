package com.ecommerce.order_service.service;

import com.ecommerce.order_service.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> fetchOrders();

    public void addOrder(Long id,Integer quantity);

    public void saveToDB(Order order);

    public Optional<Order> fetchOrderById(Long id);

    public void cancelOrder(Long id);
}
