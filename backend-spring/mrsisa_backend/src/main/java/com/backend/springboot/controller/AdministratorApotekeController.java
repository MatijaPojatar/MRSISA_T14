package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.dto.AdministratorApotekeDTO;
import com.backend.springboot.service.AdministratorApotekeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/adminApoteke")
public class AdministratorApotekeController {

	@Autowired
	private AdministratorApotekeService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<AdministratorApotekeDTO> getOne(@PathVariable Integer id){
		AdministratorApoteke a=service.findOne(id);
		AdministratorApotekeDTO dto=new AdministratorApotekeDTO(a);
		
		return new ResponseEntity<AdministratorApotekeDTO>(dto,HttpStatus.OK);
	}
}
