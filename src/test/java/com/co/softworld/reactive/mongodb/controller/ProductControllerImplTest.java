package com.co.softworld.reactive.mongodb.controller;

import com.co.softworld.reactive.mongodb.model.Product;
import com.co.softworld.reactive.mongodb.service.IProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;
import static reactor.core.publisher.Mono.just;

@WebFluxTest(IProductController.class)
class ProductControllerImplTest {

    @Autowired
    private WebTestClient productController;
    @MockBean
    private IProductService productService;
    private Flux<Product> productFlux;
    private List<Product> productList;
    private Product product;

    @BeforeEach
    void setUp() {
        productList = asList(
                Product.builder().id("1").name("Printer").marca("Epson").build(),
                Product.builder().id("2").name("Laptop").marca("HP").build()
        );
        productFlux = Flux.fromIterable(productList);
        productFlux.subscribe();

        product = Product.builder().id("3").name("Mouse").marca("Genius").build();
    }

    @AfterEach
    void tearDown() {
        productController = null;
        productService = null;
        productFlux = null;
        productList = null;
        product = null;
    }

    @Test
    void testFindAll() {
        when(productService.findAll()).thenReturn(just(ok(productFlux)));
        productController.get()
                .uri("/product")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(2)
                .isEqualTo(productList);
    }

    @Test
    void testFindById() {
        when(productService.findById("3")).thenReturn(just(ok(product)));
        productController.get()
                .uri("/product/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

    @Test
    void testSave() {
        when(productService.save(product)).thenReturn(just(ok(product)));
        productController.post()
                .uri("/product")
                .body(just(product), Product.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

    @Test
    void tesDelete() {
        when(productService.delete("3")).thenReturn(just(ok(product)));
        productController.delete()
                .uri("/product/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

}