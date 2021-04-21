package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;

public interface PregledRepository extends JpaRepository<Pregled, Integer> {
	
	public List<Pregled> findAllByDermatologId(Integer dermatologId);
	
	public Pregled findOneById(Integer id);
	
	public Pregled save(Pregled p);
	
	public List<Pregled> findAllByDermatologIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(Integer dermatologId,Integer pacijentId,Integer apotekaId,LocalDateTime pocetak);
	
	@Query("select p from Pregled p where (p.pocetak >= :start and p.pocetak<= :end) or (p.kraj>= :start and p.kraj<=:end)")
	public List<Pregled> findInRange(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	

}
