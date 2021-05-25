package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Savetovanje;

public interface SavetovanjeRepository extends JpaRepository<Savetovanje, Integer> {
	
	public List<Savetovanje> findAllByPacijentId(Integer pacijentId);
	
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId);
	
	public Savetovanje findOneById(Integer id);
	
	public Savetovanje save(Savetovanje s);
	
	public List<Savetovanje> findAllByFarmaceutIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(Integer farmaceutId,Integer pacijentId,Integer apotekaId,LocalDateTime pocetak);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="1000")})
	@Query("select s from Savetovanje s where s.farmaceut.id = :id and ((s.pocetak >= :start and s.pocetak<= :end) or (s.kraj>= :start and s.kraj<=:end))")
	public List<Savetovanje> findInRangeForFarmaceut(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select s from Savetovanje s where s.pacijent.id = :id and ((s.pocetak >= :start and s.pocetak<= :end) or (s.kraj>= :start and s.kraj<=:end))")
	public List<Savetovanje> findInRangeForPacijent(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	

}
