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

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.dto.AdministratorApotekeDTO;
import com.backend.springboot.dto.FarmaceutDTO;
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
	
	@PutMapping("/save/{id}")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody AdministratorApotekeDTO dto){
		AdministratorApoteke a=service.findOne(id);
		a.setAdresa(dto.getAdresa());
		a.setBrojTelefona(dto.getBrojTelefona());
		a.setDatumRodjenja(dto.getDatumRodjenja());
		a.setDrzava(dto.getDrzava());
		a.setGrad(dto.getGrad());
		a.setIme(dto.getIme());
		a.setPol(dto.getPol());
		a.setPrezime(dto.getPrezime());
		
		service.save(a);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/aktivacija/{id}")
	public ResponseEntity<String> aktivirajProfil(@PathVariable Integer id){
		AdministratorApoteke p = service.findOne(id);
		
		if(!p.isEnabled()) {
			p.setEnabled(true);
			service.save(p);
			return new ResponseEntity<String>("Uspešno ste aktivirali profil "+ p.getIme() + " " + p.getPrezime() + ".", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Profil korisnika "+ p.getIme() + " " + p.getPrezime() + " je već aktiviran.", HttpStatus.OK);
	}
}
