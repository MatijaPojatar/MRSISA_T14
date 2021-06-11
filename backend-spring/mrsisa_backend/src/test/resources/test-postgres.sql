insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values (' Futoški put 10 ','Srbija','Novi Sad','Benu','Lepota i zdravlje.', 4.3);
insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Rumenačka 15','Srbija','Novi Sad','Apoteka Janković','Poverenje, sigurnost i dostupnost su, već skoro 30 godina, glavna obeležja Apotekarske ustanove “Janković”. ', 4.1);
insert into apoteke (adresa,drzava,grad,naziv,opis, ocena) values ('Bulevar oslobođenja 65','Srbija','Novi Sad','Galen','Galen Pharm Apoteka specijalizovana je za prodaju farmaceutskih proizvoda.', 3.8);

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

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',true,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (1,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (2,1);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,1);
insert into savetovanja (id,farmaceut_id) values (3,1);

insert into osobe (adresa,broj_telefona,datum_rodjenja,drzava,grad,ime,prezime,mail,password,pol,promenjena_lozinka, obrisan, enabled) values ('Adresa 2','0651234567','1993-01-01','Srbija','Novi Sad','Maja','Majic','maja93@gmail.com','$2a$10$kzqghgO/vbMZuMNfDy6M9uBXqr3RsWRXtlR.DTkH7LPmM.BE.GeXO',1,true, false, true); -- sifra je maja123
insert into farmaceuti (id,kraj_radnog_vremena,pocetak_radnog_vremena,apoteka_id, ocena) values (3,'16:00','08:00',1, 3.9);
insert into osoba_role (osoba_id, role_id) values (3, 5);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,pacijent_id,version) values ('',false,'2021-04-27 16:00','2021-04-27 17:00',1,2,1);
insert into savetovanja (id,farmaceut_id) values (4,3);

insert into termini (izvestaj,izvrsen,pocetak,kraj,apoteka_id,version) values ('',false,'2021-07-27 16:00','2021-07-27 17:00',1,1);
insert into savetovanja (id,farmaceut_id) values (5,3);
