package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SpringbootApplication.class);
		logger.info("start");
		SpringApplication.run(SpringbootApplication.class, args);

	}

}
