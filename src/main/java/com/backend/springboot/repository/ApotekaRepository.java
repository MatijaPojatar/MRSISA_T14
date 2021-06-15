package com.backend.springboot.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.backend.springboot.domain.Apoteka;


public interface ApotekaRepository extends JpaRepository<Apoteka, Integer> {
	List<Apoteka> findAll();
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	Apoteka findOneById(Integer id);
	
	List<Apoteka> findByNazivIgnoringCaseAndGradIgnoringCase(String naziv, String grad);
	
	List<Apoteka> findAllByNazivIgnoringCase(String naziv);
	
	List<Apoteka> findByGradIgnoringCase(String grad);
}
