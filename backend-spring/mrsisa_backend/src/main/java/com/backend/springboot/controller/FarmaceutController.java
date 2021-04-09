package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.service.FarmaceutService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/farmaceut")
public class FarmaceutController {
	
	@Autowired
	private FarmaceutService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<FarmaceutDTO> getOne(@PathVariable Integer id){
		Farmaceut d=service.findOne(id);
		FarmaceutDTO dto=new FarmaceutDTO(d);
		
		return new ResponseEntity<FarmaceutDTO>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/save/{id}")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody FarmaceutDTO dto){
		Farmaceut f=service.findOne(id);
		f.setAdresa(dto.getAdresa());
		f.setBrojTelefona(dto.getBrojTelefona());
		f.setDatumRodjenja(dto.getDatumRodjenja());
		f.setDrzava(dto.getDrzava());
		f.setGrad(dto.getGrad());
		f.setIme(dto.getIme());
		f.setPol(dto.getPol());
		f.setPrezime(dto.getPrezime());
		
		service.save(f);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/pass/{id}")
	public ResponseEntity<String> savePass(@PathVariable Integer id,@RequestBody String newPass){
		System.out.println(newPass);
		Farmaceut f=service.findOne(id);
		f.setPassword(newPass);
		
		service.save(f);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}

}
