package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;

public interface ZalbaNaFarmaceutaRepository extends JpaRepository<ZalbaNaFarmaceuta, Integer>{
	
	List<ZalbaNaFarmaceuta> findAll();
	
	ZalbaNaFarmaceuta findOneById(Integer id);
	
	List<ZalbaNaFarmaceuta> findByObradjenaFalse();
	
	List<ZalbaNaFarmaceuta> findAllByPacijentId(Integer id);
	
	List<ZalbaNaFarmaceuta> findByObradjenaTrueAndAdministratorId(Integer id);
}
