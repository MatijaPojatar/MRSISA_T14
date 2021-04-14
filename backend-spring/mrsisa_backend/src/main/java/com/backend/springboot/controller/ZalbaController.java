package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.dto.ZalbaNaApotekuDTO;
import com.backend.springboot.service.ZalbaNaApotekuService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/zalbe")
public class ZalbaController {
	
	@Autowired
	private ZalbaNaApotekuService servisZaApoteke;
	
	@GetMapping("/apoteka")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAll () {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAll();
		
		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for(ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}
		
		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/apoteka")
	public ResponseEntity<ZalbaNaApotekuDTO> kreirajZalbuNaApoteku (@RequestBody ZalbaNaApotekuDTO dto) {
		ZalbaNaApoteku zalba = servisZaApoteke.dodajZalbu(new ZalbaNaApoteku(dto));
		
		return new ResponseEntity<ZalbaNaApotekuDTO>(new ZalbaNaApotekuDTO(zalba), HttpStatus.OK);
	}
}
