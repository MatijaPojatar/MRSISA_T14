package com.backend.springboot.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.ERecept;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekERecepta;
import com.backend.springboot.domain.ListaLekovaHelper;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.StatusErecepta;
import com.backend.springboot.dto.EReceptDTO;
import com.backend.springboot.dto.LekEReceptaDTO;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.service.EReceptService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.util.QRCodeGenerator;
import com.backend.springboot.util.QRCodeReader;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/erecepti")
public class EReceptController {
	
	@Autowired
	private EReceptService service;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private LekService lekService;

	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/apotekePacijenta/{id}")
	public ResponseEntity<List<MinimalApotekaDTO>> poseceneApoteke(@PathVariable Integer id){
		Set<Apoteka> apoteke = service.poseceneApoteke(id);
		//proveriti da li je prazna
		List<MinimalApotekaDTO> minimalne = new ArrayList<MinimalApotekaDTO>();
		for(Apoteka a : apoteke) {
			minimalne.add(new MinimalApotekaDTO(a));
		}
		
		return new ResponseEntity<List<MinimalApotekaDTO>>(minimalne, HttpStatus.OK);
	}
	

	
	@PreAuthorize("hasRole('PACIJENT')")
	@PostMapping("/upload/{id}") 
	public ResponseEntity<EReceptDTO> uploadAndReadQR(@RequestParam MultipartFile file, @PathVariable Integer id) throws Exception{
		
		ListaLekovaHelper helperLista = QRCodeReader.readQRCodeFromMultipartFile(file);
		
		
		if(service.qrExists(helperLista.getId())) {
			
			return new ResponseEntity<EReceptDTO>(HttpStatus.FORBIDDEN);
		}

		ERecept erecept = new ERecept();
		
		Pacijent pacijent = pacijentService.findOne(id);
		erecept.setPacijent(pacijent);
		erecept.setStatus(StatusErecepta.NOV);
		erecept.setQrId(helperLista.getId());
		
		erecept = service.save(erecept);	
		//sad imamo id erecepta
		
		List<LekERecepta> lekoviERecepta = new ArrayList<LekERecepta>();
			
		for(LekEReceptaDTO dto: helperLista.getLekovi()) {
			LekERecepta lekk = new LekERecepta(dto); //nema id dtoa u ovom trenutku
			
			lekk.setErecept(erecept);
			lekk.setLek(lekService.findOneByNaziv(dto.getNazivLeka()));
			//ako ne nadje, error?
			lekk.setKolicina(dto.getKolicina());
			
			lekoviERecepta.add(lekk);
		}
		erecept.setLekovi(lekoviERecepta);//id lekErecepta je i dalje null
		
		List<Lek> alergije = pacijentService.findAllAlergijeById(id);
	    for (Lek l : alergije) {
	    	for(LekERecepta lekER : lekoviERecepta) {
	    		if(lekER.getLek().getId() == l.getId()) {
		    		erecept.setStatus(StatusErecepta.ODBIJEN);
		    	}
	    	}
	    	
	    }
	    
	    if(lekoviERecepta.size() == 0) {
	    	erecept.setStatus(StatusErecepta.ODBIJEN);
	    }
	    if(erecept.getStatus() != StatusErecepta.ODBIJEN) {
	    	erecept.setDatumIzdavanja(LocalDate.now());
	    }
	    erecept = service.save(erecept);
	    //apdejtovan erecept sa lekovima i datumom izdavanja i mozda promenjenim statusom
		return new ResponseEntity<EReceptDTO>(new EReceptDTO(erecept),HttpStatus.OK);
	}
	
	@PostMapping("/generisi/{naziv}")
	public ResponseEntity<String> generisiQRkod(@RequestBody ListaLekovaHelper listaLekova, @PathVariable String naziv) throws WriterException, IOException{

		String path = "D:\\6semestar\\Projekatt\\MRSISA_T14\\QR_kodovi\\" + naziv + ".png";
		String json = new Gson().toJson(listaLekova);
		QRCodeGenerator.generateQRCodeImage(json,200, 200, path);
		
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	

	
	//FETCH APOTEKE SA OVIM LEKOVIMA I KOLICINAMA I DAJ CENU MOZDA NEGDE DRUGDE CONTROLLER
	
	/*@GetMapping()
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
	}*/
	
	@PreAuthorize("hasRole('PACIJENT')")
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
