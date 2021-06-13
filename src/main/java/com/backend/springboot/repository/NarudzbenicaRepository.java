package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.StatusNarudzbenice;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Integer> {

	public Narudzbenica save(Narudzbenica n);
	
	public Narudzbenica findOneById(Integer id);
	
	public List<Narudzbenica> findAllByStatus(StatusNarudzbenice status);
	
	public List<Narudzbenica> findAllByMagacinId(Integer magacinId);
	
	public List<Narudzbenica> findAllByStatusAndMagacinId(StatusNarudzbenice status, Integer magacinId);
	
}
