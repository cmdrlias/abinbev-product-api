package com.abinbev.product.api;

import com.abinbev.product.api.repository.ProductRepository;
import com.abinbev.product.api.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {
		ProductRepository.class,
		UserRepository.class
})
public class AbinbevProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbinbevProductApiApplication.class, args);
	}

}
