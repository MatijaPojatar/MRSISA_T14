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

import com.backend.springboot.domain.Pregled;

public interface PregledRepository extends JpaRepository<Pregled, Integer> {
	
	public List<Pregled> findAllByDermatologId(Integer dermatologId);
	
	public Pregled findOneById(Integer id);
	
	public Pregled save(Pregled p);
	
	public List<Pregled> findAllByPacijentId(Integer id);
	
	public List<Pregled> findAllByPocetakGreaterThanEqual(LocalDateTime pocetak);

	public List<Pregled> findAllByDermatologIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(Integer dermatologId,Integer pacijentId,Integer apotekaId,LocalDateTime pocetak);
	
	public List<Pregled> findAllByApotekaIdAndPocetakGreaterThanEqualAndPacijentId(Integer apotekaId, LocalDateTime pocetak, Object object);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	@Query("select p from Pregled p where p.dermatolog.id = :id and ((p.pocetak >= :start and p.pocetak<= :end) or (p.kraj>= :start and p.kraj<=:end))")
	public List<Pregled> findInRangeForDermatolog(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select p from Pregled p where p.pacijent.id = :id and ((p.pocetak >= :start and p.pocetak<= :end) or (p.kraj>= :start and p.kraj<=:end))")
	public List<Pregled> findInRangeForPacijent(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select p from Pregled p where p.izvrsen = true and ((p.pocetak >= :start and p.pocetak<= :end) or (p.kraj>= :start and p.kraj<=:end))")
	public List<Pregled> findInRangeIzvrseni(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select p from Pregled p where p.izvrsen = true and p.apoteka.id = :apotekaId and ((p.pocetak >= :start and p.pocetak<= :end) or (p.kraj>= :start and p.kraj<=:end))")
	public List<Pregled> findInRangeIzvrseniApoteka(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end, @Param("apotekaId") Integer apotekaId);
}
