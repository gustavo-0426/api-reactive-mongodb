package com.co.softworld.reactive.mongodb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
@Builder
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String marca;
}
