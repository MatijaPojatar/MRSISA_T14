package com.backend.springboot.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.service.IzvestajApotekaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/izvestaj")
public class IzvestajApotekaController {
	@Autowired
	private IzvestajApotekaService izvestajService;
	
	@GetMapping("/pregledi/mesec/{apotekaId}")
	public ResponseEntity<Collection<Integer>> getOdrzaniPregledMesec(@PathVariable("apotekaId") Integer apotekaId) {
		
		Calendar cal = Calendar.getInstance();
	    int res = cal.getActualMaximum(Calendar.DATE);
	    LocalDateTime sada = LocalDateTime.now();
	    LocalDateTime kraj = LocalDateTime.of(sada.getYear(), sada.getMonth(), res, 23, 59);
	    LocalDateTime pocetak = kraj.minusMonths(1);
		Collection <Integer> rezultat = izvestajService.odrzaniPreglediUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Integer>>(rezultat, HttpStatus.OK);
	}
	
	@GetMapping("/pregledi/kvartal/{apotekaId}")
	public ResponseEntity<Collection<Integer>> getOdrzaniPregledKvartal(@PathVariable("apotekaId") Integer apotekaId) {
		Calendar cal = Calendar.getInstance();
	    int res = cal.getActualMaximum(Calendar.DATE);
	    LocalDateTime sada = LocalDateTime.now();
	    LocalDateTime kraj = LocalDateTime.of(sada.getYear(), sada.getMonth(), res, 23, 59);
	    LocalDateTime pocetak = kraj.minusMonths(3);
		Collection <Integer> rezultat = izvestajService.odrzaniPreglediUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Integer>>(rezultat, HttpStatus.OK);
	}
	
	@GetMapping("/pregledi/godina/{apotekaId}")
	public ResponseEntity<Collection<Integer>> getOdrzaniPregledGodina(@PathVariable("apotekaId") Integer apotekaId) {
		LocalDateTime sada = LocalDateTime.now();
	    LocalDateTime kraj = LocalDateTime.of(sada.getYear(), 12, 31, 23, 59);
		LocalDateTime pocetak = kraj.minusYears(1);
		Collection <Integer> rezultat = izvestajService.odrzaniPreglediUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Integer>>(rezultat, HttpStatus.OK);
	}
	
	@GetMapping("/potrosnja/mesec/{apotekaId}")
	public ResponseEntity<Collection<Double>> getPotrosnjaLekovaMesec(@PathVariable("apotekaId") Integer apotekaId) {
		
		Calendar cal = Calendar.getInstance();
	    int res = cal.getActualMaximum(Calendar.DATE);
	    LocalDate sada = LocalDate.now();
	    LocalDate kraj = LocalDate.of(sada.getYear(), sada.getMonth(), res);
	    LocalDate pocetak = kraj.minusMonths(1);
		Collection <Double> rezultat = izvestajService.potroseniLekoviUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Double>>(rezultat, HttpStatus.OK);
	}
	
	@GetMapping("/potrosnja/kvartal/{apotekaId}")
	public ResponseEntity<Collection<Double>> getPotrosnjaLekovaKvartal(@PathVariable("apotekaId") Integer apotekaId) {
		Calendar cal = Calendar.getInstance();
	    int res = cal.getActualMaximum(Calendar.DATE);
	    LocalDate sada = LocalDate.now();
	    LocalDate kraj = LocalDate.of(sada.getYear(), sada.getMonth(), res);
	    LocalDate pocetak = kraj.minusMonths(3);
		Collection <Double> rezultat = izvestajService.potroseniLekoviUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Double>>(rezultat, HttpStatus.OK);
	}
	
	@GetMapping("/potrosnja/godina/{apotekaId}")
	public ResponseEntity<Collection<Double>> getPotrosnjaLekovaGodina(@PathVariable("apotekaId") Integer apotekaId) {
		LocalDate sada = LocalDate.now();
		LocalDate kraj = LocalDate.of(sada.getYear(), 12, 31);
		LocalDate pocetak = kraj.minusYears(1);
		Collection <Double> rezultat = izvestajService.potroseniLekoviUIntervalu(pocetak, kraj, apotekaId);
		return new ResponseEntity<Collection<Double>>(rezultat, HttpStatus.OK);
	}
	
	
}
