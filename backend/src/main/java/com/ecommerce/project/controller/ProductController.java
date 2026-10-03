package com.ecommerce.project.controller;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/public/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/public/products")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        String message = productService.deleteProduct(productId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product product,
            @PathVariable Long productId) {

        Product updatedProduct = productService.updateProduct(product, productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}