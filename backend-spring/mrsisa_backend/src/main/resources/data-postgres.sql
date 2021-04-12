insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 1','Test 1',1,'Test 1',1,'Test 1',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 2','Test 2',1,'Test 2',1,'Test 2',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 3','Test 3',1,'Test 3',1,'Test 3',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 4','Test 4',1,'Test 4',1,'Test 4',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 5','Test 5',1,'Test 5',1,'Test 5',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 6','Test 6',1,'Test 6',1,'Test 6',1);
insert into lekovi (napomena,naziv,oblik_leka,proizvodjac,rezim_izdavanja,sastav,vrsta_leka) values ('Test 7','Test 7',1,'Test 7',1,'Test 7',1);

insert into apoteke (adresa,drzava,grad,naziv,opis) values ('Adresa apoteke 1','Srbija','Novi Sad','Apoteka 1','Test');
insert into apoteke (adresa,drzava,grad,naziv,opis) values ('Adresa apoteke 2','Srbija','Novi Sad','Apoteka 2','Test apoteka 2');

insert into apoteke_lekovi (lek_id,apoteka_id) values (1,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (2,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (3,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (4,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (5,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (6,1);
insert into apoteke_lekovi (lek_id,apoteka_id) values (7,2);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka) values ('Adresa 1','0651234567','1990-01-01','Srbija','Novi Sad','Pera','Peric','perap@gmail.com','pera121',0,true);
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id) values (1,'16:00','08:00',1);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka) values ('Adresa 2','0651234568','1994-02-11','Srbija','Novi Sad','Marko','Markovic','markom@gmail.com','mare121',0,true);
insert into pacijenti (id,broj_poena,penali) values (2,0,0);


insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-06 14:00','2021-04-06 15:00',1,2);
insert into savetovanja (id,farmaceut_id) values (1,1);


insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka) values ('Adresa 3','0651234569','1990-02-02','Srbija','Novi Sad','Petar','Petrovic','petar@gmail.com','pera',0,true);
insert into dermatolozi (id,kraj_radnog_vremena,pocetak_radnog_vremena) values (3,'16:00','08:00');
insert into zaposlenje_dermatolog (dermatolog_id,apoteka_id) values (3,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id) values ('',false,'2021-04-06 11:00','2021-04-06 12:00',1,2);
insert into pregledi (id,dermatolog_id,cena) values (2,3,3000);

insert into magacini (id,apoteka_id) values (1,1);

insert into lek_magacin(id, pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values (1, '2021-04-06 14:00', 100, 100, 1, 1, false);
insert into lek_magacin(id, pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values (2, '2021-04-06 14:00', 200, 150, 2, 1, false);
insert into lek_magacin(id, pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values (3, '2021-04-06 14:00', 300, 10, 3, 1, true);
insert into lek_magacin(id, pocetak_vazenja, cena, kolicina, lek_id, magacin_id, obrisan) values (4, '2021-04-06 14:00', 400, 300, 4, 1, false);


insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka) values ('Adresa 4','0651234555','1987-02-01','Srbija','Novi Sad','Joca','Jokic','joca@gmail.com','joca123',0,true);
insert into administratori_apoteke (id,apoteka_id) values (4,1);
