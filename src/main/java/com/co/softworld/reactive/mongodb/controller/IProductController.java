package com.co.softworld.reactive.mongodb.controller;

import com.co.softworld.reactive.mongodb.model.Product;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductController {
    Mono<ResponseEntity<Flux<Product>>> findAll();
    Mono<ResponseEntity<Product>> findById(String id);
    Mono<ResponseEntity<Product>> save(Product product);
    Mono<ResponseEntity<Void>> delete(Product product);
}
