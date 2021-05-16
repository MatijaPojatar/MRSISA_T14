package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Upit;

public interface UpitRepository extends JpaRepository<Upit, Integer> {
	public List<Upit> findAllByLekId(Integer lekId);
	
	public List<Upit> findAllByMagacinId(Integer magacinId);
	
	public Upit findOneByMagacinIdAndLekId(Integer magacinId,Integer lekId);
	
}
