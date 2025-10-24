package com.ecommerce.product_service.service;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product fetchProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product productDB = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not Found"));

        if(Objects.nonNull(product.getName()) &&
                !"".equalsIgnoreCase(product.getName())){
            productDB.setName(product.getName());
        }

        if(Objects.nonNull(product.getDescription()) &&
                !"".equalsIgnoreCase(product.getDescription())){
            productDB.setDescription(product.getDescription());
        }

        if(product.getPrice() != null) productDB.setPrice(product.getPrice());

        if(product.getQuantity() != null) productDB.setQuantity(product.getQuantity());

        productRepository.save(productDB);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> checkStock(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        Map<String,Object> response = new HashMap<>();
        response.put("productId",product.getId());
        response.put("availability",product.getQuantity() > 0);
        response.put("quantity", product.getQuantity());
        return response;
    }

    @Override
    public void deductStock(Long id, Long quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        if(product.getQuantity() < quantity) throw new RuntimeException("Too large quantity... ");
        if(product.getQuantity() - quantity > 0) product.setQuantity(product.getQuantity() - quantity);
        else{
            deleteProduct(id);
            return;
        }
        productRepository.save(product);
    }

    @Override
    public Long fetchPriceOfProduct(Long id) {
        Product product = fetchProductById(id);
        return product.getPrice();
    }

    @Override
    public void addStock(Long id, Long quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);
    }
}
