package com.co.softworld.reactive.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {

    @Id
    public String id;
    public String name;
    public String marca;
}
