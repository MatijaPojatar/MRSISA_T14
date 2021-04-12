package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.ParametriPretrageLeka;
import com.backend.springboot.domain.ParametriPretrageRezervacije;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.dto.LekDTO;
import com.backend.springboot.service.LekService;

@CrossOrigin(origins = { "http://localhost:8081" })
@RestController
@RequestMapping("/lekovi")
public class LekController {

	@Autowired
	private LekService lekService;

	private ArrayList<Lek> pronadjeniLekovi;

	/*
	 * @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<Collection<Lek>> getLekovi() { Collection<Lek> lekovi =
	 * lekService.findAll();
	 * 
	 * return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK); }
	 */

	@PostMapping()
	public ResponseEntity<LekDTO> dodajLek(@RequestBody Lek lekInfo) {
		Lek lek = lekService.addLek(lekInfo);

		return new ResponseEntity<LekDTO>(new LekDTO(lek), HttpStatus.OK);
	}

	@PostMapping("/rezultat")
	public ResponseEntity<Collection<Lek>> pretrazi(@RequestBody ParametriPretrageLeka params) {

		pronadjeniLekovi = (ArrayList<Lek>) lekService.findWithParams(params.getSifra(), params.getNaziv(),
				params.getOblik(), params.getVrsta(), params.getRezim(), 1, params.getProizvodjac());

		return new ResponseEntity<Collection<Lek>>(pronadjeniLekovi, HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Lek>> getLekovi() {

		return new ResponseEntity<Collection<Lek>>(pronadjeniLekovi, HttpStatus.OK);
	}

	@GetMapping("/vrste")
	public ResponseEntity<Collection<String>> getVrste() {
		List<String> vrste = Stream.of(VrstaLeka.values()).map(VrstaLeka::name).collect(Collectors.toList());

		return new ResponseEntity<Collection<String>>(vrste, HttpStatus.OK);
	}

	@GetMapping("/oblici")
	public ResponseEntity<Collection<String>> getOblici() {
		List<String> oblici = Stream.of(OblikLeka.values()).map(OblikLeka::name).collect(Collectors.toList());

		return new ResponseEntity<Collection<String>>(oblici, HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<Collection<String>> getTest() {
		List<String> test = new ArrayList<String>();
		test.add("jen");
		test.add("dva");

		return new ResponseEntity<Collection<String>>(test, HttpStatus.OK);
	}

	@GetMapping("/apoteka/{id}")
	public ResponseEntity<Collection<LekDTO>> findAllByApotekaId(@PathVariable Integer id) {
		List<Lek> rezultatPretrage = lekService.findAllByApoteka(id);
		ArrayList<LekDTO> dtoList = new ArrayList<LekDTO>();
		for (Lek l : rezultatPretrage) {
			dtoList.add(new LekDTO(l));
		}

		return new ResponseEntity<Collection<LekDTO>>(dtoList, HttpStatus.OK);
	}

}
