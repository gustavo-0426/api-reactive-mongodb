package com.co.softworld.reactive.mongodb.service;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.repo.IProductRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private IProductService productService;
    @MockBean
    private IProductRepo productRepo;
    private Flux<Product> productFlux;
    private Product product;
    private Mono<Product> productMono;

    @BeforeEach
    void setUp() {
        List<Product> productList = asList(
                Product.builder().id("1").name("Printer").marca("Epson").build(),
                Product.builder().id("2").name("Laptop").marca("HP").build()
        );
        productFlux = Flux.fromIterable(productList);
        productFlux.subscribe();

        product = Product.builder().id("3").name("Mouse").marca("Genius").build();

        productMono = just(product);
    }

    @AfterEach
    void tearDown() {
        productService = null;
        productRepo = null;
        productFlux = null;
        product = null;
        productMono = null;
    }

    @Test
    void testFindAll() {
        when(productRepo.findAll()).thenReturn(productFlux);
        productService.findAll();
        verify(productRepo).findAll();
    }

    @Test
    void testFindById() {
        when(productRepo.findById("3")).thenReturn(productMono);
        productService.findById("3");
        verify(productRepo).findById("3");
    }

    @Test
    void testSave() {
        when(productRepo.save(product)).thenReturn(productMono);
        productService.save(product);
        verify(productRepo).save(product);
    }

    @Test
    void testDelete() {
        when(productRepo.findById("3")).thenReturn(productMono);
        productService.delete("3");
        verify(productRepo).findById("3");
    }
}