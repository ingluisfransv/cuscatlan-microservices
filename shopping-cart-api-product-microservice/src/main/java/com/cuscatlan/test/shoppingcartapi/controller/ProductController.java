package com.cuscatlan.test.shoppingcartapi.controller;
import com.cuscatlan.test.shoppingcartapi.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String EXTERNAL_API_URL = "https://fakestoreapi.com/products";

    private final RestTemplate restTemplate;

    public ProductController() {
        this.restTemplate = new RestTemplate();
    }
    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    @GetMapping("/getAll")
    public ResponseEntity<Product[]> getAllProducts() {
        Product[] products = restTemplate.getForObject(EXTERNAL_API_URL, Product[].class);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = restTemplate.getForObject(EXTERNAL_API_URL + "/" + id, Product.class);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
