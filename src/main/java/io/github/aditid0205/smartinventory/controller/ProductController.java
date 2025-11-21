package io.github.aditid0205.smartinventory.controller;

import io.github.aditid0205.smartinventory.entity.Product;
import io.github.aditid0205.smartinventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

@PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product productCreated = productService.createProduct(product);

    return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
}

@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
}

@GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
}
}
