package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Ponuda;

@Repository
public interface PonudaRepository extends JpaRepository<Ponuda, Integer> {
	
	Ponuda save(Ponuda p);
	
	Ponuda findOneById(Integer id);
	
	List<Ponuda> findAllByDobavljacId(Integer dobavljacId);
	
	List<Ponuda> findAllByNarudzbenicaId(Integer narudzbenicaId);
}
