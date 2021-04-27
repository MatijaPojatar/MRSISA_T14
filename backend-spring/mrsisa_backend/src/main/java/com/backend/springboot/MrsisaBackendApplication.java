package com.backend.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MrsisaBackendApplication{

	public static void main(String[] args) {
		SpringApplication.run(MrsisaBackendApplication.class, args);
	}
}
