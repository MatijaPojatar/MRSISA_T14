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

import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusNarudzbenice;
import com.backend.springboot.dto.LekIzNarudzbeniceDTO;
import com.backend.springboot.dto.NarudzbenicaDTO;
import com.backend.springboot.dto.NovaNarudzbenicaDTO;
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
	
	
	@GetMapping("/lekovi/{id}")
	public ResponseEntity<String> preuzmiLekove(@PathVariable Integer id){
		List<LekIzNarudzbenice> lista = narudzbenicaService.preuzmiLekove(id);
		
		StringBuilder lekovi = new StringBuilder();
		for(LekIzNarudzbenice l : lista) {
			lekovi.append(l.getLek().getNaziv() + "\t");
			lekovi.append(l.getKolicina().toString() + "\n");
		}
		
		return new ResponseEntity<String>(lekovi.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/prihvatiPonudu/{ponudaId}")
	public ResponseEntity<Boolean> prihvatiPonudu(@PathVariable Integer ponudaId, @RequestBody String narId){
		magacinService.prihvatiPonudu(Integer.parseInt(narId), ponudaId);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
	
	@PostMapping("/dodaj/{apotekaId}")
	public ResponseEntity<Boolean> dodajNarudzbenicu(@PathVariable Integer apotekaId, @RequestBody NovaNarudzbenicaDTO narDTO){
		System.out.println("\n\n--------------------------" + narDTO.getRok());
		List<LekIzNarudzbenice> lekovi = new ArrayList<LekIzNarudzbenice>();
		narudzbenicaService.dodajNarudzbenicu(apotekaId, narDTO.getRok(), narDTO.getLekovi(), narDTO.getAdminId());
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}

}
