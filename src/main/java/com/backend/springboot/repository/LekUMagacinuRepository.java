package com.backend.springboot.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.backend.springboot.domain.LekUMagacinu;

public interface LekUMagacinuRepository extends JpaRepository<LekUMagacinu, Integer> {
	public List<LekUMagacinu> findAllByLekId(Integer lekId);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<LekUMagacinu> findAllByMagacinId(Integer MagacinId);
	
	public LekUMagacinu findOneByMagacinIdAndLekIdAndKolicinaGreaterThanEqual(Integer magacinId,Integer lekId,Double kolicina);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public LekUMagacinu findOneByMagacinIdAndLekId(Integer magacinId,Integer lekId);
}
