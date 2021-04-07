package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.service.DermatologService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/dermatolog")
public class DermatologController {
	
	@Autowired
	private DermatologService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<DermatologDTO> getOne(@PathVariable Integer id){
		Dermatolog d=service.findOne(id);
		DermatologDTO dto=new DermatologDTO(d);
		
		return new ResponseEntity<DermatologDTO>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/save/{id}")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody DermatologDTO dto){
		Dermatolog d=service.findOne(id);
		d.setAdresa(dto.getAdresa());
		d.setBrojTelefona(dto.getBrojTelefona());
		d.setDatumRodjenja(dto.getDatumRodjenja());
		d.setDrzava(dto.getDrzava());
		d.setGrad(dto.getGrad());
		d.setIme(dto.getIme());
		d.setPol(dto.getPol());
		d.setPrezime(dto.getPrezime());
		
		service.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
}
