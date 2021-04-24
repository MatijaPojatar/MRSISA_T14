package com.backend.springboot.controller;

import java.time.LocalTime;
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
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.DobavljacDTO;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.dto.RadnoVremeDTO;
import com.backend.springboot.service.DermatologService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/dermatolog")
public class DermatologController {
	
	@Autowired
	private DermatologService service;
	
	@GetMapping()
	public ResponseEntity<List<DermatologDTO>> findAll(){
		List<Dermatolog> dermatolozi = service.findAll();
		
		List<DermatologDTO> dtos = new ArrayList<>();
		for(Dermatolog d : dermatolozi) {
			dtos.add(new DermatologDTO(d));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DermatologDTO> getOne(@PathVariable Integer id){
		Dermatolog d=service.findOne(id);
		DermatologDTO dto=new DermatologDTO(d);
		
		return new ResponseEntity<DermatologDTO>(dto,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<DermatologDTO> registrujDermatologa(@RequestBody DermatologDTO dto){
		Dermatolog dermatolog = service.registrujDermatologa(new Dermatolog(dto));
		
		return new ResponseEntity<DermatologDTO>(new DermatologDTO(dermatolog), HttpStatus.OK);
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
	
	@PutMapping("/pass/{id}")
	public ResponseEntity<String> savePass(@PathVariable Integer id,@RequestBody String newPass){
		System.out.println(newPass);
		Dermatolog d=service.findOne(id);
		d.setPassword(newPass);
		
		service.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("/radnoVreme/{id}")
	public ResponseEntity<RadnoVremeDTO> getRadnoVreme(@PathVariable Integer id){
		Dermatolog d=service.findOne(id);
		RadnoVremeDTO rv=new RadnoVremeDTO(d.getPocetakRadnogVremena(),d.getKrajRadnogVremena());
		
		return new ResponseEntity<RadnoVremeDTO>(rv,HttpStatus.OK);
		
	}
	
	@GetMapping("/apoteka/{id}")
	public ResponseEntity<Collection<DermatologDTO>> findAllByApotekaId(@PathVariable Integer id){
		ArrayList<Dermatolog>rezultatPretrage = (ArrayList<Dermatolog>) service.findAllByApotekaId(id);
		ArrayList<DermatologDTO> dtoList=new ArrayList<DermatologDTO>();
		for(Dermatolog l:rezultatPretrage) {
			dtoList.add(new DermatologDTO(l));
		}
		
		return new ResponseEntity<Collection<DermatologDTO>>(dtoList, HttpStatus.OK);
	}
}
