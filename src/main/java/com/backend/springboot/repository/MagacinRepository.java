package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Magacin;

public interface MagacinRepository extends JpaRepository<Magacin, Integer> {
	public Magacin findOneByApotekaId(Integer apotekaId);
}
