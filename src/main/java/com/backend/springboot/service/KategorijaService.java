package com.backend.springboot.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Kategorija;
import com.backend.springboot.repository.KategorijaRepository;

@Service
public class KategorijaService {

	@Autowired
	private KategorijaRepository katRepo;
	
	public void deleteById(Integer id) {
		katRepo.deleteById(id);
		return;
	}
	
	public Kategorija findById(Integer id) {
		return katRepo.findOneById(id);
	}
	
	public Kategorija findByName(String name) {
		return katRepo.findOneByNaziv(name);
	}
	
	public List<Kategorija> findAll() {
		return katRepo.findAll();
	}
	
	public Kategorija save(Kategorija kat) {
		return katRepo.save(kat);
	}
	
	public Kategorija getByBrojPoena(Integer broj) {
		List<Kategorija> sve = katRepo.findAll();
		Collections.sort(sve,new SortByPoeni());
		
		for(Kategorija kat : sve) {
			if(broj <= kat.getBrojPoenaStart()) {
				return kat;
			}
		}
		
		return null;
	}
	class SortByPoeni implements Comparator<Kategorija> {

	    public int compare(Kategorija a, Kategorija b)
	    {
	        return b.getBrojPoenaStart() - a.getBrojPoenaStart();
	    }
	}
}
