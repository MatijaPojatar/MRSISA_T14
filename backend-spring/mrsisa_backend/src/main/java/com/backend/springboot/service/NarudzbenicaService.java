package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusNarudzbenice;
import com.backend.springboot.domain.StatusPonude;
import com.backend.springboot.domain.Upit;
import com.backend.springboot.dto.LekIzNarudzbeniceDTO;
import com.backend.springboot.repository.AdministratorApotekeRepository;
import com.backend.springboot.repository.LekIzNarudzbeniceRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.MagacinRepository;
import com.backend.springboot.repository.NarudzbenicaRepository;
import com.backend.springboot.repository.PonudaRepository;
import com.backend.springboot.repository.UpitRepository;

@Service
public class NarudzbenicaService {

	@Autowired
	private NarudzbenicaRepository narudzbenicaRep;

	@Autowired
	private PonudaRepository ponudeRep;

	@Autowired
	private LekIzNarudzbeniceRepository lekNarRep;

	@Autowired
	private MagacinRepository magacinRep;

	@Autowired
	private UpitRepository upitRep;

	@Autowired
	private AdministratorApotekeRepository adminRep;
	
	@Autowired
	private LekRepository lekRep;

	public Narudzbenica findOne(Integer id) {
		return narudzbenicaRep.findOneById(id);
	}

	public List<Ponuda> preuzmiPonude(Integer narudzbenicaId) {
		return ponudeRep.findAllByNarudzbenicaId(narudzbenicaId);
	}

	public List<LekIzNarudzbenice> preuzmiLekove(Integer narudzbenicaId) {
		return lekNarRep.findAllByNarudzbenicaId(narudzbenicaId);
	}

	public Narudzbenica dodajNarudzbenicu(Integer apotekaId, LocalDate rok, List<LekIzNarudzbeniceDTO> lekoviDTO,
			Integer adminId) {
		AdministratorApoteke admin = adminRep.findOneById(adminId);
		Narudzbenica narudzbenica = new Narudzbenica();
		Magacin mag = magacinRep.findOneByApotekaId(apotekaId);
		
		narudzbenica.setAdmin(admin);
		narudzbenica.setRok(rok);
		narudzbenica.setMagacin(mag);
		narudzbenica.setStatus(StatusNarudzbenice.CEKA_PONUDE);
		narudzbenicaRep.save(narudzbenica);
		
		List<LekIzNarudzbenice> lekovi = new ArrayList<LekIzNarudzbenice>();

		for (LekIzNarudzbeniceDTO l : lekoviDTO) {
			LekIzNarudzbenice lek = new LekIzNarudzbenice();
			lek.setKolicina(l.getKolicina());
			lek.setLek(lekRep.findOneById(l.getLekId()));
			lek.setNarudzbenica(narudzbenica);
			lekNarRep.save(lek);
			lekovi.add(lek);
		}

		narudzbenica.setLekovi(lekovi);
		

		
		for (LekIzNarudzbenice l:lekovi) { 
			Upit upit =upitRep.findOneByMagacinIdAndLekId(mag.getId(), l.getLek().getId());
			if(upit!= null)
				upitRep.delete(upit); 
		}
		 
		return narudzbenicaRep.save(narudzbenica);
	}

}
