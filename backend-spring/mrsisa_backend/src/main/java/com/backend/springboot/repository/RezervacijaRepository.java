package com.backend.springboot.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.StatusRezervacije;

public interface RezervacijaRepository extends JpaRepository<RezervacijaLeka, Integer> {
	
	@Query("select r from RezervacijaLeka r where r.code=:code and r.status=0 and r.apoteka.id=:apotekaId")
	public RezervacijaLeka findOneActiveByCodeAndApoteka(@Param("code")String code,@Param("apotekaId")Integer apotekaId);
	
	@Query("select r from RezervacijaLeka r where r.code=:code and r.status=0")
	public RezervacijaLeka findOneActiveByCode(@Param("code")String code);
	
	public List<RezervacijaLeka> findAllByPacijentId(Integer pacijentId);
	
	@Query("select r from RezervacijaLeka r where r.status=0")
	public List<RezervacijaLeka> findAllActive();
	
	public List<RezervacijaLeka> findByPacijentIdAndStatus(Integer pacijentId, StatusRezervacije status);
	
	@Query("select r from RezervacijaLeka r where r.status=0 and r.apoteka.id=:apotekaId and ((r.kreiranje >= :start and r.kreiranje<= :end) or (r.kreiranje>= :start and r.kreiranje<=:end))")
	public List<RezervacijaLeka> findInRangeByApoteka(@Param("start") LocalDate start,@Param("end") LocalDate end, @Param("apotekaId") Integer apotekaId);
	
}
