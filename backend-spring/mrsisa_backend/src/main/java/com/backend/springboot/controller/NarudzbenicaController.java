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

import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusNarudzbenice;
import com.backend.springboot.dto.NarudzbenicaDTO;
import com.backend.springboot.dto.PonudaDTO;
import com.backend.springboot.service.MagacinService;
import com.backend.springboot.service.NarudzbenicaService;
@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/narudzbenice")
public class NarudzbenicaController {
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired 
	private MagacinService magacinService;
	
	@GetMapping("/apoteka/{id}")
	public ResponseEntity<List<NarudzbenicaDTO>> preuzmiSveNarudzbenice(@PathVariable Integer id){
		Magacin m=magacinService.findOneByApotekaId(id);
		
		List<Narudzbenica> nar= magacinService.preuzmiSveNarudzbenice(m.getId());
		List<NarudzbenicaDTO> dto = new ArrayList<NarudzbenicaDTO>();
		for (Narudzbenica n: nar) {
			dto.add(new NarudzbenicaDTO(n));
		}
		
		return new ResponseEntity<List<NarudzbenicaDTO>>(dto, HttpStatus.OK);
		
	}
	
	@PostMapping("/statusApoteka/{id}")
	public ResponseEntity<List<NarudzbenicaDTO>> preuzmiNarudzbenicePoStatusu(@PathVariable Integer id, @RequestBody String status){
		Magacin m=magacinService.findOneByApotekaId(id);
		
		List<Narudzbenica> nar= magacinService.preuzmiNarudzbenicePoStatusu(m.getId(), StatusNarudzbenice.valueOf(status));
		List<NarudzbenicaDTO> dto = new ArrayList<NarudzbenicaDTO>();
		for (Narudzbenica n: nar) {
			dto.add(new NarudzbenicaDTO(n));
		}
		
		return new ResponseEntity<List<NarudzbenicaDTO>>(dto, HttpStatus.OK);
		
	}
	
	@GetMapping("/ponude/{id}")
	public ResponseEntity<List<PonudaDTO>> preuzmiPonude(@PathVariable Integer id){
		List<Ponuda> lista = narudzbenicaService.preuzmiPonude(id);
		
		List<PonudaDTO> dtos = new ArrayList<PonudaDTO>();
		for(Ponuda p : lista) {
			dtos.add(new PonudaDTO(p));
		}
		
		return new ResponseEntity<List<PonudaDTO>>(dtos, HttpStatus.OK);
	}

}
