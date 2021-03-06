package com.backend.springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.repository.OsobaRepository;

@Service
public class OsobaService implements UserDetailsService{

	@Autowired
	private OsobaRepository osobaRep;

	public Collection<Osoba> findAll() {
		return osobaRep.findAll();
	}

	public Osoba addOsoba(Osoba osoba) {
		return osobaRep.save(osoba);
	}

	public Osoba findOne(Integer id) {
		return osobaRep.findOneById(id);
	}

//	public void delete(Integer id) {
//		osobaRep.delete(id);
//	}

	public Boolean promeniLozinku(int id, String staraLozinka, String novaLozinka) {
		Osoba o = findOne(id);
		String trenutnaLozinka = o.getPassword();
		if (trenutnaLozinka.equals(staraLozinka)) {
			o.setPassword(novaLozinka);
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Osoba osoba = osobaRep.findOneByMail(username);
		if(osoba == null) {
			throw new UsernameNotFoundException(String.format("Niko nije pronadjen sa email adresom '%s' .", username));
		}else {
			return osoba;
		}
	}

	public Osoba save(Osoba osoba) {
		return osobaRep.save(osoba);
	}

}
