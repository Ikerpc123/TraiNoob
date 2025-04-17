package com.ikerpc123.trainoob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"com.ikerpc123.trainoob", "com/ikerpc123/controlador"})
public class TrainoobApplication {
	
	@Bean
	public Principal applicationStartupRunner() {
		return new Principal();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TrainoobApplication.class, args);
	}

}
