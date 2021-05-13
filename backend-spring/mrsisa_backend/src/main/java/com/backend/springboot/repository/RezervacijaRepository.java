package com.backend.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.RezervacijaLeka;

public interface RezervacijaRepository extends JpaRepository<RezervacijaLeka, Integer> {
	
	@Query("select r from RezervacijaLeka r where r.code=:code and r.status=0 and r.apoteka.id=:apotekaId")
	public RezervacijaLeka findOneActiveByCodeAndApoteka(@Param("code")String code,@Param("apotekaId")Integer apotekaId);
	
	@Query("select r from RezervacijaLeka r where r.code=:code and r.status=0")
	public RezervacijaLeka findOneActiveByCode(@Param("code")String code);
}
