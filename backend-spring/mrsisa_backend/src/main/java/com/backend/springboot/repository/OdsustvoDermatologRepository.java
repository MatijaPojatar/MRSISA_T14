package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;

public interface OdsustvoDermatologRepository extends JpaRepository<OdsustvoDermatolog, Integer> {
	
	@Query("select o from OdsustvoDermatolog o where o.dermatolog.id = :id and o.odobren=true and ((o.pocetak <= :start and o.kraj >= :end) or (o.pocetak >= :start and o.pocetak <= :end and o.kraj >= :end) or (o.kraj >= :start and o.kraj <= :end and o.pocetak <= :start))")
	public List<OdsustvoDermatolog> findExistInTime(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select o from OdsustvoDermatolog o where o.dermatolog.id = :id and o.odobren=true")
	List<OdsustvoDermatolog> findAllByDermatologId(@Param("id")Integer dermatologId);
	
	@Query("select o from OdsustvoDermatolog o where o.odobren=false and o.pocetak >= :start")
	public List<OdsustvoDermatolog> findAllNotOdobrenInFuture(@Param("start") LocalDateTime start);
	
	

}
