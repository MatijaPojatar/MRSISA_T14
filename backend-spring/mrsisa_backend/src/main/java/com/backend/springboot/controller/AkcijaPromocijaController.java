package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.dto.AkcijaPromocijaDTO;
import com.backend.springboot.service.AkcijaPromocijaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/akcijaPromocija")
public class AkcijaPromocijaController {
	@Autowired
	private AkcijaPromocijaService apService;
	
	@GetMapping("/pacijent/{id}")
	public ResponseEntity<List<AkcijaPromocijaDTO>> getAPByPacijent(@PathVariable Integer id){
		List<AkcijaPromocija> akcijePromocije = apService.findAllByPacijent(id);
		
		List<AkcijaPromocijaDTO> akcijePromocijeDTO = new ArrayList<AkcijaPromocijaDTO>();
		for(AkcijaPromocija ap : akcijePromocije) {
			akcijePromocijeDTO.add(new AkcijaPromocijaDTO(ap));
		}

		return new ResponseEntity<List<AkcijaPromocijaDTO>>(akcijePromocijeDTO, HttpStatus.OK);	
	}
}
