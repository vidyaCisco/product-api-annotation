package com.vidya.productapiannotation.controller;

import com.vidya.productapiannotation.model.Product;
import com.vidya.productapiannotation.model.ProductEvent;
import com.vidya.productapiannotation.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> getByProductId(@PathVariable String id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return repository.findById(id)
                .flatMap(existingProduct -> repository.delete(existingProduct)
                .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductEvent> getProductEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(val  -> new ProductEvent(val, "Product Event"));
    }

}
