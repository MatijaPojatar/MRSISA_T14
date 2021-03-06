
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Čuvati van domašaja dece','Bromazepam',2,'Hemofarm',0,'kukuruzni skrob, talk, natrijum-laurilsulfat', 0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ako simptomi traju duže od 5 dana, javite se lekaru','Fervex',0,'UPSA SAS',1,'saharoza, glukoza, etarska ulja limuna, cimeta i karanfilića',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ne konzumirati uz alkohol','Bactrim',1,'Galenika',0,'sufametoksazola, trimetoprima, povidon, magnezijum-stearat',0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Koristi se samo kao pomoćna terapija','Konjska mast',3,'Krauterhof',1,'divlji kesten, vinova loza, kamilica, alkohol',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Konsultovati se sa lekarom, pre upotrebe','Caffetin',2,'Alkaloid',1,'paracetamol, propifenazon, kofein, seskvihidrat',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Pročitati uputstvo pre upotrebe','Aspirin',2,'Alims',1,'paracetamol, propifenazon, kofein, seskvihidrat',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Pažljivo proćitati uputstvo','Brufen',0,'Alims',1,'celuloza, laktoza, ibuprofen',1);


insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Futoški put 10 ','Srbija','Novi Sad','Benu','Lepota i zdravlje.', 4.3);
insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Rumenačka 15','Srbija','Novi Sad','Apoteka Janković','Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja Apotekarske ustanove “Janković”. ', 4.1);
insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Bulevar oslobođenja 65','Srbija','Novi Sad','Galen','Galen Pharm Apoteka specijalizovana je za prodaju farmaceutskih proizvoda.', 3.8);

insert into apoteke_lekovi (lek_id,apoteka_id) values (1,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (2,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (3,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (4,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (5,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (3,2);
insert into apoteke_lekovi (lek_id,apoteka_id) values (4,2);
insert into apoteke_lekovi (lek_id,apoteka_id) values (4,3);

insert into role (name) values ('ROLE_PACIJENT');
insert into role (name) values ('ROLE_ADMIN_APOTEKE');
insert into role (name) values ('ROLE_ADMIN_SISTEMA');
insert into role (name) values ('ROLE_DERMATOLOG');
insert into role (name) values ('ROLE_FARMACEUT');
insert into role (name) values ('ROLE_DOBAVLJAC');

-- Lozinke su hesovane pomocu BCrypt algoritma https://www.bcryptcalculator.com/

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 1','0651234567','1990-01-01','Srbija','Novi Sad','Pera','Peric','perap@gmail.com','$2a$10$m45abtHydLkNASwq61g8r.NFJjn/UsRZIUFQVshzd1Ntr69c7ISx2',0,true, false, true); --sifra je pera121
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id, ocena) values (1,'16:00','08:00',1, 4.7);
insert into osoba_role (osoba_id, role_id) values (1, 5);	--role farmaceut
--markom
insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234568','1994-02-11','Srbija','Novi Sad','Marko','Markovic','markom@gmail.com','$2a$10$tChEqmOzl.2w6gMny6UXe.iH/Q6mb/zvoNLfxCXwVn5tu5Bxhpxhi',0,true, false, true); -- sifra je mare121
insert into pacijenti (id,broj_poena,penali) values (2,0,0);
insert into osoba_role (osoba_id, role_id) values (2, 1); --role pacijent

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (1,1);


insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 3','0651234569','1990-02-02','Srbija','Novi Sad','Petar','Petrovic','petar@gmail.com','$2a$10$KHKLhLY7PbDM6KM/mIZC7OBwgXBtTw7l.sB2o.SJW3H.6j8Gb8qJO',0,true, false, true); --sifra je pera
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena, ocena, broj_ocena, zbir_ocena) values (3,'16:00','08:00', 0.0, 0, 0);
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (3,1);
insert into zaposlenja (dermatolog_id,apoteka_id,pocetak_radnog_vremena,kraj_radnog_vremena, obrisan) values (3,1,'08:00','12:00', false);
insert into zaposlenja (dermatolog_id,apoteka_id,pocetak_radnog_vremena,kraj_radnog_vremena, obrisan) values (3,2,'12:00','16:00', false);
insert into osoba_role (osoba_id, role_id) values (3, 4); --role dermatolog

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-04-27 12:00','2021-04-27 13:00',1,2,1);
insert into pregledi (id,dermatolog_id,cena) values (2,3,3000);

insert into magacini (apoteka_id) values (1);
insert into magacini (apoteka_id) values (2);
insert into magacini (apoteka_id) values (3);

insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ('2021-04-06 14:00', 100, 100, 1, 1, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ('2021-04-06 14:00', 200, 0, 2, 1, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 300, 0, 3, 1, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 400, 300, 4, 1, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 300, 10, 5, 1, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 200, 150, 4, 2, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 300, 10, 3, 2, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 300, 0, 3, 3, false, 1);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan, version) values ( '2021-04-06 14:00', 400, 300, 5, 3, false, 1);





insert into cenovnik(pocetak_vazenja, cena, lekUMagacinu_id) values ('2021-04-06 14:00', 100, 1);
insert into cenovnik( pocetak_vazenja, cena, lekUMagacinu_id ) values ('2021-04-06 14:00', 200, 2 );
insert into cenovnik(pocetak_vazenja, cena,  lekUMagacinu_id ) values ( '2021-04-06 14:00', 300, 3 );
insert into cenovnik(pocetak_vazenja, cena,  lekUMagacinu_id ) values ( '2021-04-06 14:00', 400, 4);
insert into cenovnik(pocetak_vazenja, cena, lekUMagacinu_id ) values ( '2021-04-06 14:00', 300, 5);
insert into cenovnik(pocetak_vazenja, cena,  lekUMagacinu_id ) values ( '2021-04-06 14:00', 400, 6);
insert into cenovnik(pocetak_vazenja, cena,  lekUMagacinu_id ) values ( '2021-04-06 14:00', 100, 7);


insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 4','0651234555','1987-02-01','Srbija','Novi Sad','Joca','Jokic','joca@gmail.com','$2a$10$CfQ3WAfVoba7Do9ZwQpaF.sg73W2jfUWVWnaEzpsWFfduUWPe0tWa',0,true, false, true); -- sifra je joca123
insert into administratori_apoteke (id,apoteka_id) values (4,1);
insert into osoba_role (osoba_id, role_id) values (4, 2); --role admin apoteke

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 5','0651234555','1987-02-01','Srbija','Novi Sad','Jovana','Jovanovic','jovana@gmail.com','$2a$10$tb6IRTf7yq8wvTuO39JHvek6V6EAPQqXfxEmL59IpE6oAtbBtgBvW',1,true, false, true); --sifra je jovana123
insert into administratori_apoteke (id,apoteka_id) values (5, 2);
insert into osoba_role (osoba_id, role_id) values (5, 2); --role admin apoteke

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234567','1993-01-01','Srbija','Novi Sad','Maja','Majic','maja93@gmail.com','$2a$10$kzqghgO/vbMZuMNfDy6M9uBXqr3RsWRXtlR.DTkH7LPmM.BE.GeXO',1,true, false, true); -- sifra je maja123
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id, ocena) values (6,'16:00','08:00',1, 3.9);
insert into osoba_role (osoba_id, role_id) values (6, 5); --role farmaceut

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 6','0651234955','1987-04-01','Srbija','Novi Sad','Nikola','Nikola','admin@example.com','$2a$10$YIjbriG.mdkw6P6.tdr4LuwZdTp5RBZAjSo/0mpML8gevssilbjz6',0,true, false, true); --sifra je 123
insert into administratori_apoteke (id,apoteka_id) values (7,1);
insert into osoba_role (osoba_id, role_id) values (7, 2); -- admin-u apoteke dodeljujemo rolu admin apoteke

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 7','0651564568','1994-02-11','Srbija','Novi Sad','Imenko','Prezimic','imenkoprezimic94@gmail.com','$2a$10$YIjbriG.mdkw6P6.tdr4LuwZdTp5RBZAjSo/0mpML8gevssilbjz6',0,true, false, true); -- sifra je 123
insert into pacijenti (id,broj_poena,penali) values (8,0,0);--ima role
insert into osoba_role (osoba_id, role_id) values (8, 1); -- user-u dodeljujemo rolu pacijent

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa admina', '060000000', '1995-02-05', 'Srbija', 'Novi Sad', 'Sreten', 'Sreckovic', 'sreten@gmail.com', '$2a$10$s1GDRA8butHfGLEqYSJxHefKDzFw2pEESzGZRU7fm2R5L3kC6zSYu',0,true,false,true); --sifra je 123
insert into administratori_sistema (id, potpis) values (9, 'PotpisanAdminn');
insert into osoba_role (osoba_id, role_id) values (9, 3); --ima ulogu admin sistema

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-04-28 12:50','2021-04-28 14:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (3,6);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,version) values ('',false,'2021-07-16 15:00','2021-07-16 16:00',2,1);
insert into pregledi (id,dermatolog_id,cena) values (4,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-04-29 11:00','2021-04-29 12:00',1,8,1);
insert into savetovanja (id,farmaceut_id) values (5,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('Primer izvrsenog savetovanaja 1',true,'2021-04-26 11:00','2021-04-26 12:00',1,8,1);
insert into savetovanja (id,farmaceut_id) values (6,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('Primer izvrsenog savetovanaja 2',true,'2021-04-26 12:15','2021-04-26 13:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (7,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('Primer izvrsenog pregleda 1',true,'2021-05-25 12:00','2021-05-25 12:00',1,2,1);
insert into pregledi (id,dermatolog_id,cena) values (8,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('Primer izvrsenog pregleda 2',true,'2021-05-24 15:00','2021-05-24 16:00',1,8,1);
insert into pregledi (id,dermatolog_id,cena) values (9,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-05-27 15:00','2021-05-27 16:00',2,8,1);
insert into pregledi (id,dermatolog_id,cena) values (10,3,3000);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 9','0651234580','1996-12-02','Srbija','Novi Sad','Luka','Lukic','luka@gmail.com','$2a$10$UnO./eDTpKBzdcDio.khoO5/Egozb0LUrLR.ANtjFJQN7pVcEdGEy',0,true, false, true); --sifra je luka
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena, ocena, broj_ocena, zbir_ocena) values (10,'16:00','08:00', 0.0, 0, 0);
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (10,2);
insert into zaposlenja (dermatolog_id,apoteka_id,pocetak_radnog_vremena,kraj_radnog_vremena, obrisan) values (10,2,'08:00','12:00', false);
insert into osoba_role (osoba_id, role_id) values (10, 4);

insert into pacijent_alergije (pacijent_id,lek_id) values (2,1);
insert into zamenski_lekovi (lek_id,zamenski_id) values (2,1);
insert into zamenski_lekovi (lek_id,zamenski_id) values (2,4);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-05-25 18:00','2021-05-25 19:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (11,1);
insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-05-14 12:00','2021-05-14 13:00',1,2,1);
insert into pregledi (id,dermatolog_id,cena) values (12,3,3000);
insert into zalba_apoteka(pacijent_id,apoteka_id, tekst, obradjena, odgovor) values(2, 1, 'Kao pacijent id=2 zalim se na apoteku id=1',true, 'Kao admin odgovaram na zalbu pacijenta 2 na apoteku 1');
insert into zalba_apoteka(pacijent_id,apoteka_id, tekst, obradjena) values(2, 1, 'Kao pacijent id=2 po drugi put se zalim na apoteku id=1',false);

insert into zalba_dermatolog(pacijent_id,dermatolog_id, tekst, obradjena) values (2, 3, 'Kao pacijent id=2 zalim se na dermatologa id=3',false);

insert into zalba_farmaceut(pacijent_id,farmaceut_id, tekst, obradjena) values (2,6, 'Kao pacijent id=2 zalim se na farmaceuta id=6',false);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa dobavljaca','0651236680','1995-12-02','Srbija','Novi Sad','Mika','Mikic','imenkoprezimic94@gmaill.com','$2a$10$s1GDRA8butHfGLEqYSJxHefKDzFw2pEESzGZRU7fm2R5L3kC6zSYu',0,false, false, true); --sifra je 123
insert into dobavljaci(id, naziv_preduzeca) values (11, 'MExpress');
insert into osoba_role (osoba_id, role_id) values (11, 6);

insert into lekovi_na_stanju(kolicina, dobavljac_lekova_id, lek_id) values (100, 11, 5);

insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h22ad',0,'2021-07-27',2.0,1,2,1,1, '2021-06-25');
insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h23ad',0,'2021-05-27',2.0,2,2,4,1, '2021-05-20');
insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h24ad',0,'2021-07-13',2.0,1,2,1,1, '2021-06-26');


insert into upiti(kolicina, lek_id, magacin_id, obrisan) values (10, 3, 1, false);

insert into narudzbenice(rok, magacin_id, status, admin_id) values ('2021-05-15', 1, 0, 4);
insert into lek_narudzbenica(kolicina, lek_id, narudzbenica_id) values (10, 1, 1);
insert into narudzbenice(rok, magacin_id, status, admin_id) values ('2021-05-15', 1, 0, 4);
insert into lek_narudzbenica(kolicina, lek_id, narudzbenica_id) values (5, 7, 2);
insert into lek_narudzbenica(kolicina, lek_id, narudzbenica_id) values (11000, 1, 2);
insert into narudzbenice(rok, magacin_id, status, admin_id) values ('2021-06-20', 1, 0, 4);
insert into lek_narudzbenica(kolicina, lek_id, narudzbenica_id) values (50, 5, 3);

insert into ponude(cena, rok_isporuke, status, dobavljac_id, narudzbenica_id) values (50000, '2021-05-25', 0, 11, 1);
insert into ponude(cena, rok_isporuke, status, dobavljac_id, narudzbenica_id) values (58900, '2021-05-25', 2, 11, 2);


insert into odsustva_farmaceut(pocetak, kraj, status, farmaceut_id, apoteka_id) values ('2021-05-28', '2021-05-31', 2, 1, 1);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id, version) values ('2021-05-28', '2021-05-31', 2, 3, 1);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id, version) values ('2021-06-15', '2021-06-21', 2, 3, 1);
insert into odsustva_farmaceut(pocetak, kraj, status, farmaceut_id, apoteka_id) values ('2021-06-28', '2021-06-30', 2, 1, 1);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id, version) values ('2021-07-28', '2021-07-31', 2, 3, 1);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id, version) values ('2021-07-15', '2021-07-21', 2, 3, 1);

insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(1, '15% popusta na sve kreme', '2021-05-18', '2021-06-18');
insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(2, '3 + 1 gratis - Konjska mast', '2021-04-18', '2021-06-18');
insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(1, '-10% na cene savetovanja', '2021-05-22', '2021-06-12');

insert into pacijent_akcije(pacijent_id, apoteka_id) values(2, 2);

insert into termini(izvestaj, izvrsen, pocetak, kraj, apoteka_id,pacijent_id,version) values ('', false, '2021-06-20 12:00', '2021-06-20 12:45', 1,2,1);
insert into pregledi(id, dermatolog_id, cena) values (13, 3, 2500);
insert into termini(izvestaj, izvrsen, pocetak, kraj, apoteka_id,version) values ('', false, '2021-07-27 13:10', '2021-07-27 14:30', 1,1);
insert into pregledi(id, dermatolog_id, cena) values (14, 3, 3000);
insert into termini(izvestaj, izvrsen, pocetak, kraj, apoteka_id,version) values ('', false, '2021-05-19 10:45', '2021-05-19 11:30', 2,1);
insert into pregledi(id, dermatolog_id, cena) values (15, 10, 4000);
insert into termini(izvestaj, izvrsen, pocetak, kraj, apoteka_id,version,pacijent_id) values ('', false, '2021-05-21 09:00', '2021-05-21 09:45', 1,1,2);
insert into savetovanja(id, farmaceut_id) values (16, 1);

insert into erecepti( datum_izdavanja, pacijent_id, status) values ('2021-05-30', 2, 1);
insert into erecepti( datum_izdavanja, pacijent_id, status) values ('2021-05-29', 2, 1);

insert into lekovi_erecepta( erecept_id, kolicina, lek_id) values ( 1, 3, 2);
insert into lekovi_erecepta( erecept_id, kolicina, lek_id) values ( 1, 1, 3);
insert into lekovi_erecepta( erecept_id, kolicina, lek_id) values ( 2, 1, 6);
insert into lekovi_erecepta( erecept_id, kolicina, lek_id) values ( 2, 1, 7); --pacijent je alergican na lek sa idjem 1

insert into kategorije(broj_poena_start, naziv, procenat) values (0, 'Početnik',0.0 )

