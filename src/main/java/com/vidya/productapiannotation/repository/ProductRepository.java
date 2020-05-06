package com.vidya.productapiannotation.repository;

import com.vidya.productapiannotation.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
