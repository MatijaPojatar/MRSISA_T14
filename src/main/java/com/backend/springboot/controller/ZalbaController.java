package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;
import com.backend.springboot.dto.ZalbaNaApotekuDTO;
import com.backend.springboot.dto.ZalbaNaDermatologaDTO;
import com.backend.springboot.dto.ZalbaNaFarmaceutaDTO;
import com.backend.springboot.service.AdministratorSistemaService;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.ZalbaNaApotekuService;
import com.backend.springboot.service.ZalbaNaDermatologaService;
import com.backend.springboot.service.ZalbaNaFarmaceutaService;

@CrossOrigin(origins = { "http://localhost:8081" })
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
	
	@Autowired 
	private AdministratorSistemaService adminService;
	
	@Autowired
	private EmailService emailService;
	

	@GetMapping("/apoteka/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<ZalbaNaApotekuDTO> getZNAById(@PathVariable Integer id){
		ZalbaNaApoteku zalba = servisZaApoteke.findOne(id);
		return new ResponseEntity<ZalbaNaApotekuDTO>(new ZalbaNaApotekuDTO(zalba), HttpStatus.OK);
	}
	
	
	@GetMapping("/apoteka")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAllZNA() {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAll();

		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for (ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/apoteka/neobradjene")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAllZNANeobradjene() {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAllNeobradjene();

		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for (ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/apoteka/obradjene/{id}")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAllZNAObradjeneByAdmin(@PathVariable Integer id) {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAllObradjeneByAdmin(id);

		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for (ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/apoteka/pacijent/{id}")
	public ResponseEntity<List<ZalbaNaApotekuDTO>> getAllZNAByPacijent(@PathVariable Integer id) {
		List<ZalbaNaApoteku> zalbe = servisZaApoteke.findAllByPacijent(id);
		
		List<ZalbaNaApotekuDTO> dtos = new ArrayList<ZalbaNaApotekuDTO>();
		for (ZalbaNaApoteku z : zalbe) {
			dtos.add(new ZalbaNaApotekuDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaApotekuDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('PACIJENT')")
	@PostMapping("/apoteka")
	public ResponseEntity<ZalbaNaApotekuDTO> kreirajZalbuNaApoteku(@RequestBody ZalbaNaApotekuDTO dto) {
		ZalbaNaApoteku kreirana = new ZalbaNaApoteku(dto);
		kreirana.setApoteka(apotekaService.findOne(dto.getApotekaId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaApoteku zalba = servisZaApoteke.dodajZalbu(kreirana);

		return new ResponseEntity<ZalbaNaApotekuDTO>(new ZalbaNaApotekuDTO(zalba), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PutMapping("/apoteka/{id}")
	public ResponseEntity<ZalbaNaApotekuDTO> odgovoriNaZalbuNaApoteku(@PathVariable Integer id, @RequestBody ZalbaNaApotekuDTO apdejtovana) {
		ZalbaNaApoteku odabrana = servisZaApoteke.findOne(id);
		ZalbaNaApoteku zalba = servisZaApoteke.odgovoriNaZalbu(odabrana,
				apdejtovana.getOdgovor(), adminService.findOne( apdejtovana.getAdministratorId()));
		
		try {
			emailService.noviOdgovorNaZalbuNaApoteku(zalba);
		} catch(Exception e){
			Thread.currentThread().interrupt();
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<ZalbaNaApotekuDTO>(new ZalbaNaApotekuDTO(zalba), HttpStatus.OK);
	}

	@GetMapping("/farmaceut")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<List<ZalbaNaFarmaceutaDTO>> getAllZNF() {
		List<ZalbaNaFarmaceuta> zalbe = servisZaFarmaceute.findAll();

		List<ZalbaNaFarmaceutaDTO> dtos = new ArrayList<ZalbaNaFarmaceutaDTO>();
		for (ZalbaNaFarmaceuta z : zalbe) {
			dtos.add(new ZalbaNaFarmaceutaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaFarmaceutaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/farmaceut/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<ZalbaNaFarmaceutaDTO> getZNFById(@PathVariable Integer id){
		ZalbaNaFarmaceuta zalba = servisZaFarmaceute.findOne(id);
		return new ResponseEntity<ZalbaNaFarmaceutaDTO>(new ZalbaNaFarmaceutaDTO(zalba), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/farmaceut/neobradjene")
	public ResponseEntity<List<ZalbaNaFarmaceutaDTO>> getAllZNFNeobradjene() {
		List<ZalbaNaFarmaceuta> zalbe = servisZaFarmaceute.findAllNeobradjene();

		List<ZalbaNaFarmaceutaDTO> dtos = new ArrayList<ZalbaNaFarmaceutaDTO>();
		for (ZalbaNaFarmaceuta z : zalbe) {
			dtos.add(new ZalbaNaFarmaceutaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaFarmaceutaDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/farmaceut/obradjene/{id}")
	public ResponseEntity<List<ZalbaNaFarmaceutaDTO>> getAllZNFObradjeneByAdmin(@PathVariable Integer id) {
		List<ZalbaNaFarmaceuta> zalbe = servisZaFarmaceute.findAllObradjeneByAdmin(id);

		List<ZalbaNaFarmaceutaDTO> dtos = new ArrayList<ZalbaNaFarmaceutaDTO>();
		for (ZalbaNaFarmaceuta z : zalbe) {
			dtos.add(new ZalbaNaFarmaceutaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaFarmaceutaDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/farmaceut/pacijent/{id}")
	public ResponseEntity<List<ZalbaNaFarmaceutaDTO>> getAllZNFByPacijent(@PathVariable Integer id) {
		List<ZalbaNaFarmaceuta> zalbe = servisZaFarmaceute.findAllByPacijent(id);
		
		List<ZalbaNaFarmaceutaDTO> dtos = new ArrayList<ZalbaNaFarmaceutaDTO>();
		for (ZalbaNaFarmaceuta z : zalbe) {
			dtos.add(new ZalbaNaFarmaceutaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaFarmaceutaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@PostMapping("/farmaceut")
	public ResponseEntity<ZalbaNaFarmaceutaDTO> kreirajZalbuNaFarmaceuta(@RequestBody ZalbaNaFarmaceutaDTO dto) {
		ZalbaNaFarmaceuta kreirana = new ZalbaNaFarmaceuta(dto);
		kreirana.setFarmaceut(farmaceutService.findOne(dto.getFarmaceutId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaFarmaceuta zalba = servisZaFarmaceute.dodajZalbu(kreirana);

		return new ResponseEntity<ZalbaNaFarmaceutaDTO>(new ZalbaNaFarmaceutaDTO(zalba), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PutMapping("/farmaceut/{id}")
	public ResponseEntity<ZalbaNaFarmaceutaDTO> odgovoriNaZalbuNaFarmaceuta(@PathVariable Integer id, @RequestBody ZalbaNaApotekuDTO apdejtovana) {
		ZalbaNaFarmaceuta odabrana = servisZaFarmaceute.findOne(id);
		ZalbaNaFarmaceuta zalba = servisZaFarmaceute.odgovoriNaZalbu(odabrana, 
				apdejtovana.getOdgovor(), adminService.findOne( apdejtovana.getAdministratorId()));
		
		try {
			emailService.noviOdgovorNaZalbuNaFarmaceuta(zalba);
		} catch(Exception e){
			Thread.currentThread().interrupt();
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<ZalbaNaFarmaceutaDTO>(new ZalbaNaFarmaceutaDTO(zalba), HttpStatus.OK);
	}
	
	@GetMapping("/dermatolog")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<List<ZalbaNaDermatologaDTO>> getAllZND() {
		List<ZalbaNaDermatologa> zalbe = servisZaDermatologe.findAll();

		List<ZalbaNaDermatologaDTO> dtos = new ArrayList<ZalbaNaDermatologaDTO>();
		for (ZalbaNaDermatologa z : zalbe) {
			dtos.add(new ZalbaNaDermatologaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaDermatologaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/dermatolog/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<ZalbaNaDermatologaDTO> getZNDById(@PathVariable Integer id){
		ZalbaNaDermatologa zalba = servisZaDermatologe.findOne(id);
		return new ResponseEntity<ZalbaNaDermatologaDTO>(new ZalbaNaDermatologaDTO(zalba), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/dermatolog/neobradjene")
	public ResponseEntity<List<ZalbaNaDermatologaDTO>> getAllZNDNeobradjene() {
		List<ZalbaNaDermatologa> zalbe = servisZaDermatologe.findAllNeobradjene();

		List<ZalbaNaDermatologaDTO> dtos = new ArrayList<ZalbaNaDermatologaDTO>();
		for (ZalbaNaDermatologa z : zalbe) {
			dtos.add(new ZalbaNaDermatologaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaDermatologaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/dermatolog/obradjene/{id}")
	public ResponseEntity<List<ZalbaNaDermatologaDTO>> getAllZNDObradjeneByAdmin(@PathVariable Integer id) {
		List<ZalbaNaDermatologa> zalbe = servisZaDermatologe.findAllObradjeneByAdmin(id);

		List<ZalbaNaDermatologaDTO> dtos = new ArrayList<ZalbaNaDermatologaDTO>();
		for (ZalbaNaDermatologa z : zalbe) {
			dtos.add(new ZalbaNaDermatologaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaDermatologaDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/dermatolog/pacijent/{id}")
	public ResponseEntity<List<ZalbaNaDermatologaDTO>> getAllZNDByPacijent(@PathVariable Integer id) {
		List<ZalbaNaDermatologa> zalbe = servisZaDermatologe.findAllByPacijent(id);
		
		List<ZalbaNaDermatologaDTO> dtos = new ArrayList<ZalbaNaDermatologaDTO>();
		for (ZalbaNaDermatologa z : zalbe) {
			dtos.add(new ZalbaNaDermatologaDTO(z));
		}

		return new ResponseEntity<List<ZalbaNaDermatologaDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('PACIJENT')")
	@PostMapping("/dermatolog")
	public ResponseEntity<ZalbaNaDermatologaDTO> kreirajZalbuNaDermatologa(@RequestBody ZalbaNaDermatologaDTO dto) {
		ZalbaNaDermatologa kreirana = new ZalbaNaDermatologa(dto);
		kreirana.setDermatolog(dermatologService.findOne(dto.getDermatologId()));
		kreirana.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		ZalbaNaDermatologa zalba = servisZaDermatologe.dodajZalbu(kreirana);

		return new ResponseEntity<ZalbaNaDermatologaDTO>(new ZalbaNaDermatologaDTO(zalba), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PutMapping("/dermatolog/{id}")
	public ResponseEntity<ZalbaNaDermatologaDTO> odgovoriNaZalbuNaDermatologa(@PathVariable Integer id, @RequestBody  ZalbaNaApotekuDTO apdejtovana) {
		ZalbaNaDermatologa odabrana = servisZaDermatologe.findOne(id);
		ZalbaNaDermatologa zalba = servisZaDermatologe.odgovoriNaZalbu(odabrana,
				apdejtovana.getOdgovor(), adminService.findOne( apdejtovana.getAdministratorId()));
		
		try {
			emailService.noviOdgovorNaZalbuNaDermatologa(zalba);
		} catch(Exception e){
			Thread.currentThread().interrupt();
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<ZalbaNaDermatologaDTO>(new ZalbaNaDermatologaDTO(zalba), HttpStatus.OK);
	}

}
