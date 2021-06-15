package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.domain.LekNaStanju;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Role;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.DobavljacDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.DobavljacService;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.NarudzbenicaService;
import com.backend.springboot.service.RoleService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/dobavljaci")
public class DobavljacController {

	@Autowired
	private DobavljacService dobavljacService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@GetMapping()
	public ResponseEntity<List<DobavljacDTO>> findAll(){
		List<Dobavljac> dobavljaci = dobavljacService.findAll();
		
		List<DobavljacDTO> dtos = new ArrayList<>();
		for(Dobavljac d : dobavljaci) {
			dtos.add(new DobavljacDTO(d));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PostMapping("/dobSignup")
	public ResponseEntity<DobavljacDTO> registrujDobavljaca(@RequestBody DobavljacDTO dobavljacDTO, UriComponentsBuilder ucBuilder){
		
		Dobavljac existDobavljac = this.dobavljacService.findByMail(dobavljacDTO.getMail());
		if(existDobavljac != null) {
			throw new ResourceConflictException(dobavljacDTO.getId(), "Email vec postoji");
		}
		
		Dobavljac novi = new Dobavljac(dobavljacDTO);
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_DOBAVLJAC"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		Dobavljac dobavljac = this.dobavljacService.save(novi);
		
		try {
			emailService.regByAdmin(dobavljac.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<DobavljacDTO>(new DobavljacDTO(dobavljac), HttpStatus.CREATED);
	} 
	
	@PreAuthorize("hasRole('DOBAVLJAC')")
	@GetMapping("/nazivPreduzeca/{id}")
	public ResponseEntity<String> getNazivPreduzeca(@PathVariable Integer id){
		Dobavljac d = dobavljacService.findOne(id);
		return new ResponseEntity<String>(d.getNazivPreduzeca(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('DOBAVLJAC')")
	@GetMapping("/{id}/mogucaNarudzbenica/{idNar}")
	public ResponseEntity<Boolean> isMogucaNarudzbenica(@PathVariable Integer id, @PathVariable Integer idNar){
		
		Dobavljac d = dobavljacService.findOne(id);
		Narudzbenica n = narudzbenicaService.findOne(idNar);
		
		for(LekIzNarudzbenice lekNar : n.getLekovi()) {
			Boolean pronadjen = false;
			for(LekNaStanju lekStanje : d.getLekoviNaStanju()) {
				if(lekNar.getLek().getId().equals(lekStanje.getLek().getId()) 
						&& lekStanje.getKolicina() >= lekNar.getKolicina()) {
					pronadjen = true;
					break;
				}
			}
			if(!pronadjen) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('DOBAVLJAC')")
	public ResponseEntity<DobavljacDTO> getOne(@PathVariable Integer id){
		Dobavljac d = dobavljacService.findOne(id);
		DobavljacDTO dto = new DobavljacDTO(d);
		
		return new ResponseEntity<DobavljacDTO>(dto,HttpStatus.OK);
	}
	

	@PutMapping("/save/{id}")
	@PreAuthorize("hasRole('DOBAVLJAC')")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody DobavljacDTO dto){
		Dobavljac d = dobavljacService.findOne(id);
		d.setAdresa(dto.getAdresa());
		d.setBrojTelefona(dto.getBrojTelefona());
		d.setDatumRodjenja(dto.getDatumRodjenja());
		d.setDrzava(dto.getDrzava());
		d.setGrad(dto.getGrad());
		d.setIme(dto.getIme());
		d.setPol(dto.getPol());
		d.setPrezime(dto.getPrezime());
		d.setNazivPreduzeca(dto.getNazivPreduzeca());
		
		dobavljacService.save(d);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	
	
	
}
