package com.co.softworld.reactive.mongodb.repo;

import com.co.softworld.reactive.mongodb.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends ReactiveMongoRepository<Product, String> {
}
