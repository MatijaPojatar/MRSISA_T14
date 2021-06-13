package com.backend.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.StavkaCenovnika;

public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {
	public List<StavkaCenovnika> findAllByLekUMagacinuId(Integer id); 
	
	@Query("select p from StavkaCenovnika p where p.lekUMagacinu.id = :lekUMagacinuId and (p.pocetakVazenja <= :datum and p.krajVazenja>= :datum)")
	public StavkaCenovnika findOneInTimeByLekId(@Param("datum") LocalDateTime datum, @Param("lekUMagacinuId") Integer lekUMagacinuId);
}
