package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.DermatologApoteka;

public interface DermatologApotekaRepository extends JpaRepository<DermatologApoteka, Integer> {
	
	public DermatologApoteka findOneByApotekaIdAndDermatologId(Integer apotekaId,Integer dermatologId);

}
