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

import com.backend.springboot.domain.OdsustvoFarmaceut;

public interface OdsustvoFarmaceutRepository extends JpaRepository<OdsustvoFarmaceut, Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	@Query("select o from OdsustvoFarmaceut o where o.farmaceut.id = :id and o.status=0 and ((o.pocetak <= :start and o.kraj >= :end) or (o.pocetak >= :start and o.pocetak <= :end and o.kraj >= :end) or (o.kraj >= :start and o.kraj <= :end and o.pocetak <= :start))")
	public List<OdsustvoFarmaceut> findExistInTime(@Param("id") Integer id,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	
	@Query("select o from OdsustvoFarmaceut o where o.farmaceut.id = :id and o.status=0")
	List<OdsustvoFarmaceut> findAllByFarmaceutId(@Param("id")Integer farmaceutId);
	
	@Query("select o from OdsustvoFarmaceut o where o.apoteka.id = :id and o.status=2 and o.pocetak >= :start")
	public List<OdsustvoFarmaceut> findAllNotOdobrenInFutureApoteka(@Param("id") Integer id,@Param("start") LocalDateTime start);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public OdsustvoFarmaceut findOneById(Integer id);
}
