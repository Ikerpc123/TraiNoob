package com.ikerpc123.trainoob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "modelo")
public class TrainoobApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainoobApplication.class, args);
	}

}
