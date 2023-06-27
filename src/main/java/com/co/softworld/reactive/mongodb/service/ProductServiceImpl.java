package com.co.softworld.reactive.mongodb.service;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepo productRepo;

    @Override
    public Flux<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepo.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        return productRepo.delete(product);
    }

}
