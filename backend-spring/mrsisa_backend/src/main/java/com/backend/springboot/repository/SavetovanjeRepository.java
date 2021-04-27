package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Savetovanje;

public interface SavetovanjeRepository extends JpaRepository<Savetovanje, Integer> {
	
	public List<Savetovanje> findAllByPacijentId(Integer pacijentId);
	
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId);
	
	public Savetovanje findOneById(Integer id);
	
	public Savetovanje save(Savetovanje s);
	
	public List<Savetovanje> findAllByFarmaceutIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(Integer farmaceutId,Integer pacijentId,Integer apotekaId,LocalDateTime pocetak);
	
	@Query("select s from Savetovanje s where (s.pocetak >= :start and s.pocetak<= :end) or (s.kraj>= :start and s.kraj<=:end)")
	public List<Savetovanje> findInRange(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	

}
