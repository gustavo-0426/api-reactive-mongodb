package com.co.softworld.reactive.mongodb.service;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.*;
import static reactor.core.publisher.Mono.just;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo productRepo;

    @Override
    public Mono<ResponseEntity<Flux<Product>>> findAll() {
        return just(ok(productRepo.findAll()));
    }

    @Override
    public Mono<ResponseEntity<Product>> findById(String id) {
        return productRepo.findById(id).map(ResponseEntity::ok).defaultIfEmpty(notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Product>> save(Product product) {
        return productRepo.save(product).map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(Product product) {
        return productRepo.delete(product).map(ResponseEntity::ok);
    }

}
