package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.OdsustvoDermatolog;

public interface OdsustvoDermatologRepository extends JpaRepository<OdsustvoDermatolog, Integer> {
	
	@Query("select o from OdsustvoDermatolog o where o.odobren=true and ((o.pocetak <= :start and o.kraj >= :end) or (o.pocetak >= :start and o.pocetak <= :end and o.kraj >= :end) or (o.kraj >= :start and o.kraj <= :end and o.pocetak <= :start))")
	public List<OdsustvoDermatolog> findExistInTime(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

}
