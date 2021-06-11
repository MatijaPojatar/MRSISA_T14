package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.ERecept;

public interface EReceptRepository extends JpaRepository<ERecept, Integer>{

	public ERecept save(ERecept e);
	
	public ERecept findOneById(Integer id);
	
	public List<ERecept> findAllByPacijentId(Integer id);
	
	public ERecept findOneByQrId(Integer id);
}
