package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.LekUMagacinu;

public interface LekUMagacinuRepository extends JpaRepository<LekUMagacinu, Integer> {
	public List<LekUMagacinu> findAllByLekId(Integer lekId);
	
	public List<LekUMagacinu> findAllByMagacinId(Integer MagacinId);
	
	public LekUMagacinu findOneByMagacinIdAndLekIdAndKolicinaGreaterThanEqual(Integer magacinId,Integer lekId,Double kolicina);
	
	public LekUMagacinu findOneByMagacinIdAndLekId(Integer magacinId,Integer lekId);
}
