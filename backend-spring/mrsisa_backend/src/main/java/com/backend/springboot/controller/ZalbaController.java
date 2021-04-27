package com.backend.springboot.controller;

import java.util.ArrayList;
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

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;
import com.backend.springboot.dto.ZalbaNaApotekuDTO;
import com.backend.springboot.dto.ZalbaNaDermatologaDTO;
import com.backend.springboot.dto.ZalbaNaFarmaceutaDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.ZalbaNaApotekuService;
import com.backend.springboot.service.ZalbaNaDermatologaService;
import com.backend.springboot.service.ZalbaNaFarmaceutaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/zalbe")
public class ZalbaController {
	
	@Autowired
	private ZalbaNaApotekuService servisZaApoteke;
	
	@Autowired 
	private ZalbaNaFarmaceutaService servisZaFarmaceute;
	
	@Autowired
	private ZalbaNaDermatologaService servisZaDermatologe;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private FarmaceutService farmaceutService;
	
	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@GetMapping("/apoteka")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAllZNA () {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAll();
		
		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for(ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}
		
		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/apoteka")
	public ResponseEntity<ZalbaNaApotekuDTO> kreirajZalbuNaApoteku (@RequestBody ZalbaNaApotekuDTO dto) {
		ZalbaNaApoteku kreirana = new ZalbaNaApoteku(dto);
		kreirana.setApoteka(apotekaService.findOne(dto.getApotekaId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaApoteku zalba = servisZaApoteke.dodajZalbu(kreirana);
		
		return new ResponseEntity<ZalbaNaApotekuDTO>(new ZalbaNaApotekuDTO(zalba), HttpStatus.OK);
	}
	
	@PutMapping("/apoteka/{id}")
	public ResponseEntity<String> odgovoriNaZalbuNaApoteku(@PathVariable Integer id, @RequestBody String odgovor){
		ZalbaNaApoteku odabrana = servisZaApoteke.findOne(id);
		servisZaApoteke.odgovoriNaZalbu(odabrana, odgovor);
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
	
	
	@GetMapping("/farmaceut")
	public ResponseEntity<List<ZalbaNaFarmaceutaDTO>> getAllZNF () {
		List<ZalbaNaFarmaceuta> zalbe = servisZaFarmaceute.findAll();
		
		List<ZalbaNaFarmaceutaDTO> dtos = new ArrayList<ZalbaNaFarmaceutaDTO>();
		for(ZalbaNaFarmaceuta z : zalbe) {
			dtos.add(new ZalbaNaFarmaceutaDTO(z));
		}
		
		return new ResponseEntity<List<ZalbaNaFarmaceutaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/farmaceut")
	public ResponseEntity<ZalbaNaFarmaceutaDTO> kreirajZalbuNaFarmaceuta (@RequestBody ZalbaNaFarmaceutaDTO dto) {
		ZalbaNaFarmaceuta kreirana = new ZalbaNaFarmaceuta(dto);
		kreirana.setFarmaceut(farmaceutService.findOne(dto.getFarmaceutId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaFarmaceuta zalba = servisZaFarmaceute.dodajZalbu(kreirana);
		
		return new ResponseEntity<ZalbaNaFarmaceutaDTO>(new ZalbaNaFarmaceutaDTO(zalba), HttpStatus.OK);
	}
	
	@PutMapping("/farmaceut/{id}")
	public ResponseEntity<String> odgovoriNaZalbuNaFarmaceuta(@PathVariable Integer id, @RequestBody String odgovor){
		ZalbaNaFarmaceuta odabrana = servisZaFarmaceute.findOne(id);
		servisZaFarmaceute.odgovoriNaZalbu(odabrana, odgovor);
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
	
	@GetMapping("/dermatolog")
	public ResponseEntity<List<ZalbaNaDermatologaDTO>> getAllZND () {
		List<ZalbaNaDermatologa> zalbe = servisZaDermatologe.findAll();
		
		List<ZalbaNaDermatologaDTO> dtos = new ArrayList<ZalbaNaDermatologaDTO>();
		for(ZalbaNaDermatologa z : zalbe) {
			dtos.add(new ZalbaNaDermatologaDTO(z));
		}
		
		return new ResponseEntity<List<ZalbaNaDermatologaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/dermatolog")
	public ResponseEntity<ZalbaNaDermatologaDTO> kreirajZalbuNaDermatologa (@RequestBody ZalbaNaDermatologaDTO dto) {
		ZalbaNaDermatologa kreirana = new ZalbaNaDermatologa(dto);
		kreirana.setDermatolog(dermatologService.findOne(dto.getDermatologId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaDermatologa zalba = servisZaDermatologe.dodajZalbu(kreirana);
		
		return new ResponseEntity<ZalbaNaDermatologaDTO>(new ZalbaNaDermatologaDTO(zalba), HttpStatus.OK);
	}
	
	@PutMapping("/dermatolog/{id}")
	public ResponseEntity<String> odgovoriNaZalbuNaDermatologa(@PathVariable Integer id, @RequestBody String odgovor){
		ZalbaNaDermatologa odabrana = servisZaDermatologe.findOne(id);
		servisZaDermatologe.odgovoriNaZalbu(odabrana, odgovor);
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
