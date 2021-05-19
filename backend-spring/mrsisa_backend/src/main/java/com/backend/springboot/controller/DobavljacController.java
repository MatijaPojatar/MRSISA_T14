package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.DobavljacDTO;
import com.backend.springboot.service.DobavljacService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/dobavljaci")
public class DobavljacController {

	@Autowired
	private DobavljacService dobavljacService;
	
	@GetMapping()
	public ResponseEntity<List<DobavljacDTO>> findAll(){
		List<Dobavljac> dobavljaci = dobavljacService.findAll();
		
		List<DobavljacDTO> dtos = new ArrayList<>();
		for(Dobavljac d : dobavljaci) {
			dtos.add(new DobavljacDTO(d));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DobavljacDTO> getOne(@PathVariable Integer id){
		Dobavljac d = dobavljacService.findOne(id);
		DobavljacDTO dto = new DobavljacDTO(d);
		
		return new ResponseEntity<DobavljacDTO>(dto,HttpStatus.OK);
	}
	

	@PostMapping()
	public ResponseEntity<DobavljacDTO> registrujDobavljaca(@RequestBody DobavljacDTO dto){
		Dobavljac dobavljac = dobavljacService.save(new Dobavljac(dto));
		
		return new ResponseEntity<DobavljacDTO>(new DobavljacDTO(dobavljac), HttpStatus.OK);
	}

	@PutMapping("/save/{id}")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody DobavljacDTO dto){
		Dobavljac d = dobavljacService.findOne(id);
		d.setAdresa(dto.getAdresa());
		d.setBrojTelefona(dto.getBrojTelefona());
		d.setDatumRodjenja(dto.getDatumRodjenja());
		d.setDrzava(dto.getDrzava());
		d.setGrad(dto.getGrad());
		d.setIme(dto.getIme());
		d.setPol(dto.getPol());
		d.setPrezime(dto.getPrezime());
		d.setNazivPreduzeca(dto.getNazivPreduzeca());
		
		dobavljacService.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	
	
	
}
