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
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;

//@Repository
public interface OdsustvoDermatologRepository extends JpaRepository<OdsustvoDermatolog, Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="1000")})
	@Query("select o from OdsustvoDermatolog o where o.dermatolog.id = :id and o.status=0 and ((o.pocetak <= :start and o.kraj >= :end) or (o.pocetak >= :start and o.pocetak <= :end and o.kraj >= :end) or (o.kraj >= :start and o.kraj <= :end and o.pocetak <= :start))")
	public List<OdsustvoDermatolog> findExistInTime(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select o from OdsustvoDermatolog o where o.dermatolog.id = :id and o.status=0")
	List<OdsustvoDermatolog> findAllByDermatologId(@Param("id")Integer dermatologId);
	
	@Query("select o from OdsustvoDermatolog o where o.status=2 and o.pocetak >= :start")
	public List<OdsustvoDermatolog> findAllNotOdobrenInFuture(@Param("start") LocalDateTime start);
	
	

}
