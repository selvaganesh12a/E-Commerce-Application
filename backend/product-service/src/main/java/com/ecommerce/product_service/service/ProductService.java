package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public Product addProduct(Product product);

    public List<Product> fetchAllProducts();

    public Product fetchProductById(Long id);

    public void updateProduct(Long id, Product product);

    public void deleteProduct(Long id);

    public Map<String, Object> checkStock(Long id);

    public void deductStock(Long id, Long quantity);

    public Long fetchPriceOfProduct(Long id);

    public void addStock(Long id, Long quantity);
}
