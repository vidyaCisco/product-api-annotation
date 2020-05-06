package com.vidya.productapiannotation;

import com.vidya.productapiannotation.model.Product;
import com.vidya.productapiannotation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ProductApiAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiAnnotationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProductRepository repository) {
		return args -> {
			Flux<Product> productFlux = Flux.just(
					new Product(null, "Coffee", 9.25),
					new Product(null, "Tea", 12.25),
					new Product(null, "Green Tea", 15.0)

			).flatMap(repository::save);

			productFlux.thenMany(repository.findAll())
					.subscribe(System.out::println);

		};
	}

}
