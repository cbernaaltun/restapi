package com.bernaaltun.restapiexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.bernaaltun.restapiexample.repository")
public class RestapiexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiexampleApplication.class, args);
	}
}
