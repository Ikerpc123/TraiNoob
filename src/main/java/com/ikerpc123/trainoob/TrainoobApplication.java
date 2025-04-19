package com.ikerpc123.trainoob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ikerpc123.repositorio")
@EntityScan(basePackages = "com.ikerpc123.modelo")
@ComponentScan(basePackages = "com.ikerpc123")
public class TrainoobApplication {
	
	@Bean
	public Principal applicationStartupRunner() {
		return new Principal();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TrainoobApplication.class, args);
	}

}
