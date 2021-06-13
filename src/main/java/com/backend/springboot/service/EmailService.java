package com.backend.springboot.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;
import com.backend.springboot.dto.EReceptDTO;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.dto.SavetovanjeDTO;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender jms;

	@Autowired
	private Environment env;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private FarmaceutService farmService;
	
	@Autowired
	private DermatologService dermService;

	@Async
	public void noviPregled(PregledDTO pregled) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Pacijent p=pacijentService.findOne(pregled.getPacijentId());
		Dermatolog d=dermService.findOne(pregled.getDermatologId());
		//mail.setTo(pregled.getPacijent().getMail());
	    mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazan novi termin pregleda");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je kreiran novi termin pregleda.\n"
				   + "\nPacijent: " + p.getIme() + " " + p.getPrezime() 
				   + "\nDermatolog: dr. " + d.getPrezime()
				   + "\nTermin: " + pregled.getStart().format(formatter) + " - " + pregled.getEnd().format(formatter)
				   + "\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void novoSavetovanje(SavetovanjeDTO savetovanje) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		Pacijent p=pacijentService.findOne(savetovanje.getPacijentId());
		Farmaceut f=farmService.findOne(savetovanje.getFarmaceutId());
		mail.setTo(p.getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Zakazan novi termin savetovanja");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je kreiran novi termin savetovanja.\n"
				   + "\nPacijent: " + p.getIme() + " " + p.getPrezime() 
				   + "\nFarmaceut: dr. " + f.getPrezime()
				   + "\nTermin: " + savetovanje.getStart().format(formatter) + " - " + savetovanje.getEnd().format(formatter)
				   + "\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void noviOdgovorNaZalbuNaApoteku(ZalbaNaApoteku zalba)  throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
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
	public void noviOdgovorNaZalbuNaFarmaceuta(ZalbaNaFarmaceuta zalba)  throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
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
	public void noviOdgovorNaZalbuNaDermatologa(ZalbaNaDermatologa zalba)  throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
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
		
		mail.setTo(osoba.getMail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Link za aktivaciju profila");
		mail.setText("Poštovani \n\nProfil za "+ osoba.getIme() +" "+osoba.getPrezime() + " možete aktivirati klikom na link: \n"+link);
	
		jms.send(mail);
	}
	
	@Async
	public void odobravanjeOdsustvaDermatolog(OdsustvoDermatolog odsustvo) throws MailException, InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odobren zahtev za odsustvo");
		mail.setText("Poštovani "+ odsustvo.getDermatolog().getIme() +"\n\nObaveštavamo Vas da je odobren Vaš zahtev za odmor u periodu: \n"+
				"\nPočetak: " + odsustvo.getPocetak().format(dtf)+
				"\nKraj: "+ odsustvo.getKraj().format(dtf) + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void odobravanjeOdsustvaFarmaceut(OdsustvoFarmaceut odsustvo) throws MailException, InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odobren zahtev za odsustvo");
		mail.setText("Poštovani "+ odsustvo.getFarmaceut().getIme() +"\n\nObaveštavamo Vas da je odobren Vaš zahtev za odmor u periodu: \n"+
				"\nPočetak: " + odsustvo.getPocetak().format(dtf)+
				"\nKraj: "+ odsustvo.getKraj().format(dtf) + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void odbijanjeOdsustvaDermatolog(OdsustvoDermatolog odsustvo) throws MailException, InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijen zahtev za odsustvo");
		mail.setText("Poštovani "+ odsustvo.getDermatolog().getIme() +"\n\nObaveštavamo Vas da je odbijen Vaš zahtev za odmor u periodu: \n"+
				"\nPočetak: " + odsustvo.getPocetak().format(dtf)+
				"\nKraj: "+ odsustvo.getKraj().format(dtf) + 
				"\n\nRazlog odbijanja: "+ odsustvo.getRazlog() + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void odbijanjeOdsustvaFarmaceut(OdsustvoFarmaceut odsustvo) throws MailException, InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Odbijen zahtev za odsustvo");
		mail.setText("Poštovani "+ odsustvo.getFarmaceut().getIme() +"\n\nObaveštavamo Vas da je odbijen Vaš zahtev za odmor u periodu: \n"+
				"\nPočetak: " + odsustvo.getPocetak().format(dtf)+
				"\nKraj: "+ odsustvo.getKraj().format(dtf) + 
				"\n\nRazlog odbijanja: "+ odsustvo.getRazlog() + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void odgovorPonuda(Integer narudzbenicaId, Integer ponudaId, String status) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Obrađena ponuda");
		mail.setText("Poštovani,  \n\nObaveštavamo Vas da je " + status +" Vaša ponuda. \n"+
				"\nNarudzbenica ID: " + narudzbenicaId+
				"\nPonuda ID: "+ ponudaId + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void novaAkcija(String nazivApoteke, AkcijaPromocija akcija, Pacijent pacijent) throws MailException, InterruptedException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(rl.getPacijent().getMail());
		mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Nova akcija");
		mail.setText("Poštovani " + pacijent.getIme() +",  \n\nObaveštavamo Vas da je u toku nova akcija u apoteci  " + nazivApoteke +" \n"+
				"\nOpis: " + akcija.getOpis()+
				"\nRok važenja: "+ akcija.getKrajVazenja().format(dtf) + 
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}

	@Async
	public void potvrdaKupovine(EReceptDTO dto, Pacijent pac, String nazivApoteke) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pac.getMail());
//		mail.setTo("sasicnatalija4@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrda o izdavanju lekova preko E-Recepta");
		mail.setText("Poštovani "+ pac.getIme() +",\n\nObaveštavamo Vas da je E-Recept sa šifrom " + dto.getId() + "uspešno obrađen u apoteci "+ nazivApoteke+
				"\n\nSrdačan pozdrav!");
		jms.send(mail);
	}
	
	@Async
	public void rezervacijaLeka(RezervacijaLeka rl) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		//mail.setTo(pregled.getPacijent().getMail());
	    mail.setTo("imenkoprezimic94@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Rezervisan lek");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je uspešno rezervisan lek.\n"
				   + "\nBroj rezervacije: " + rl.getCode()
				   + "\nPacijent: " + rl.getPacijent().getIme() + " " + rl.getPacijent().getPrezime() 
				   + "\nNaziv leka: " + rl.getLek().getNaziv()
				   + "\nProizvođač: " + rl.getLek().getProizvodjac()
				   + "\nApoteka: " + rl.getApoteka().getNaziv()
				   + "\nRok preuzimanja: " + rl.getDatum().format(formatter)
				   + "\n\nSrdačan pozdrav!");
		jms.send(mail);
	}

	@Async
	public void regByAdmin(String adresa) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(adresa);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registracija");
		mail.setText("Poštovani,\n\nObaveštavamo Vas da je registracija Vašeg profila na sistem za upravljanje apotekama uspešna.\n" +
					"Username je Vaša email adresa, a Vaša trenutna šifra je: 12345678 . Prilikom prve prijave, moraćete da postavite novu šifru."+
					"\n\nSrdačan pozdrav!");
		jms.send(mail);
		
	}
}
