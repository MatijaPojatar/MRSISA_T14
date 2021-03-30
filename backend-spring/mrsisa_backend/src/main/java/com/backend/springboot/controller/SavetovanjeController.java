package com.backend.springboot.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.SavetovanjeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/savetovanje")
public class SavetovanjeController {
	
	@Autowired
	private SavetovanjeService service;
	@Autowired
	private FarmaceutService farmService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<SavetovanjeDTO>> getAll() {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(1);

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}
		
		savetovanjaDTO.add(new SavetovanjeDTO("Savetovanje 1", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 30)), null, false));
		savetovanjaDTO.add(new SavetovanjeDTO("Savetovanje 2", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 30)), 1, false));
		savetovanjaDTO.add(new SavetovanjeDTO("Savetovanje 3", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 30)), 2, true));

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}

}
