package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.dto.AdministratorSistemaDTO;
import com.backend.springboot.service.AdministratorSistemaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/adminSistema")
public class AdministratorSistemaController {
	
	@Autowired
	private AdministratorSistemaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<AdministratorSistemaDTO> getOne(@PathVariable Integer id) {
		AdministratorSistema a = service.findOne(id);
		AdministratorSistemaDTO dto = new AdministratorSistemaDTO(a);
		
		return new ResponseEntity<AdministratorSistemaDTO>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/aktivacija/{id}")
	public ResponseEntity<String> aktivirajProfil(@PathVariable Integer id){
		AdministratorSistema p = service.findOne(id);
		
		if(!p.isEnabled()) {
			p.setEnabled(true);
			service.save(p);
			return new ResponseEntity<String>("Uspešno ste aktivirali profil "+ p.getIme() + " " + p.getPrezime() + ".", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Profil korisnika "+ p.getIme() + " " + p.getPrezime() + " je već aktiviran.", HttpStatus.OK);
	}
	
}
