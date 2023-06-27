package com.co.softworld.reactive.mongodb.controller;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements IProductController{

    @Autowired
    private IProductService productService;

    @Override
    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @Override
    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @Override
    @DeleteMapping
    public Mono<Void> delete(Product product) {
        return productService.delete(product);
    }
}