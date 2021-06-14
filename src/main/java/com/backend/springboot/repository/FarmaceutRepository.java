package com.backend.springboot.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.backend.springboot.domain.Farmaceut;

public interface FarmaceutRepository extends JpaRepository<Farmaceut, Integer> {

	public List<Farmaceut> findAll();
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Farmaceut findOneById(Integer id);
	
	public List<Farmaceut> findAllByApotekaId(Integer id);
}
