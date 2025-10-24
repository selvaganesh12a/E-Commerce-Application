package com.ecommerce.order_service.service;

import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webclientBuilder;

    @Override
    public List<Order> fetchOrders() {
        System.out.println("Find All");
        return orderRepository.findAll();
    }

    @Override
    public void addOrder(Long id, Integer quantity) {
        HashMap stock = webclientBuilder.build()
                .get()
                .uri("http://product-service/product/stock/" + id)
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
        if (stock != null) {
            boolean avail = (boolean) stock.get("availability");
            if(avail && (Integer) stock.get("quantity") >= quantity){
                webclientBuilder.build()
                        .put()
                        .uri("http://product-service/product/stock/deduct/"+ id +"/"+ quantity)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
                Order order = new Order();
                order.setProductId(id);
                order.setQuantity(quantity);
                Long price = webclientBuilder.build()
                        .get()
                        .uri("http://product-service/product/fetch/price/" + id)
                        .retrieve()
                        .bodyToMono(Long.class)
                        .block();
                order.setTotalPrice((double) (quantity*price));
                order.setStatus("Confirmed");
                order.setOrderDate(LocalDateTime.now());
                saveToDB(order);
            }
        }
    }

    @Override
    public void saveToDB(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> fetchOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void cancelOrder(Long id) {
        Integer quantity = fetchOrderById(id).get().getQuantity();
        Long productId = fetchOrderById(id).get().getProductId();
        webclientBuilder.build()
                .put()
                .uri("http://product-service/product/stock/add/"+ productId +"/"+ quantity)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        orderRepository.deleteById(id);
    }
}
