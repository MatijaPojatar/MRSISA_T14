package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.ParametriPretrageLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.StavkaCenovnika;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.dto.ApotekaCenaDTO;
import com.backend.springboot.dto.LekApotekaDTO;
import com.backend.springboot.dto.LekDTO;
import com.backend.springboot.dto.LekPrikazDTO;
import com.backend.springboot.dto.LekUMagacinuDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.MagacinService;
import com.backend.springboot.service.PacijentService;

@CrossOrigin(origins = { "http://localhost:8081" })
@RestController
@RequestMapping("/lekovi")
public class LekController {

	@Autowired
	private LekService lekService;
	@Autowired 
	private ApotekaService apotekaService;
	@Autowired 
	private MagacinService magacinService;
	@Autowired
	private PacijentService pacijentService;
	
	private ArrayList<Lek> pronadjeniLekovi;

	
	private ArrayList<LekUMagacinu> pronadjeniLekoviApoteka;
	private ArrayList<LekUMagacinuDTO> pronadjeniLekoviApotekaDTO;
	private boolean pretragaLekovaApoteka = false;
	
	@GetMapping("/minOcena/{ocena}")
	public ResponseEntity<List<LekPrikazDTO>> getLekoveSaVecomOcenom(@PathVariable Integer ocena){
		
		return new ResponseEntity<List<LekPrikazDTO>>(new ArrayList<LekPrikazDTO>(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/zaSifrarnik")
	public ResponseEntity<List<LekPrikazDTO>> getAllZASifrarnik(){
		List<Lek> lekovi = lekService.findAll();
		List<LekPrikazDTO> dto = new ArrayList<>();
		
		for (Lek l : lekovi) {
			dto.add(new LekPrikazDTO(l));
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<LekDTO>> getAll() {
		List<Lek> lekovi = lekService.findAll();
		List<LekDTO> dto = new ArrayList<>();
		
		for (Lek l : lekovi) {
			dto.add(new LekDTO(l));
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<Collection<Lek>> getLekovi() { Collection<Lek> lekovi =
	 * lekService.findAll();
	 * 
	 * return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK); }
	 */
	
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@GetMapping(value = "/vanApoteke/{apotekaId}")
	public ResponseEntity<List<LekDTO>> preuzmiLekoveVanApoteke(@PathVariable Integer apotekaId) {
		List<Lek> lekovi = lekService.preuzmiLekoveVanApoteke(apotekaId);
		List<LekDTO> dto = new ArrayList<>();
		
		for (Lek l : lekovi) {
			dto.add(new LekDTO(l));
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PostMapping()
	public ResponseEntity<LekDTO> dodajLek(@RequestBody Lek lekInfo) { //da bude dto?
		Lek lek = lekService.addLek(lekInfo);

		return new ResponseEntity<LekDTO>(new LekDTO(lek), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PutMapping()
	public ResponseEntity<LekDTO> izmeniLek(@RequestBody LekDTO lekInfo){
		Lek lek = lekService.updateLek(new Lek(lekInfo));
		
		return new ResponseEntity<LekDTO>(new LekDTO(lek), HttpStatus.OK);
	}

	@GetMapping("/apoteka/{id}")
	public ResponseEntity<Collection<LekUMagacinuDTO>> findAllByApotekaId(@PathVariable Integer id){
		if (pretragaLekovaApoteka) {
			pretragaLekovaApoteka = false;
			return new ResponseEntity<Collection<LekUMagacinuDTO>>(pronadjeniLekoviApotekaDTO, HttpStatus.OK);
			
		}
		Integer magacinId = apotekaService.findOne(id).getMagacin().getId();
		List<LekUMagacinu> rezultatPretrage=magacinService.preuzmiAktivneLekove(magacinId);
		ArrayList<LekUMagacinuDTO> dtoList=new ArrayList<LekUMagacinuDTO>();
		for(LekUMagacinu l:rezultatPretrage) {
			dtoList.add(new LekUMagacinuDTO(l));
		}
		
		return new ResponseEntity<Collection<LekUMagacinuDTO>>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/pretragaLekova/{apotekaId}")
	public ResponseEntity<Boolean> pretrazi(@PathVariable Integer apotekaId, @RequestBody ParametriPretrageLeka params) {
		
		pronadjeniLekoviApotekaDTO = new ArrayList<LekUMagacinuDTO>();
		pronadjeniLekoviApoteka = (ArrayList<LekUMagacinu>) magacinService.pretraziLekoveMagacina(params.getSifra(), params.getNaziv(),
				params.getOblik(), params.getVrsta(), params.getRezim(), apotekaId, params.getProizvodjac());
		if (pronadjeniLekoviApoteka.size() != 0) {
			pretragaLekovaApoteka = true;
			for (LekUMagacinu l : pronadjeniLekoviApoteka) {
				pronadjeniLekoviApotekaDTO.add(new LekUMagacinuDTO(l));
			}
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
			Integer magacinId = apotekaService.findOne(apotekaId).getMagacin().getId();
			List<LekUMagacinu> rezultatPretrage=magacinService.preuzmiAktivneLekove(magacinId);
			for(LekUMagacinu l:rezultatPretrage) {
				pronadjeniLekoviApotekaDTO.add(new LekUMagacinuDTO(l));
			}
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		
	}
	
	
	@GetMapping()
	public ResponseEntity<Collection<LekDTO>> getLekovi() {
		Collection<Lek> allLekovi = lekService.findAll();
		ArrayList<LekDTO> lekoviDTO = new ArrayList<LekDTO>();
		
		for (Lek lek : allLekovi) {
			lekoviDTO.add(new LekDTO(lek));
		}
		
		return new ResponseEntity<Collection<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/rezimi")
	public ResponseEntity<Collection<String>> getRezimi() {
		List<String> rezimi = Stream.of(RezimIzdavanja.values()).map(RezimIzdavanja::name).collect(Collectors.toList());

		return new ResponseEntity<Collection<String>>(rezimi, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')") 
	@GetMapping("/vrste")
	public ResponseEntity<Collection<String>> getVrste() {
		List<String> vrste = Stream.of(VrstaLeka.values()).map(VrstaLeka::name).collect(Collectors.toList());

		return new ResponseEntity<Collection<String>>(vrste, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')") 
	@GetMapping("/oblici")
	public ResponseEntity<Collection<String>> getOblici() {
		List<String> oblici = Stream.of(OblikLeka.values()).map(OblikLeka::name).collect(Collectors.toList());

		return new ResponseEntity<Collection<String>>(oblici, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN_SISTEMA','DERMATOLOG','FARMACEUT')")
	@GetMapping("/zamenski/{id}")
	public ResponseEntity<List<LekDTO>> getZamenskeZaLek(@PathVariable Integer id){
		try {
			List<Lek> zamenski = lekService.getZamenskeZaLek(id);
			
			List<LekDTO> dtos = new ArrayList<LekDTO>();
			
			for(Lek lek : zamenski) {
				dtos.add(new LekDTO(lek));
			}
			
			return new ResponseEntity<List<LekDTO>>(dtos,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<List<LekDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PutMapping("/zamenski/{id}")
	public ResponseEntity<Object> dodajZamenskeZaLek(@RequestBody List<Integer> zamenskiIds, @PathVariable int id){
		try {
			lekService.addZamenskeZaLek(id, zamenskiIds);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteLek(@PathVariable("id") int id) {
		try {
			lekService.deleteLek(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('DERMATOLOG','FARMACEUT')")
	@GetMapping("/zamenski/{id}/{apoteka_id}/{pacijent_id}")
	public ResponseEntity<LekDTO> getZamenskiLek(@PathVariable("id") Integer id,@PathVariable("apoteka_id") Integer apotekaId,@PathVariable("pacijent_id") Integer pacijentId){
		List<Lek> zamenski=lekService.findZamenski(id);
		Magacin m=magacinService.findOneByApotekaId(apotekaId);
		List<Lek> alergije=pacijentService.findAllAlergijeById(pacijentId);
		LekDTO dto=new LekDTO();
		dto.setId(-1);
		for(Lek l:zamenski) {
			if(magacinService.proveriStanje(m.getId(), l.getId(), (double) 1) && !alergije.contains(l)) {
				dto=new LekDTO(l);
				break;
			}
		}
		return new ResponseEntity<LekDTO>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/findOne/{id}")
	public ResponseEntity<LekDTO> findOne(@PathVariable Integer id){
		Lek l=lekService.findOneById(id);
		LekDTO dto=new LekDTO(l);
		
		return new ResponseEntity<LekDTO>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/findOneInApoteka/{id}")
	public ResponseEntity<LekUMagacinuDTO> findOneInApoteka(@PathVariable Integer id,@RequestParam Integer apotekaId){
		Magacin m=magacinService.findOneByApotekaId(apotekaId);
		LekUMagacinuDTO dto=new LekUMagacinuDTO();
		for(LekUMagacinu l:m.getLekovi()) {
			if(l.getLek().getId().equals(id)) {
				dto=new LekUMagacinuDTO(l);
				break;
			}
		}
		
		return new ResponseEntity<LekUMagacinuDTO>(dto,HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@GetMapping("/dostupnostLeka/{lekId}")
	public ResponseEntity<List<ApotekaCenaDTO>> getApotekeICeneZaLek(@PathVariable Integer lekId) {
		List<ApotekaCenaDTO> result = new ArrayList<ApotekaCenaDTO>();

		List<Apoteka> apoteke = apotekaService.findAll();
		for(Apoteka a : apoteke) {
			if(a.getMagacin() != null) {
				
				Magacin m = a.getMagacin();
				for (LekUMagacinu lekUM : m.getLekovi()) {
					
					if(lekUM.getLek().getId().equals(lekId)) {
						StavkaCenovnika sc = magacinService.preuzmiTrenutnuCenu(lekUM.getId());
						if(sc!= null) {
							ApotekaCenaDTO ACdto = new ApotekaCenaDTO(a.getNaziv(), a.getId(), sc.getCena());
							result.add(ACdto);
						}
					}
				}
				

			}
		}
		
		return new ResponseEntity<List<ApotekaCenaDTO>>(result, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/dostupni")
	public ResponseEntity<List<LekApotekaDTO>> getAllAvailable() {		
		List<Apoteka> apoteke = apotekaService.findAll();
		List<LekApotekaDTO> dtos = new ArrayList<LekApotekaDTO>();
		LekApotekaDTO la;
		
		for (Apoteka apoteka : apoteke) {
			for (Lek lek : apoteka.getLekovi()) {
				la = new LekApotekaDTO();
				la.setId(lek.getId());
				la.setNaziv(lek.getNaziv());
				la.setOblikLeka(lek.getOblikLeka());
				la.setApotekaId(apoteka.getId());
				la.setNazivApoteke(apoteka.getNaziv());
				dtos.add(la);
			}
		}
		
		for (LekApotekaDTO laDTO : dtos) {
			laDTO.setCena(magacinService.preuzmiTrenutnuCenu(laDTO.getId()).getCena());
		}
		
		return new ResponseEntity<List<LekApotekaDTO>>(dtos, HttpStatus.OK);
	}
}
