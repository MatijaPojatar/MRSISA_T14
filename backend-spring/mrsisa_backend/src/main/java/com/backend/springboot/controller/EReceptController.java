package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.ERecept;
import com.backend.springboot.dto.EReceptDTO;
import com.backend.springboot.service.EReceptService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/erecepti")
public class EReceptController {
	
	@Autowired
	private EReceptService service;
	
	@GetMapping()
	public ResponseEntity<List<EReceptDTO>> findAll(){
		List<ERecept> erecepti = service.findAll();
		
		List<EReceptDTO> dtos = new ArrayList<EReceptDTO>();
		for (ERecept e: erecepti) {
			dtos.add(new EReceptDTO(e));
		}
		
		return new ResponseEntity<List<EReceptDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EReceptDTO> findOne(@PathVariable Integer id)
	{
		ERecept rec = service.findOne(id);
		
		return new ResponseEntity<EReceptDTO>(new EReceptDTO(rec), HttpStatus.OK);
	}
	
	@GetMapping("/pacijent/{id}")
	public ResponseEntity<List<EReceptDTO>> findByPacijent(@PathVariable Integer id){
		List<ERecept> recepti = service.findByPacijent(id);
		
		List<EReceptDTO> dtos = new ArrayList<EReceptDTO>();
		for(ERecept e : recepti) {
			dtos.add(new EReceptDTO(e));
		}
		
		return new ResponseEntity<List<EReceptDTO>>(dtos, HttpStatus.OK);
	}
}
