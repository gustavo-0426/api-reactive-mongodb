package com.co.softworld.reactive.mongodb.service;

import com.co.softworld.reactive.mongodb.model.Product;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Mono<ResponseEntity<Flux<Product>>> findAll();
    Mono<ResponseEntity<Product>> findById(String id);
    Mono<ResponseEntity<Product>> save(Product product);
    Mono<ResponseEntity<Void>> delete(Product product);
}
