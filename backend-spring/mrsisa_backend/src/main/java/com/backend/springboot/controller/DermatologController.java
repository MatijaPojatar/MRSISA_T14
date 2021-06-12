package com.backend.springboot.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.DermatologApoteka;
import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.ParametriPretrageOsoba;
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
	

	private boolean pretragaDermatologaApoteke = false;
	private ArrayList<Dermatolog> pronadjeniDermatolozi;
	private ArrayList<DermatologDTO> pronadjeniDermatoloziDTO;
	
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
	
	@PostMapping("/dermatologApoteke/{id}")
	public ResponseEntity<DermatologDTO> getOneDermatologApoteke(@PathVariable Integer id, @RequestBody String apotekaId){
		Dermatolog d=service.findOne(id);
		DermatologDTO dto=new DermatologDTO(d, Integer.parseInt(apotekaId));
		
		return new ResponseEntity<DermatologDTO>(dto,HttpStatus.OK);
	}
	

	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DermatologDTO> registrujDermatologa(@RequestBody DermatologDTO dto){
		Dermatolog dermatolog = service.registrujDermatologa(new Dermatolog(dto));
		
		return new ResponseEntity<DermatologDTO>(new DermatologDTO(dermatolog), HttpStatus.OK);
	}
	
	@PutMapping("/save/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody DermatologDTO dto){
		if(dto.getIme().length()>20) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		if(dto.getPrezime().length()>20) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		if(dto.getBrojTelefona().length()!=10 || !Pattern.matches("06[0-9]{8}", dto.getBrojTelefona())) {
			return new ResponseEntity<String>("Greska",HttpStatus.NO_CONTENT);
		}
		Dermatolog d=service.findOne(id);
		d.setAdresa(dto.getAdresa());
		d.setBrojTelefona(dto.getBrojTelefona());
		d.setDatumRodjenja(dto.getDatumRodjenja());
		d.setDrzava(dto.getDrzava());
		d.setGrad(dto.getGrad());
		d.setIme(dto.getIme());
		d.setPol(dto.getPol());
		d.setPrezime(dto.getPrezime());
		d.setPocetakRadnogVremena(dto.getPocetakRadnogVremena());
		d.setKrajRadnogVremena(dto.getKrajRadnogVremena());
		
		service.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/updateRadnoVreme/{apotekaId}")
	public ResponseEntity<Boolean> updateOne(@PathVariable Integer apotekaId,@RequestBody DermatologDTO dto){
		boolean uspeh = service.promeniRadnoVreme(dto.getId(), apotekaId, dto.getPocetakRadnogVremena(), dto.getKrajRadnogVremena());
		
		return new ResponseEntity<Boolean>(uspeh,HttpStatus.OK);
	}
	
	@GetMapping("/pass/check/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<Boolean> checkPass(@PathVariable Integer id,@RequestParam String pass){
		Dermatolog d=service.findOne(id);
		if(d.getPassword().equals(pass)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/pass/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<String> savePass(@PathVariable Integer id,@RequestBody String newPass){
		System.out.println(newPass);
		Dermatolog d=service.findOne(id);
		d.setPassword(newPass);
		
		service.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("/radnoVreme/{id}")
	public ResponseEntity<RadnoVremeDTO> getRadnoVreme(@PathVariable Integer id,@RequestParam Integer apotekaId){
		DermatologApoteka da=service.findZaposlenje(apotekaId, id);
		RadnoVremeDTO rv=new RadnoVremeDTO(da.getPocetakRadnogVremena(),da.getKrajRadnogVremena());
		
		return new ResponseEntity<RadnoVremeDTO>(rv,HttpStatus.OK);
		
	}
	
	@GetMapping("/apoteka/{id}")
	public ResponseEntity<Collection<DermatologDTO>> findAllByApotekaId(@PathVariable Integer id){
		if (pretragaDermatologaApoteke) {
			pretragaDermatologaApoteke = false;
			return new ResponseEntity<Collection<DermatologDTO>>(pronadjeniDermatoloziDTO, HttpStatus.OK);
			
		}
		ArrayList<Dermatolog>rezultatPretrage = (ArrayList<Dermatolog>) service.findAllByApotekaId(id);
		ArrayList<DermatologDTO> dtoList=new ArrayList<DermatologDTO>();
		for(Dermatolog l:rezultatPretrage) {
			dtoList.add(new DermatologDTO(l, id));
		}
		
		return new ResponseEntity<Collection<DermatologDTO>>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/pretragaDermatologa/{apotekaId}")
	public ResponseEntity<Boolean> pretrazi(@PathVariable Integer apotekaId, @RequestBody ParametriPretrageOsoba params) {
		
		pronadjeniDermatoloziDTO = new ArrayList<DermatologDTO>();
		pronadjeniDermatolozi = (ArrayList<Dermatolog>)service.pretraziDermatologeApoteke(params.getIme(), params.getPrezime(), params.getGrad(), params.getDrzava(), params.getPol(), apotekaId);
		if (pronadjeniDermatolozi.size() != 0) {
			pretragaDermatologaApoteke = true;
			for (Dermatolog f : pronadjeniDermatolozi) {
				pronadjeniDermatoloziDTO.add(new DermatologDTO(f));
			}
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
			
			ArrayList<Dermatolog>rezultatPretrage = (ArrayList<Dermatolog>) service.findAllByApotekaId(apotekaId);
			ArrayList<DermatologDTO> dtoList=new ArrayList<DermatologDTO>();
			for(Dermatolog l:rezultatPretrage) {
				pronadjeniDermatoloziDTO.add(new DermatologDTO(l));
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/vanApoteka/{id}")
	public ResponseEntity<Collection<DermatologDTO>> findAllNotInApoteka(@PathVariable Integer id){
		ArrayList<Dermatolog>rezultatPretrage = (ArrayList<Dermatolog>) service.preuzmiDermatologeVanApoteke(id);
		ArrayList<DermatologDTO> dtoList=new ArrayList<DermatologDTO>();
		for(Dermatolog l:rezultatPretrage) {
			dtoList.add(new DermatologDTO(l));
		}
		
		return new ResponseEntity<Collection<DermatologDTO>>(dtoList, HttpStatus.OK);
	}
	
	@PutMapping("/obrisiDermatologaApoteka/{id}")
	public ResponseEntity<String> obrisiDermatologaApoteka(@PathVariable Integer id, @RequestBody String apotekaId) {
		service.obrisiDermatologaIzApoteke(id, Integer.parseInt(apotekaId));
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/dodajDermatologaApoteka/{id}/{apotekaId}")
	public ResponseEntity<Boolean> dodajDermatologaApoteka(@PathVariable Integer id,@PathVariable Integer apotekaId, @RequestBody RadnoVremeDTO radnoVreme ){
		boolean uspeh = service.dodajDermatologaUApoteku(id, apotekaId, radnoVreme.getPocetak(), radnoVreme.getKraj());
		
		return new ResponseEntity<Boolean>(uspeh,HttpStatus.OK);
	}
}
