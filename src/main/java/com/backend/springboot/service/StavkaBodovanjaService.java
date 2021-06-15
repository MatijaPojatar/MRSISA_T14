package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.StavkaBodovanja;
import com.backend.springboot.domain.TipStavkeBodovanja;
import com.backend.springboot.repository.StavkaBodovanjaRepository;

@Service
public class StavkaBodovanjaService {
	@Autowired
	private StavkaBodovanjaRepository repo;
	
	public StavkaBodovanja findById(Integer id) {
		return repo.findOneById(id);
	}
	
	public StavkaBodovanja save(StavkaBodovanja s) {
		return repo.save(s);
	}

	public StavkaBodovanja find(String tip, Integer id) {
		for(StavkaBodovanja s : repo.findAll()){
			if(s.getTip().equals(tip)) {
				if(s.getTip().equals(TipStavkeBodovanja.LEK)) {
					if(s.getIdLeka().equals(id)) {
						return s;
					}
				}else {
					return s;
				}
			}
		}
		return null;
	}
}
