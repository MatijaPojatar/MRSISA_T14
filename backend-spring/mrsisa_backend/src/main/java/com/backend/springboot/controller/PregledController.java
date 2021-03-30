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

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.service.PregledService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/pregled")
public class PregledController {
	
	@Autowired
	private PregledService service;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<PregledDTO>> getAll() {

		List<Pregled> pregledi = service.findAllByDermatologId(2);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}
		
		preglediDTO.add(new PregledDTO("Pregled 1", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 30)), null, false,1000));
		preglediDTO.add(new PregledDTO("Pregled 2", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)), 1, false,1000));
		preglediDTO.add(new PregledDTO("Pregled 3", "", LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30)), 2, true,1000));

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}

}
