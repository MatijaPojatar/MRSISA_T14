package com.backend.springboot.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender jms;

	@Autowired
	private Environment env;

	@Async
	public void noviPregled(Pregled pregled) throws MailException, InterruptedException {SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pregled.getPacijent().getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazan novi termin pregleda");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je kreiran novi termin pregleda.\n"
				   + "\nPacijent: " + pregled.getPacijent().getIme() + " " + pregled.getPacijent().getPrezime() 
				   + "\nFarmaceut: dr. " + pregled.getDermatolog().getPrezime()
				   + "\nTermin: " + pregled.getPocetak().format(formatter) + " - " + pregled.getKraj().format(formatter)
				   + "\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void novoSavetovanje(Savetovanje savetovanje) throws MailException, InterruptedException {SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(savetovanje.getPacijent().getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazan novi termin savetovanja");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je kreiran novi termin savetovanja.\n"
				   + "\nPacijent: " + savetovanje.getPacijent().getIme() + " " + savetovanje.getPacijent().getPrezime() 
				   + "\nFarmaceut: dr. " + savetovanje.getFarmaceut().getPrezime()
				   + "\nTermin: " + savetovanje.getPocetak().format(formatter) + " - " + savetovanje.getKraj().format(formatter)
				   + "\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void noviOdgovorNaZalbuNaApoteku(ZalbaNaApoteku zalba)  throws MailException, InterruptedException{SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(zalba.getPacijent().getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odgovor na žalbu");
		mail.setText("Poštovani \n\nObaveštavamo Vas da je vaša žalba pregledana i sledi odgovor koji smo Vam pripremili:\n"+
						"\nPacijent: " + zalba.getPacijent().getIme() + " " + zalba.getPacijent().getPrezime()+
						"\nApoteka: "+ zalba.getApoteka().getNaziv() +
						"\nTekst žalbe: " + zalba.getTekst() +
						"\nAutor odgovora: " + zalba.getAdministrator().getIme() + " " + zalba.getAdministrator().getPrezime() +
						"\n\nOdgovor: " + zalba.getOdgovor()+
						"\n\nSrdačan pozdrav!" +
						"\nPotpis: " + zalba.getAdministrator().getPotpis());
		jms.send(mail);
	}
	
	@Async
	public void noviOdgovorNaZalbuNaFarmaceuta(ZalbaNaFarmaceuta zalba)  throws MailException, InterruptedException{SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(zalba.getPacijent().getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odgovor na žalbu");
		mail.setText("Poštovani \n\nObaveštavamo Vas da je vaša žalba pregledana i sledi odgovor koji smo Vam pripremili:\n"+
						"\nPacijent: " + zalba.getPacijent().getIme() + " " + zalba.getPacijent().getPrezime()+
						"\nFarmaceut: "+ zalba.getFarmaceut().getIme() + " " + zalba.getFarmaceut().getPrezime()+ 
						"\nTekst žalbe: " + zalba.getTekst() +
						"\nAutor odgovora: " + zalba.getAdministrator().getIme() + " " + zalba.getAdministrator().getPrezime() +
						"\n\nOdgovor: " + zalba.getOdgovor()+
						"\n\nSrdačan pozdrav!" +
						"\nPotpis: " + zalba.getAdministrator().getPotpis());
		jms.send(mail);
	}
	
	@Async
	public void noviOdgovorNaZalbuNaDermatologa(ZalbaNaDermatologa zalba)  throws MailException, InterruptedException{SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(zalba.getPacijent().getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odgovor na žalbu");
		mail.setText("Poštovani \n\nObaveštavamo Vas da je vaša žalba pregledana i sledi odgovor koji smo Vam pripremili:\n"+
						"\nPacijent: " + zalba.getPacijent().getIme() + " " + zalba.getPacijent().getPrezime()+
						"\nDermatolog: "+ zalba.getDermatolog().getIme() + " " + zalba.getDermatolog().getPrezime() + 
						"\nTekst žalbe: " + zalba.getTekst() +
						"\nAutor odgovora: " + zalba.getAdministrator().getIme() + " " + zalba.getAdministrator().getPrezime() +
						"\n\nOdgovor: " + zalba.getOdgovor()+
						"\n\nSrdačan pozdrav!" +
						"\nPotpis: " + zalba.getAdministrator().getPotpis());
		jms.send(mail);
	}
	
	@Async
	public void preuzimanjeRezervacije(RezervacijaLeka rl) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Preuzimanje rezervacije leka");
		mail.setText("Poštovani "+rl.getPacijent().getIme()+"\n\nObaveštavamo Vas da je vaša rezervacija na lek: \n"+
				"\nNaziv: " + rl.getLek().getNaziv()+
				"\nProizvođač: "+ rl.getLek().getProizvodjac() + 
				"\nKoličina: " + rl.getKolicina() +
				"\n preuzeta od strane našeg farmaceuta apoteke "+rl.getApoteka().getNaziv() +
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}

	@Async
	public void aktivacija(Osoba osoba, String link) throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setText(osoba.getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Link za aktivaciju profila");
		mail.setText("Poštovani \n\nProfil za "+ osoba.getIme() +" "+osoba.getPrezime() + " možete aktivirati klikom na link: \n"+link);
	}
}
