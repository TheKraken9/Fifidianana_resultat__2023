create database fifidianana;


create table bureau_de_vote(
    id varchar(100) primary key not null,
    region varchar(100) not null,
    district varchar(100) not null,
    commune varchar(100) not null,
    fokontany varchar(100) not null,
    centre_de_vote varchar(100) not null,
    nom varchar(100) not null
);

create table details_bureau_de_vote(
    id serial primary key not null,
    id_bureau_de_vote varchar(100) references bureau_de_vote(id),
    inscrit int not null,
    votant int not null,
    taux_de_participation float not null,
    blanc int not null,
    suffrage_exprime int not null
);

create table candidat(
    id varchar(100) primary key not null,
    nom varchar(100) not null,
    parti varchar(100) not null
);
insert into candidat(id, nom, parti) values('1', 'RAZAFINJOELINA Tahina', 'FTT');
insert into candidat(id, nom, parti) values('2', 'ANDRIANAINARIVELO Hajo Herivelona', 'MMM');
insert into candidat(id, nom, parti) values('3', 'RAJOELINA Andry Nirina', 'TGV');
insert into candidat(id, nom, parti) values('4', 'RATSIRAKA Iarovana Roland', 'MTS');
insert into candidat(id, nom, parti) values('5', 'RAVALOMANANA Marc', 'TIM');
insert into candidat(id, nom, parti) values('6', 'PARAINA Auguste Richard', 'TT');
insert into candidat(id, nom, parti) values('7', 'RAOBELINA ANDRIAMALALA Andry Tsiverizo', 'ARB');
insert into candidat(id, nom, parti) values('8', 'RAZAFINTSIANDRAOFA Jean Brunelle', 'APM');
insert into candidat(id, nom, parti) values('9', 'RATSIRAHONANA Lalaina Harilanto', 'Antoko Fihavanantsika An''i Kristy');
insert into candidat(id, nom, parti) values('10', 'RAJAONARIMAMPIANINA RAKOTOARIMANANA Hery-Martial', 'HVM');
insert into candidat(id, nom, parti) values('11', 'RADERANIRINA Sendrison Daniela', 'Fy-M');
insert into candidat(id, nom, parti) values('12', 'RATSIETISON Jean-Jacques Jedidia', 'FMI-Ma');
insert into candidat(id, nom, parti) values('13', 'RANDRIANASOLONIAIKO Siteny Thierry', 'Fitambolagnela/IAD , PSD , RPSD Vaovao , ABA, &parrainage de , 150 elus');

create table resultat_par_candidat(
    id serial primary key not null,
    id_bureau_de_vote varchar(100) references bureau_de_vote(id),
    id_candidat varchar(100) references candidat(id),
    voix int not null,
    pourcentage float not null
);

select resultat_par_candidat.id_bureau_de_vote as id_bureau_de_vote, resultat_par_candidat.id_candidat, voix, pourcentage, region, district, commune, fokontany, centre_de_vote, nom, from resultat_par_candidat join bureau_de_vote on bureau_de_vote.id = resultat_par_candidat.id_bureau_de_vote;