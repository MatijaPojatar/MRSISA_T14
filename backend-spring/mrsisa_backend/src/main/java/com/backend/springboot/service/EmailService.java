package com.backend.springboot.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;

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
}
