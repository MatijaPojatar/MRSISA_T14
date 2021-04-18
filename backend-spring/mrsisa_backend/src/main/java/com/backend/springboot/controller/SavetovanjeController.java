package com.backend.springboot.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.repository.LekUIzvestajuRepository;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
import com.backend.springboot.service.SavetovanjeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/savetovanje")
public class SavetovanjeController {
	
	@Autowired
	private SavetovanjeService service;
	@Autowired
	private FarmaceutService farmService;
	@Autowired
	private LekService lekService;
	@Autowired
	private LekUIzvestajuService izvestajService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<SavetovanjeDTO>> getAll() {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(1);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@PutMapping("/penal/{id}")
	public ResponseEntity<String> pacijentNijeDosao(@PathVariable Integer id){
		Savetovanje s=service.findOne(id);
		s.setIzvrsen(true);
		s.setIzvestaj("Pacijent se nije pojavio");
		
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izvestaj/{id}")
	public ResponseEntity<String> sacuvajIzvestaj(@PathVariable Integer id,@RequestBody IzvestajDTO izvestaj){
		Savetovanje s=service.findOne(id);
		s.setIzvrsen(true);
		s.setIzvestaj(izvestaj.getText());
		for(LekUIzvestajuDTO lekDto: izvestaj.getLekovi()) {
			Lek l=lekService.findOneById(lekDto.getLekId());
			System.out.println("========================"+lekDto.getLekId()+"==============================");
			LekUIzvestaju lekIz=new LekUIzvestaju( l, lekDto.getTerapija(),s);
			izvestajService.addLek(lekIz);
			s.getLekovi().add(lekIz);
		}
		
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<List<SavetovanjeDTO>> getAllForFarmaceut(@PathVariable Integer id) {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(id);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
}
