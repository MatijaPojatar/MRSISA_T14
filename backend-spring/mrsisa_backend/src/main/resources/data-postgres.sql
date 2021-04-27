
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Čuvati van domašaja dece','Bromazepam',3,'Hemofarm',1,'kukuruzni skrob, talk, natrijum-laurilsulfat', 1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ako simptomi traju duže od 5 dana, javite se lekaru','Fervex',1,'UPSA SAS',0,'saharoza, glukoza, etarska ulja limuna, cimeta i karanfilića',0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Ne konzumirati uz alkohol','Bactrim',2,'Galenika',1,'sufametoksazola, trimetoprima, povidon, magnezijum-stearat',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Koristi se samo kao pomoćna terapija','Konjska mast',4,'Krauterhof',0,'divlji kesten, vinova loza, kamilica, alkohol',0);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Konsultovati se sa lekarom, pre upotrebe','Caffetin',3,'Alkaloid',1,'paracetamol, propifenazon, kofein, seskvihidrat',1);

insert into apoteke (adresa,drzava,grad,naziv,opis) values ('Adresa apoteke 1','Srbija','Novi Sad','Apoteka 1','Test');
insert into apoteke (adresa,drzava,grad,naziv,opis) values ('Adresa apoteke 2','Srbija','Novi Sad','Apoteka 2','Test apoteka 2');
insert into apoteke (adresa,drzava,grad,naziv,opis) values ('Adresa apoteke 3','Srbija','Novi Sad','Apoteka 3','Test apoteka 3');

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
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id) values (1,'16:00','08:00',1);
insert into osoba_role (osoba_id, role_id) values (1, 5);	--role farmaceut

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234568','1994-02-11','Srbija','Novi Sad','Marko','Markovic','markom@gmail.com','$2a$10$tChEqmOzl.2w6gMny6UXe.iH/Q6mb/zvoNLfxCXwVn5tu5Bxhpxhi',0,true, false, true); -- sifra je mare121
insert into pacijenti (id,broj_poena,penali) values (2,0,0);
insert into osoba_role (osoba_id, role_id) values (2, 1); --role pacijent

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,2);
insert into savetovanja (id,farmaceut_id) values (1,1);


insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 3','0651234569','1990-02-02','Srbija','Novi Sad','Petar','Petrovic','petar@gmail.com','$2a$10$KHKLhLY7PbDM6KM/mIZC7OBwgXBtTw7l.sB2o.SJW3H.6j8Gb8qJO',0,true, false, true); --sifra je pera
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena) values (3,'16:00','08:00');
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (3,1);
insert into osoba_role (osoba_id, role_id) values (3, 4); --role dermatolog

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-27 12:00','2021-04-27 13:00',1,2);
insert into pregledi (id,dermatolog_id,cena) values (2,3,3000);

insert into magacini (id,apoteka_id) values (1,1);
insert into magacini (id,apoteka_id) values (2,2);

insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ('2021-04-06 14:00', 100, 100, 1, 1, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ('2021-04-06 14:00', 200, 150, 2, 1, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ( '2021-04-06 14:00', 300, 10, 3, 1, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ( '2021-04-06 14:00', 400, 300, 4, 1, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ( '2021-04-06 14:00', 300, 10, 5, 1, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ( '2021-04-06 14:00', 200, 150, 4, 2, false);
insert into lek_magacin(pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values ( '2021-04-06 14:00', 300, 10, 3, 2, false);

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

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234567','1993-01-01','Srbija','Novi Sad','Maja','Majic','maja93@gmail.com','$2a$10$rTH2hJib.dPUAvqQmKzqqelhh5wsGZOSE0JvXKkf/H8TZuKjZCEB.',1,true, false, true); -- sifra je maja123
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id) values (6,'16:00','08:00',1);
insert into osoba_role (osoba_id, role_id) values (6, 5); --role farmaceut

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 6','0651234955','1987-04-01','Srbija','Novi Sad','Nikola','Nikola','admin@example.com','$2a$10$YIjbriG.mdkw6P6.tdr4LuwZdTp5RBZAjSo/0mpML8gevssilbjz6',0,true, false, true); --sifra je 123
insert into administratori_apoteke (id,apoteka_id) values (7,1);
insert into osoba_role (osoba_id, role_id) values (7, 2); -- admin-u apoteke dodeljujemo rolu admin apoteke

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 7','0651564568','1994-02-11','Srbija','Novi Sad','Imenko','Prezimic','sovara.sovic@gmail.com','$2a$10$YIjbriG.mdkw6P6.tdr4LuwZdTp5RBZAjSo/0mpML8gevssilbjz6',0,true, false, true); -- sifra je 123
insert into pacijenti (id,broj_poena,penali) values (8,0,0);--ima role
insert into osoba_role (osoba_id, role_id) values (8, 1); -- user-u dodeljujemo rolu pacijent

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa admina', '060000000', '1995-02-05', 'Srbija', 'Novi Sad', 'Sreten', 'Sreckovic', 'sreten@gmail.com', '$2a$10$s1GDRA8butHfGLEqYSJxHefKDzFw2pEESzGZRU7fm2R5L3kC6zSYu',0,true,false,true); --sifra je 123
insert into administratori_sistema (id, potpis) values (9, 'PotpisanAdminn');
insert into osoba_role (osoba_id, role_id) values (9, 3); --ima ulogu admin sistema

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-28 12:50','2021-04-28 14:00',1,2);
insert into savetovanja (id,farmaceut_id) values (3,6);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id) values ('',false,'2021-04-28 11:00','2021-04-28 12:30',1);
insert into savetovanja (id,farmaceut_id) values (4,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id) values ('',false,'2021-04-28 15:00','2021-04-28 16:00',1);
insert into pregledi (id,dermatolog_id,cena) values (5,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id) values ('',false,'2021-04-29 12:50','2021-04-29 14:00',1);
insert into savetovanja (id,farmaceut_id) values (6,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-29 11:00','2021-04-29 12:00',1,8);
insert into savetovanja (id,farmaceut_id) values (7,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('Primer izvrsenog savetovanaja 1',true,'2021-04-26 11:00','2021-04-26 12:00',1,8);
insert into savetovanja (id,farmaceut_id) values (8,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('Primer izvrsenog savetovanaja 2',true,'2021-04-26 12:15','2021-04-26 13:00',1,2);
insert into savetovanja (id,farmaceut_id) values (9,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('Primer izvrsenog pregleda 1',true,'2021-04-25 12:00','2021-04-25 12:00',1,2);
insert into pregledi (id,dermatolog_id,cena) values (10,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('Primer izvrsenog pregleda 2',true,'2021-04-24 15:00','2021-04-24 16:00',1,8);
insert into pregledi (id,dermatolog_id,cena) values (11,3,3000);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-27 15:00','2021-04-27 16:00',2,8);
insert into pregledi (id,dermatolog_id,cena) values (12,3,3000);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 9','0651234580','1996-12-02','Srbija','Novi Sad','Luka','Lukic','luka@gmail.com','$2a$10$UnO./eDTpKBzdcDio.khoO5/Egozb0LUrLR.ANtjFJQN7pVcEdGEy',0,true, false, true); --sifra je luka
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena) values (10,'16:00','08:00');
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (10,2);
insert into osoba_role (osoba_id, role_id) values (10, 4);
