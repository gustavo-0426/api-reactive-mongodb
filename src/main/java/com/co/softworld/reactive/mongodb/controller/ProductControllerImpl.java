package com.co.softworld.reactive.mongodb.controller;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductControllerImpl implements IProductController{

    @Autowired
    private IProductService productService;

    @Override
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productService.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return productService.save(product);
    }
}
