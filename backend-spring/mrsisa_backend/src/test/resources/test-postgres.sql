insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Čuvati van domašaja dece','Bromazepam',2,'Hemofarm',0,'kukuruzni skrob, talk, natrijum-laurilsulfat', 0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ako simptomi traju duže od 5 dana, javite se lekaru','Fervex',0,'UPSA SAS',1,'saharoza, glukoza, etarska ulja limuna, cimeta i karanfilića',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ne konzumirati uz alkohol','Bactrim',1,'Galenika',0,'sufametoksazola, trimetoprima, povidon, magnezijum-stearat',0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Koristi se samo kao pomoćna terapija','Konjska mast',3,'Krauterhof',1,'divlji kesten, vinova loza, kamilica, alkohol',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Konsultovati se sa lekarom, pre upotrebe','Caffetin',2,'Alkaloid',1,'paracetamol, propifenazon, kofein, seskvihidrat',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Pročitati uputstvo pre upotrebe','Aspirin',2,'Alims',1,'paracetamol, propifenazon, kofein, seskvihidrat',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Pažljivo proćitati uputstvo','Brufen',0,'Alims',1,'celuloza, laktoza, ibuprofen',1);



insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Sentandrejski put 10 ','Srbija','Subotica','Benu','Lepota i zdravlje.', 4.3);
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

insert into magacini (id,apoteka_id) values (1,1);
insert into magacini (id,apoteka_id) values (2,2);
insert into magacini (id,apoteka_id) values (3,3);

insert into upiti(kolicina, lek_id, magacin_id, obrisan) values (10, 3, 1, false);

insert into role (name) values ('ROLE_PACIJENT');
insert into role (name) values ('ROLE_ADMIN_APOTEKE');
insert into role (name) values ('ROLE_ADMIN_SISTEMA');
insert into role (name) values ('ROLE_DERMATOLOG');
insert into role (name) values ('ROLE_FARMACEUT');
insert into role (name) values ('ROLE_DOBAVLJAC');

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 1','0651234567','1990-01-01','Srbija','Novi Sad','Pera','Peric','perap@gmail.com','$2a$10$m45abtHydLkNASwq61g8r.NFJjn/UsRZIUFQVshzd1Ntr69c7ISx2',0,true, false, true); --sifra je pera121
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id, ocena) values (1,'16:00','08:00',1, 4.7);
insert into osoba_role (osoba_id, role_id) values (1, 5);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234568','1994-02-11','Srbija','Novi Sad','Marko','Markovic','markom@gmail.com','$2a$10$tChEqmOzl.2w6gMny6UXe.iH/Q6mb/zvoNLfxCXwVn5tu5Bxhpxhi',0,true, false, true); -- sifra je mare121
insert into pacijenti (id,broj_poena,penali) values (2,0,0);
insert into osoba_role (osoba_id, role_id) values (2, 1);

insert into erecepti( datum_izdavanja, pacijent_id, status) values ('2021-05-30', 2, 1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (1,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (2,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,1);
insert into savetovanja (id,farmaceut_id) values (3,1);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234567','1993-01-01','Srbija','Novi Sad','Maja','Majic','maja93@gmail.com','$2a$10$kzqghgO/vbMZuMNfDy6M9uBXqr3RsWRXtlR.DTkH7LPmM.BE.GeXO',1,true, false, true); -- sifra je maja123
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id, ocena) values (3,'16:00','08:00',1, 3.9);
insert into osoba_role (osoba_id, role_id) values (3, 5);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 3','0651234569','1990-02-02','Srbija','Novi Sad','Petar','Petrovic','petar@gmail.com','$2a$10$KHKLhLY7PbDM6KM/mIZC7OBwgXBtTw7l.sB2o.SJW3H.6j8Gb8qJO',0,true, false, true); --sifra je pera
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena, ocena) values (4,'16:00','08:00', 4.3);
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (4,1);
insert into zaposlenja (dermatolog_id,apoteka_id,pocetak_radnog_vremena,kraj_radnog_vremena, obrisan) values (4,1,'08:00','12:00', false);
insert into zaposlenja (dermatolog_id,apoteka_id,pocetak_radnog_vremena,kraj_radnog_vremena, obrisan) values (4,2,'12:00','16:00', false);
insert into osoba_role (osoba_id, role_id) values (4, 4); --role dermatolog

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (4,3);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,version) values ('',false,'2021-07-27 16:00','2021-07-27 17:00',1,1);
insert into savetovanja (id,farmaceut_id) values (5,3);

insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h22ad',0,'2021-06-27',2.0,1,2,1,1, '2021-06-02');
insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h23ad',0,'2021-05-27',2.0,2,2,4,1, '2021-05-20');
insert into rezervacije(code,status,datum,kolicina,apoteka_id,pacijent_id,lek_id,version, kreiranje) values ('h24ad',0,'2021-06-13',2.0,1,2,1,1, '2021-06-01');

insert into pacijent_alergije (pacijent_id,lek_id) values (2,1);

insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(1, '15% popusta na sve kreme', '2021-05-18', '2021-06-18');
insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(2, '3 + 1 gratis - Konjska mast', '2021-04-18', '2021-06-18');
insert into akcije(apoteka_id, opis, pocetak_vazenja, kraj_vazenja) values(1, '-10% na cene savetovanja', '2021-05-22', '2021-06-12');

insert into pacijent_akcije(pacijent_id, apoteka_id) values(2, 1);
insert into pacijent_akcije(pacijent_id, apoteka_id) values(2, 2);
insert into odsustva_farmaceut(pocetak, kraj, status, farmaceut_id, apoteka_id) values ('2021-06-28', '2021-06-30', 0, 1, 1);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id) values ('2021-06-28', '2021-06-30', 0, 4);
insert into odsustva_dermatolog(pocetak, kraj, status, dermatolog_id) values ('2021-07-15', '2021-07-21', 0, 4);
insert into odsustva_farmaceut(pocetak, kraj, status, farmaceut_id, apoteka_id) values ('2021-07-28', '2021-07-30', 2, 1, 1);
insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa dobavljaca','0651236680','1995-12-02','Srbija','Novi Sad','Mika','Mikic','imenkoprezimic94@gmaill.com','$2a$10$s1GDRA8butHfGLEqYSJxHefKDzFw2pEESzGZRU7fm2R5L3kC6zSYu',0,false, false, true); --sifra je 123
insert into dobavljaci(id, naziv_preduzeca) values (5, 'MExpress');
insert into osoba_role (osoba_id, role_id) values (5, 6);


insert into zalba_farmaceut(pacijent_id,farmaceut_id, tekst, obradjena) values (2,1, 'Tekst',false);

