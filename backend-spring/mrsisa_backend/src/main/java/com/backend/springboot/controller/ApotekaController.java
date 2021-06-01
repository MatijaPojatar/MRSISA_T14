package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Upit;
import com.backend.springboot.dto.ApotekaCenaDTO;
import com.backend.springboot.dto.ApotekaDTO;
import com.backend.springboot.dto.ApotekaMainInfoDTO;
import com.backend.springboot.dto.EReceptDTO;
import com.backend.springboot.dto.LekDTO;
import com.backend.springboot.dto.LekUMagacinuDTO;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.dto.UpitDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.MagacinService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/apoteke")
public class ApotekaController {
	@Autowired
	private ApotekaService apotekaService;

	private ArrayList<Apoteka> pronadjeneApoteke;
	
	@Autowired 
	private MagacinService magacinService;
	
	private ArrayList<LekUMagacinu> pronadjeniLekovi;
	private ArrayList<LekUMagacinuDTO> pronadjeniLekoviDTO;
	
	/*@GetMapping("/lekovi")
	public ResponseEntity<Collection<Lek>> getLekovi() {
		apotekaService.findAll();
		Collection<Lek> lekovi = apotekaService.preuzmiLekoveApoteke("ap2");
		
		return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK);
	}*/
	
	@PostMapping("/snabdeveneApoteke")
	public ResponseEntity<List<ApotekaCenaDTO>> getSnabdeveneApoteke(@RequestBody EReceptDTO dto){
		
		List<ApotekaCenaDTO> result = new ArrayList<ApotekaCenaDTO>();
		//za svaku apoteku
		for (Apoteka a : apotekaService.findAll()) {
			double cena = apotekaService.getUkupnaCena(a,dto);//proveri da li imaju sve lekove u magacinu i u tim kolicinama, ako da, usput odmah i cenu daj
			if(cena == 0.0) {
				continue;
			} 
			ApotekaCenaDTO ACdto = new ApotekaCenaDTO(a.getNaziv(), a.getId(), cena);
			result.add(ACdto);
		}
		
		return new ResponseEntity<List<ApotekaCenaDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ApotekaDTO> addApoteka(@RequestBody ApotekaDTO dto){
		try {
			System.out.println(dto.getNaziv() + "NAZIV");
			System.out.println(dto.getAdresa() + "ADRSEA");
            return new ResponseEntity<>(apotekaService.addApoteka(dto), HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping()
	public ResponseEntity<List<MinimalApotekaDTO>> findAll(){
		List<Apoteka> apoteke = apotekaService.findAll();
		
		List<MinimalApotekaDTO> dtos = new ArrayList<>();
		for(Apoteka a : apoteke) {
			dtos.add(new MinimalApotekaDTO(a));
		}
		
		return new ResponseEntity<List<MinimalApotekaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<ApotekaMainInfoDTO> getOne(@PathVariable Integer id){
		ApotekaMainInfoDTO apoteka = new ApotekaMainInfoDTO(apotekaService.findOne(id));
		return new ResponseEntity<ApotekaMainInfoDTO>(apoteka, HttpStatus.OK);
	}
	
	@PutMapping("/save/{id}")
	public ResponseEntity<Boolean> saveUpdates(@PathVariable Integer id, @RequestBody ApotekaMainInfoDTO dto){
		Apoteka a = apotekaService.findOne(id);
		a.setNaziv(dto.getNaziv());
		a.setOpis(dto.getOpis());
		a.setGrad(dto.getGrad());
		a.setDrzava(dto.getDrzava());
		a.setAdresa(dto.getAdresa());
		apotekaService.save(a);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PostMapping("/rezultat")
	public ResponseEntity<Collection<Apoteka>> pretrazi(String naziv, String grad) {
		pronadjeneApoteke = (ArrayList<Apoteka>) apotekaService.pronadjiApoteke(naziv, grad);
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Apoteka>> getApoteke() {
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@PutMapping("/obrisiLek/{id}")
	public ResponseEntity<String> obrisiLek(@PathVariable Integer id, @RequestBody String apotekaId) {
		magacinService.obrisiLek(id, Integer.parseInt(apotekaId));
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/dodajLek/{id}/{apotekaId}")
	public ResponseEntity<String> dodajLek(@PathVariable Integer id,@PathVariable Integer apotekaId, @RequestBody String cena ) {
		LocalDateTime pocetakVazenja = LocalDateTime.now();
		magacinService.dodajLek(pocetakVazenja, Double.parseDouble(cena), id, apotekaId, 0.0);
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izmeniLek/{apotekaId}")
	public ResponseEntity<String> izmeniLek(@PathVariable Integer apotekaId, @RequestBody LekUMagacinuDTO lek) {
		magacinService.izmeniLekUMagacinu(lek.getCena(), lek.getKolicina(), lek.getId(), apotekaId);
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/preuzmiLek/{id}")
	public ResponseEntity<LekUMagacinuDTO> preuzmiLek(@PathVariable Integer id, @RequestBody String apotekaId) {
		LekUMagacinu lek = magacinService.preuzmiJedanLekApoteke(id, Integer.parseInt(apotekaId));
		LekUMagacinuDTO lekdto= new LekUMagacinuDTO(lek);
		return new ResponseEntity<LekUMagacinuDTO>(lekdto,HttpStatus.OK);
	}
	
	@GetMapping("/stanje/{lekId}")
	public ResponseEntity<Boolean> proveriStanje(@PathVariable Integer lekId,@RequestParam Integer apotekaId,@RequestParam Double kolicina){
		System.out.println("========================="+lekId+" "+apotekaId+" "+kolicina+"==============================");
		Magacin m=magacinService.findOneByApotekaId(apotekaId);
		boolean check=magacinService.proveriStanje(m.getId(), lekId, kolicina);
		
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	@GetMapping("/upiti/{id}")
	public ResponseEntity<List<UpitDTO>> preuzmiUpite(@PathVariable Integer id){
		Magacin m=magacinService.findOneByApotekaId(id);
		
		List<Upit> upiti= magacinService.preuzmiUpite(m.getId());
		List<UpitDTO> dto = new ArrayList<UpitDTO>();
		for (Upit u: upiti) {
			dto.add(new UpitDTO(u));
		}
		
		return new ResponseEntity<List<UpitDTO>>(dto, HttpStatus.OK);
		
	}
	
	@GetMapping("/upitiLekovi/{id}")
	public ResponseEntity<List<LekDTO>> preuzmiLekoveUpita(@PathVariable Integer id){
		Magacin m=magacinService.findOneByApotekaId(id);
		
		List<Upit> upiti= magacinService.preuzmiUpite(m.getId());
		List<LekDTO> dto = new ArrayList<LekDTO>();
		for (Upit u: upiti) {
			dto.add(new LekDTO(u.getLek()));
		}
		
		return new ResponseEntity<List<LekDTO>>(dto, HttpStatus.OK);
		
	}
	
	
}
