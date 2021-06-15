package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.ParametriPretrageOsoba;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.dto.RadnoVremeDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.FarmaceutService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/farmaceut")
public class FarmaceutController {
	
	@Autowired
	private FarmaceutService service;
	@Autowired
	private ApotekaService apotekaService;

	
	private boolean pretragaFarmaceutaApoteke = false;
	private ArrayList<Farmaceut> pronadjeniFarmaceuti;
	private ArrayList<FarmaceutDTO> pronadjeniFarmaceutiDTO;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_SISTEMA','ADMIN_APOTEKE','FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> getOne(@PathVariable Integer id){
		Farmaceut d=service.findOne(id);
		FarmaceutDTO dto=new FarmaceutDTO(d);
		
		return new ResponseEntity<FarmaceutDTO>(dto,HttpStatus.OK);
	}
	

	
	@PutMapping("/save/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','ADMIN_APOTEKE')")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody FarmaceutDTO dto){
		if(dto.getIme().length()>20) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		if(dto.getPrezime().length()>20) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		if(dto.getBrojTelefona().length()!=10 || !Pattern.matches("06[0-9]{8}", dto.getBrojTelefona())) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		Farmaceut f=service.findOne(id);
		f.setAdresa(dto.getAdresa());
		f.setBrojTelefona(dto.getBrojTelefona());
		f.setDatumRodjenja(dto.getDatumRodjenja());
		f.setDrzava(dto.getDrzava());
		f.setGrad(dto.getGrad());
		f.setIme(dto.getIme());
		f.setPol(dto.getPol());
		f.setPrezime(dto.getPrezime());
		f.setPocetakRadnogVremena(dto.getPocetakRadnogVremena());
		f.setKrajRadnogVremena(dto.getKrajRadnogVremena());
		
		service.save(f);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("/pass/check/{id}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<Boolean> checkPass(@PathVariable Integer id,@RequestParam String pass){
		Farmaceut f=service.findOne(id);
		if(f.getPassword().equals(pass)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/pass/{id}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<String> savePass(@PathVariable Integer id,@RequestBody String newPass){
		System.out.println(newPass);
		Farmaceut f=service.findOne(id);
		f.setPassword(newPass);
		
		service.save(f);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	
	@GetMapping("/apoteka/{id}")
	public ResponseEntity<Collection<FarmaceutDTO>> findAllByApotekaId(@PathVariable Integer id){
		if (pretragaFarmaceutaApoteke) {
			pretragaFarmaceutaApoteke = false;
			return new ResponseEntity<Collection<FarmaceutDTO>>(pronadjeniFarmaceutiDTO, HttpStatus.OK);
			
		}
		ArrayList<Farmaceut>rezultatPretrage = (ArrayList<Farmaceut>) service.findAllByApotekaId(id);
		ArrayList<FarmaceutDTO> dtoList=new ArrayList<FarmaceutDTO>();
		for(Farmaceut l:rezultatPretrage) {
			dtoList.add(new FarmaceutDTO(l));
		}
		
		return new ResponseEntity<Collection<FarmaceutDTO>>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/pretragaFarmaceuta/{apotekaId}")
	public ResponseEntity<Boolean> pretrazi(@PathVariable Integer apotekaId, @RequestBody ParametriPretrageOsoba params) {
		
		pronadjeniFarmaceutiDTO = new ArrayList<FarmaceutDTO>();
		pronadjeniFarmaceuti = (ArrayList<Farmaceut>)service.pretraziFarmaceuteApoteke(params.getIme(), params.getPrezime(), params.getGrad(), params.getDrzava(), params.getPol(), apotekaId);
		if (pronadjeniFarmaceuti.size() != 0) {
			pretragaFarmaceutaApoteke = true;
			for (Farmaceut f : pronadjeniFarmaceuti) {
				pronadjeniFarmaceutiDTO.add(new FarmaceutDTO(f));
			}
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
			
			ArrayList<Farmaceut>rezultatPretrage = (ArrayList<Farmaceut>) service.findAllByApotekaId(apotekaId);
			for(Farmaceut l:rezultatPretrage) {
				pronadjeniFarmaceutiDTO.add(new FarmaceutDTO(l));
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		
	}
	
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@PutMapping("/obrisiFarmaceuta/{id}")
	public ResponseEntity<String> obrisiFarmaceuta(@PathVariable Integer id) {
		service.obrisiFarmaceuta(id);
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@PostMapping("/dodajFarmaceuta/{id}")
	public ResponseEntity<String> dodajFarmaceuta(@PathVariable Integer id,@RequestBody FarmaceutDTO dto){
		System.out.println("=============================================zczcdzcz=================");
		Farmaceut f=new Farmaceut();
		f.setAdresa(dto.getAdresa());
		f.setBrojTelefona(dto.getBrojTelefona());
		f.setDatumRodjenja(dto.getDatumRodjenja());
		f.setDrzava(dto.getDrzava());
		f.setGrad(dto.getGrad());
		f.setIme(dto.getIme());
		f.setPol(dto.getPol());
		f.setPrezime(dto.getPrezime());
		f.setPocetakRadnogVremena(dto.getPocetakRadnogVremena());
		f.setKrajRadnogVremena(dto.getKrajRadnogVremena());
		f.setMail(dto.getMail());
		f.setApoteka(apotekaService.findOne(id));
		f.setPromenjenaLozinka(false);
		f.setObrisan(false);
		f.setPassword("defaultPassword");
		f.setOcena(4.0);
		
		service.save(f);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("/radnoVreme/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','ADMIN_APOTEKE','PACIJENT')")
	public ResponseEntity<RadnoVremeDTO> getRadnoVreme(@PathVariable Integer id){
		System.out.println("==============================================================");
		Farmaceut f=service.findOne(id);
		RadnoVremeDTO rv=new RadnoVremeDTO(f.getPocetakRadnogVremena(),f.getKrajRadnogVremena());
		
		return new ResponseEntity<RadnoVremeDTO>(rv,HttpStatus.OK);
		
	}
	

}
