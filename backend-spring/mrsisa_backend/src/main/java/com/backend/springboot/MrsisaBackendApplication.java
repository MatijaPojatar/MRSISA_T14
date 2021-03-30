package com.backend.springboot;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pol;
import com.backend.springboot.service.FarmaceutService;

@SpringBootApplication
public class MrsisaBackendApplication{

	public static void main(String[] args) {
		SpringApplication.run(MrsisaBackendApplication.class, args);
	}
	
	

}
