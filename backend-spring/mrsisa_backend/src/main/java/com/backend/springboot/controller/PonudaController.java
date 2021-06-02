package com.backend.springboot.controller;

import java.time.LocalDate;
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

import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusPonude;
import com.backend.springboot.dto.PonudaDTO;
import com.backend.springboot.service.DobavljacService;
import com.backend.springboot.service.NarudzbenicaService;
import com.backend.springboot.service.PonudaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/ponuda")
public class PonudaController {

	@Autowired
	private PonudaService ponudaService;
	
	@Autowired
	private DobavljacService dobavljacService;
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PonudaDTO> getById(@PathVariable Integer id){
		Ponuda ponuda = ponudaService.findOne(id);
		return new ResponseEntity<PonudaDTO>(new PonudaDTO(ponuda), HttpStatus.OK);
	}
	
	//kreiranje
	@PostMapping()
	public ResponseEntity<PonudaDTO> kreirajPonudu(@RequestBody PonudaDTO dto){
		Ponuda kreirana = new Ponuda(dto);
		kreirana.setDobavljac(dobavljacService.findOne(dto.getDobavljacId()));
		kreirana.setNarudzbenica(narudzbenicaService.findOne(dto.getNarudzbenicaId()));
		Ponuda ponuda = ponudaService.dodajPonudu(kreirana);
		
		return new ResponseEntity<PonudaDTO>(new PonudaDTO(ponuda), HttpStatus.OK);
	}
	
	//izmena
	@PutMapping("/{id}")
	public ResponseEntity<Ponuda> izmeniPonudu(@PathVariable Integer id, @RequestBody PonudaDTO dto){
		//znaci samo cena i rok isporuke mogu biti izmenjeni
		Ponuda ponuda = ponudaService.findOne(id);
		ponuda.setCena(dto.getCena());
		ponuda.setRokIsporuke(dto.getRokIsporuke());
		ponuda = ponudaService.izmeniPonudu(ponuda);
		return new ResponseEntity<Ponuda>(ponuda, HttpStatus.OK);
	}
	
	//za dobavljaca
	@GetMapping("/dobavljac/{id}")
	public ResponseEntity<List<PonudaDTO>> getByDobavljac(@PathVariable Integer id){
		List<Ponuda> lista = ponudaService.findAllByDobavljacId(id);
		
		List<PonudaDTO> dtos = new ArrayList<PonudaDTO>();
		for(Ponuda p : lista) {
			dtos.add(new PonudaDTO(p));
		}
		
		return new ResponseEntity<List<PonudaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/izmenaMoguca/{id}")
	public ResponseEntity<Boolean> isIzmenaMoguca(@PathVariable Integer id){
		Ponuda p = ponudaService.findOne(id);
		if(p.getStatus() != StatusPonude.OBRADA) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		LocalDate rokN = p.getNarudzbenica().getRok();
		LocalDate now  = LocalDate.now();
		if(now.isBefore(rokN)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	
	//za narudzbenicu
	@GetMapping("/narudzbenica/{id}")
	public ResponseEntity<List<PonudaDTO>> getByNarudzbenica(@PathVariable Integer id){
		List<Ponuda> lista = ponudaService.findAllByNarudzbenicaId(id);
		
		List<PonudaDTO> dtos = new ArrayList<PonudaDTO>();
		for(Ponuda p : lista) {
			dtos.add(new PonudaDTO(p));
		}
		
		return new ResponseEntity<List<PonudaDTO>>(dtos, HttpStatus.OK);
	}
}
