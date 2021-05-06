package com.backend.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.springboot.domain.Lek;


public interface LekRepository extends JpaRepository<Lek, Integer> {
	
	List<Lek> findAll();
	
	//List<Lek> findAllByLekId(Integer id);
	
	Lek save(Lek lek);
	
	Lek findOneById(Integer id);
	
	List<Lek> findAllBySnabdeveneApoteke_Id(Integer id);
	
	@Query("select l.zamenskiLekovi from Lek l where l.id=:id")
	List<Lek> findZamenski(@Param("id")Integer id);
	
	
	
}
