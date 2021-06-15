package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer>{
	
	Kategorija findOneByNaziv(String naziv);
	
	Kategorija findOneById(Integer id);
	
	List<Kategorija> findAll();
	
	Kategorija save(Kategorija kat);
	
	
	}
