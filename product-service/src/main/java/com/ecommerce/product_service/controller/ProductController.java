package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/fetch")
    public List<Product> fetchAllProducts(){
        return productService.fetchAllProducts();
    }

    @GetMapping("/fetch/{id}")
    public String fetchProductById(@PathVariable("id") Long id){
        Product product = productService.fetchProductById(id);
        if(product == null) return "Product Not Available";
        else{
            JSONObject obj = new JSONObject(product);
            return obj.toString();
        }
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        productService.updateProduct(id,product);
        return "Product Updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "Product is deleted Successfully";
    }

    @GetMapping("/stock/{id}")
    public Map<String,Object> checkStock(@PathVariable("id") Long id){
        return productService.checkStock(id);
    }

    @PutMapping("/stock/deduct/{id}/{quantity}")
    public void deductStock(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity){
        System.out.println("deduct : id : "+id + " quantity : " + quantity);
        productService.deductStock(id,quantity);
    }

    @PutMapping("/stock/add/{id}/{quantity}")
    public void addStock(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity){
        System.out.println("Add Stock : id : "+id + " quantity : " + quantity);
        productService.addStock (id,quantity);
    }

    @GetMapping("/fetch/price/{id}")
    public Long fetchPriceOfProduct(@PathVariable("id") Long id){
        return productService.fetchPriceOfProduct(id);
    }
}
