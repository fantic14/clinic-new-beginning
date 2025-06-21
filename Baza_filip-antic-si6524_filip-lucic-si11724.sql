CREATE DATABASE Psihoterapija;

USE Psihoterapija;

CREATE TABLE oblast(
                       oblast_id            INT          AUTO_INCREMENT,
                       naziv                VARCHAR(24)  NOT NULL,
                       PRIMARY KEY (oblast_id),
                       CONSTRAINT uq_naziv_oblast
                           UNIQUE (naziv),

                       INDEX idx_naziv_oblast (naziv)
);

CREATE TABLE usmerenje(
                          usmerenje_id         INT          AUTO_INCREMENT,
                          naziv                VARCHAR(24)  NOT NULL,
                          naziv_srodne_oblasti VARCHAR(24),
                          PRIMARY KEY (usmerenje_id),
                          CONSTRAINT uq_naziv_usmerenje
                              UNIQUE (naziv),

                          INDEX idx_naziv_usmerenje (naziv)
);

CREATE TABLE univerzitet(
                            univerzitet_id       INT          AUTO_INCREMENT,
                            naziv                VARCHAR(24)  NOT NULL,
                            usmerenje_id         INT,
                            PRIMARY KEY (univerzitet_id),
                            CONSTRAINT fk_univerzitet_usmerenje
                                FOREIGN KEY (usmerenje_id) REFERENCES usmerenje(usmerenje_id),
                            CONSTRAINT uq_naziv_univerzitet
                                UNIQUE (naziv),

                            INDEX idx_naziv_univerzitet (naziv)
);


CREATE TABLE fakultet(
                         fakultet_id          INT          AUTO_INCREMENT,
                         naziv                VARCHAR(24)  NOT NULL,
                         univerzitet_id       INT          NOT NULL,
                         PRIMARY KEY (fakultet_id),
                         CONSTRAINT fk_fakultet_univerzitet
                             FOREIGN KEY (univerzitet_id) REFERENCES univerzitet(univerzitet_id),
                         CONSTRAINT uq_naziv_fakultet
                             UNIQUE (naziv),

                         INDEX idx_naziv_fakultet (naziv)
);

CREATE TABLE fakultet_oblast(
                                fakultet_id INT NOT NULL,
                                oblast_id INT NOT NULL,
                                CONSTRAINT fk_fakultet
                                    FOREIGN KEY (fakultet_id) REFERENCES fakultet(fakultet_id),
                                CONSTRAINT fk_oblast
                                    FOREIGN KEY (oblast_id) REFERENCES oblast(oblast_id),
                                PRIMARY KEY (fakultet_id, oblast_id)
);

CREATE TABLE stepen_studija(
                               stepen_studija_id    INT    AUTO_INCREMENT,
                               naziv CHAR(10)   NOT NULL,
                               PRIMARY KEY (stepen_studija_id),
                               CONSTRAINT uq_naziv_stepen_studija
                                   UNIQUE (naziv),

                               INDEX idx_naziv_stepen_studija (naziv)
);

CREATE TABLE osoba(
                      osoba_id             INT           AUTO_INCREMENT,
                      ime                  VARCHAR(16)   NOT NULL,
                      prezime              VARCHAR(16)   NOT NULL,
                      jmbg                 CHAR(13)   NOT NULL,
                      datum_rodjenja       DATE          NOT NULL,
                      prebivaliste         VARCHAR(30)   NOT NULL,
                      broj_telefona        VARCHAR(16)   NOT NULL,
                      email                VARCHAR(48)   NOT NULL,
                      fakultet_id          INT           NOT NULL,
                      stepen_studija_id INT      NOT NULL,
                      PRIMARY KEY (osoba_id),
                      CONSTRAINT fk_osoba_fakultet
                          FOREIGN KEY (fakultet_id)          REFERENCES fakultet(fakultet_id),
                      CONSTRAINT fk_pen
                          FOREIGN KEY (stepen_studija_id) REFERENCES stepen_studija(stepen_studija_id),
                      CONSTRAINT uq_naziv_osoba
                          UNIQUE (ime,prezime, jmbg),

                      INDEX idx_naziv_osoba (ime,prezime)
);

CREATE TABLE oblast_psihoterapije(
                                     oblast_psihoterapije_id INT         AUTO_INCREMENT,
                                     naziv                   VARCHAR(24) NOT NULL,
                                     PRIMARY KEY (oblast_psihoterapije_id),
                                     CONSTRAINT uq_naziv_oblast_psihoterapije
                                         UNIQUE (naziv),

                                     INDEX idx_naziv_oblast_psihoterapije (naziv)
);

CREATE TABLE psihoterapeut(
                              psihoterapeut_id     INT,
                              datum_sertifikacije  DATE          NOT NULL,
                              oblast_psihoterapije_id INT        NOT NULL,
                              PRIMARY KEY (psihoterapeut_id),
                              CONSTRAINT fk_pt_osoba
                                  FOREIGN KEY (psihoterapeut_id)     REFERENCES osoba(osoba_id),
                              CONSTRAINT fk_pt_oblast
                                  FOREIGN KEY (oblast_psihoterapije_id) REFERENCES oblast_psihoterapije(oblast_psihoterapije_id)
);

CREATE TABLE supervizor(
                           supervizor_id        INT,
                           PRIMARY KEY (supervizor_id),
                           CONSTRAINT fk_supervizor_pt
                               FOREIGN KEY (supervizor_id) REFERENCES psihoterapeut(psihoterapeut_id)
);

CREATE TABLE centar_za_obuku(
                                centar_za_obuku_id   INT          AUTO_INCREMENT,
                                naziv                VARCHAR(24)  NOT NULL,
                                email                VARCHAR(48)  NOT NULL,
                                broj_telefona        VARCHAR(16)  NOT NULL,
                                adresa               VARCHAR(30)  NOT NULL,
                                PRIMARY KEY (centar_za_obuku_id),
                                CONSTRAINT uq_naziv_centar_za_obuku
                                    UNIQUE (naziv),

                                INDEX idx_naziv_centar_za_obuku (naziv)
);

CREATE TABLE kandidat(
                         kandidat_id          INT,
                         centar_za_obuku_id   INT          NOT NULL,
                         pocetak_obuke        DATE         NOT NULL,
                         kraj_obuke           DATE,
                         PRIMARY KEY (kandidat_id),
                         CONSTRAINT fk_kandidat_centar
                             FOREIGN KEY (centar_za_obuku_id) REFERENCES centar_za_obuku(centar_za_obuku_id),
                         CONSTRAINT fk_kandidat_osoba
                             FOREIGN KEY (kandidat_id) REFERENCES osoba(osoba_id)
);

CREATE TABLE kandidat_supervizor(
                                    kandidat_id          INT,
                                    supervizor_id        INT,
                                    pocetak_supervizije  DATE NOT NULL,
                                    kraj_supervizije     DATE,
                                    PRIMARY KEY (kandidat_id, supervizor_id),
                                    CONSTRAINT fk_ks_kandidat
                                        FOREIGN KEY (kandidat_id)   REFERENCES kandidat(kandidat_id),
                                    CONSTRAINT fk_ks_supervizor
                                        FOREIGN KEY (supervizor_id) REFERENCES supervizor(supervizor_id),
                                    CONSTRAINT uq_kandidat_supervizor
                                        UNIQUE (kandidat_id, pocetak_supervizije),

                                    INDEX idx_naziv_klijent (pocetak_supervizije)
);

CREATE TABLE klijent(
                        klijent_id           INT          AUTO_INCREMENT,
                        ime                  VARCHAR(16)  NOT NULL,
                        prezime              VARCHAR(16)  NOT NULL,
                        datum_rodjenja       DATE         NOT NULL,
                        pol                  CHAR(1)      NOT NULL,
                        email                VARCHAR(48)  NOT NULL,
                        broj_telefona        VARCHAR(16)  NOT NULL,
                        opis_problema        VARCHAR(250) NOT NULL,
                        nacin_kontakta       CHAR(10)     NOT NULL,
                        prva_seansa          TINYINT(1)   NOT NULL,
                        PRIMARY KEY (klijent_id),
                        CONSTRAINT uq_naziv_klijent
                            UNIQUE (ime,prezime),

                        INDEX idx_naziv_klijent (ime,prezime)
);

CREATE TABLE psiholoski_test(
                                psiholoski_test_id  INT          AUTO_INCREMENT,
                                oblast              VARCHAR(24)  NOT NULL,
                                naziv               VARCHAR(24)  NOT NULL,
                                cena                INT          NOT NULL,
                                rezultat            VARCHAR(100) NOT NULL,
                                PRIMARY KEY (psiholoski_test_id),
                                CONSTRAINT uq_naziv_psiholoski_test
                                    UNIQUE (naziv),

                                INDEX idx_naziv_psiholoski_test (naziv)
);

CREATE TABLE seansa(
                       seansa_id            INT          AUTO_INCREMENT,
                       beleske              VARCHAR(256) NOT NULL,
                       cena                 INT          NOT NULL,
                       poslednja_promena_cene DATE       NOT NULL,
                       priznao_krivicno_delo TINYINT(1)  NOT NULL,
                       PRIMARY KEY (seansa_id),
                       CONSTRAINT uq_naziv_seansa
                           UNIQUE (beleske),

                       INDEX idx_naziv_seansa (beleske)
);

CREATE TABLE klijent_seansa_psihoterapeut(
                                             klijent_id           INT,
                                             seansa_id            INT,
                                             osoba_id     	     INT,
                                             datum                DATE         NOT NULL,
                                             vreme                TIME         NOT NULL,
                                             trajanje             INT          NOT NULL,
                                             psiholoski_test_id   INT          NULL,
                                             PRIMARY KEY (klijent_id, seansa_id, osoba_id),
                                             CONSTRAINT fk_ksp_klijent
                                                 FOREIGN KEY (klijent_id)       REFERENCES klijent(klijent_id),
                                             CONSTRAINT fk_ksp_seansa
                                                 FOREIGN KEY (seansa_id)        REFERENCES seansa(seansa_id),
                                             CONSTRAINT fk_ksp_psihoterapeut
                                                 FOREIGN KEY (osoba_id) REFERENCES osoba(osoba_id),
                                             CONSTRAINT fk_ksp_test
                                                 FOREIGN KEY (psiholoski_test_id) REFERENCES psiholoski_test(psiholoski_test_id),
                                             CONSTRAINT uq_klijent_seansa_psihoterapeut
                                                 UNIQUE (klijent_id,datum,vreme),

                                             INDEX idx_klijent_seansa_psihoterapeut (datum)
);

CREATE TABLE valute(
                       valuta_id            INT         AUTO_INCREMENT,
                       naziv_valute         CHAR(30)     NOT NULL,
                       vrednost_u_dinarima    DECIMAL(10,2)          NOT NULL,
                       PRIMARY KEY (valuta_id),
                       CONSTRAINT uq_naziv_valute
                           UNIQUE (naziv_valute),

                       INDEX idx_naziv_valute (naziv_valute)
);

CREATE TABLE placanje(
                         placanje_id         INT          AUTO_INCREMENT,
                         klijent_id          INT          NOT NULL,
                         psiholoski_test_id  INT,
                         seansa_id			INT,
                         iznos               INT         NOT NULL,
                         strana_valuta       TINYINT(1)   NOT NULL,
                         svrha_placanja      VARCHAR(50)  NOT NULL,
                         kesh                TINYINT(1)   NOT NULL,
                         naziv_valute        CHAR(30)     NOT NULL,
                         datum_uplate		DATE         NOT NULL,
                         PRIMARY KEY (placanje_id),
                         CONSTRAINT fk_seansa
                             FOREIGN KEY (seansa_id) REFERENCES seansa(seansa_id),
                         CONSTRAINT fk_placanje_test
                             FOREIGN KEY (psiholoski_test_id) REFERENCES psiholoski_test(psiholoski_test_id),
                         CONSTRAINT fk_placanje_klijent
                             FOREIGN KEY (klijent_id)  REFERENCES klijent(klijent_id),
                         CONSTRAINT fk_placanje_valuta
                             FOREIGN KEY (naziv_valute) REFERENCES valute(naziv_valute),
                         CONSTRAINT uq_placanje
                             UNIQUE (klijent_id,seansa_id),

                         INDEX idx_placanje_datum (datum_uplate)
);

CREATE TABLE rate(
                     rata_id              INT,
                     prva_rata_iznos      INT          NOT NULL,
                     druga_rata_iznos     INT          NOT NULL,
                     datum_prve_rate		 DATE         NOT NULL,
                     datum_druge_rate	 DATE,
                     PRIMARY KEY (rata_id),
                     CONSTRAINT fk_rate_placanje
                         FOREIGN KEY (rata_id) REFERENCES placanje(placanje_id),
                     CONSTRAINT uq_rate
                         UNIQUE (prva_rata_iznos,druga_rata_iznos,datum_prve_rate),

                     INDEX idx_rate (datum_prve_rate)
);

CREATE TABLE promena_kursa_prema_dinaru(
                                           datum                DATE        NOT NULL,
                                           naziv_valute         CHAR(30)    NOT NULL,
                                           stara_vrednost_u_dinarima DECIMAL(10,2)      NOT NULL,
                                           PRIMARY KEY (datum, naziv_valute),
                                           CONSTRAINT uq_promena_kursa
                                               UNIQUE (naziv_valute,stara_vrednost_u_dinarima),

                                           INDEX idx_promena_kursa (datum)
);

CREATE TABLE objavljeni_podatci(
                                   objavljeni_podatci_id   INT         AUTO_INCREMENT,
                                   seansa_id               INT         NOT NULL,
                                   kada                    DATE        NOT NULL,
                                   kome                    VARCHAR(30) NOT NULL,
                                   zasto                   VARCHAR(100) NOT NULL,
                                   PRIMARY KEY (objavljeni_podatci_id),
                                   CONSTRAINT fk_seansa_id
                                       FOREIGN KEY (seansa_id) REFERENCES seansa(seansa_id),
                                   CONSTRAINT uq_objavljeni_podatci
                                       UNIQUE (seansa_id),

                                   INDEX idx_objavljeni_podatci (kada,kome)
);


INSERT INTO oblast (naziv) VALUES ('Kognitivna');
INSERT INTO oblast (naziv) VALUES ('Bihevioralna');
INSERT INTO oblast (naziv) VALUES ('Psihodinamska');
INSERT INTO oblast (naziv) VALUES ('Humanistička');
INSERT INTO oblast (naziv) VALUES ('Gestalt');
INSERT INTO oblast (naziv) VALUES ('Porodična');
INSERT INTO oblast (naziv) VALUES ('Dečja');
INSERT INTO oblast (naziv) VALUES ('Adolescentska');
INSERT INTO oblast (naziv) VALUES ('Integrativna');
INSERT INTO oblast (naziv) VALUES ('Egzistencijalna');


INSERT INTO oblast_psihoterapije (naziv) VALUES ('Kognitivna');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Bihevioralna');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Psihodinamska');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Humanistička');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Gestalt');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Sistemska');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Integrativna');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Egzistencijalna');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Analitička');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Razvojna');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Porodična');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Dečja');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Adolescentska');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Trauma-fokus');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Mindfulness');
INSERT INTO oblast_psihoterapije (naziv) VALUES ('Psihodrama');



INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Kognitivno', 'Psihologija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Bihevioralno', 'Psihologija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Psihodinamsko', 'Psihijatrija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Humanističko', 'Filozofija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Gestalt', 'Psihoterapija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Sistemsko', 'Porodična terapija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Integrativno', 'Psihoterapija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Egzistencijalno', 'Filozofija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Analitičko', 'Jungova psihologija');
INSERT INTO usmerenje (naziv, naziv_srodne_oblasti) VALUES ('Razvojno', 'Dečja psihologija');


INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivBG', 1);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivNS', 2);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivNI', 3);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivKG', 4);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivSU', 5);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivZR', 6);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivSM', 7);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivVA', 8);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivPO', 9);
INSERT INTO univerzitet (naziv, usmerenje_id) VALUES ('UnivUB', 10);


INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiBG', 1);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PsiholoskiBG', 1);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FPN_BG', 1);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiNS', 2);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PsiholoskiNS', 2);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PedagoskiNS', 2);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('MedicinskiNI', 3);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PsiholoskiNI', 3);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('DefektoloskiNI', 3);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PedagoskiKG', 4);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiKG', 4);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PsiholoskiKG', 4);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FPN_SU', 5);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('Socijalni_SU', 5);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('Humanisticki_SU', 5);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PsiholoskiZR', 6);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FPN_ZR', 6);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('UmetnickiZR', 6);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('HumanistickiSM', 7);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiSM', 7);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('SocijalniSM', 7);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('TeoloskiVA', 8);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiVA', 8);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PedagoskiVA', 8);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('DefektoloskiPO', 9);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('FilozofskiPO', 9);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('UmetnickiPO', 9);

INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('SocijalniUB', 10);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('HumanistickiUB', 10);
INSERT INTO fakultet (naziv, univerzitet_id) VALUES ('PedagoskiUB', 10);


INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (1, 1);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (2, 1);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (3, 2);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (4, 2);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (5, 3);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (6, 3);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (7, 4);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (8, 4);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (9, 5);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (10, 5);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (11, 6);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (12, 6);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (13, 7);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (14, 7);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (15, 8);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (16, 8);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (17, 9);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (18, 9);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (19, 10);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (20, 10);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (21, 1);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (22, 2);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (23, 3);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (24, 4);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (25, 5);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (26, 6);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (27, 7);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (28, 8);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (29, 9);
INSERT INTO fakultet_oblast (fakultet_id, oblast_id) VALUES (30, 10);


INSERT INTO stepen_studija (stepen_studija_id, naziv) VALUES
                                                          (1, 'Master'),(2, 'Akademske'),(3, 'Doktorske');


INSERT INTO osoba (ime, prezime, jmbg, datum_rodjenja, prebivaliste, broj_telefona, email, fakultet_id, stepen_studija_id) VALUES
    ('Ana', 'Jovanovic', '0101995123456', '1995-01-01', 'Beograd', '064111222', 'ana.jov@nb.rs', 1, 1),
    ('Marko', 'Petrovic', '1503888123456', '1988-03-15', 'Novi Sad', '063222333', 'marko.pet@nb.rs', 2, 2),
    ('Petra', 'Kovacevic', '2402199023456', '1990-02-24', 'Beograd', '062555666', 'petra@nb.rs', 3, 1),
    ('Dragan', 'Savic', '1102198523456', '1985-02-11', 'Novi Sad', '063666777', 'dragan@nb.rs', 4, 3),
    ('Ivana', 'Nikolic', '0506974123456', '1997-06-05', 'Kragujevac', '065123456', 'ivana.nik@nb.rs', 5, 2),
    ('Milan', 'Ilic', '0812926123456', '1992-12-08', 'Niš', '064777888', 'milan.ilic@nb.rs', 6, 1),
    ('Jelena', 'Stankovic', '2201983123456', '1983-01-22', 'Subotica', '062999111', 'jelena.sta@nb.rs', 7, 3),
    ('Nikola', 'Lazic', '3004994123456', '1994-04-30', 'Zrenjanin', '061888777', 'nikola.laz@nb.rs', 8, 1),
    ('Marija', 'Vukovic', '1206955123456', '1995-06-12', 'Čačak', '064321321', 'marija.vuk@nb.rs', 9, 2),
    ('Luka', 'Popovic', '1011907123456', '1990-11-10', 'Pirot', '065456456', 'luka.pop@nb.rs', 10, 3),
    ('Sara', 'Obradovic', '1902888123456', '1988-02-19', 'Vranje', '066678678', 'sara.obr@nb.rs', 11, 1),
    ('Stefan', 'Milosevic', '2103989123456', '1989-03-21', 'Pančevo', '067999555', 'stefan.mil@nb.rs', 12, 2),
    ('Tamara', 'Ristic', '0104999123456', '1999-04-01', 'Kruševac', '062333444', 'tamara.ris@nb.rs', 13, 1),
    ('Uros', 'Vasic', '0505956123456', '1995-05-05', 'Užice', '064112233', 'uros.vas@nb.rs', 14, 3),
    ('Natalija', 'Simic', '1508917123456', '1991-08-15', 'Zaječar', '063123123', 'natalija.sim@nb.rs', 15, 2),
    ('Filip', 'Radovanovic', '2303838123456', '1983-03-23', 'Sombor', '061555444', 'filip.rad@nb.rs', 16, 1),
    ('Jovana', 'Djordjevic', '1711949123456', '1994-11-17', 'Jagodina', '062777999', 'jovana.djo@nb.rs', 17, 3),
    ('Bogdan', 'Matic', '0101985123456', '1985-01-01', 'Valjevo', '065666777', 'bogdan.mat@nb.rs', 18, 2),
    ('Milica', 'Todorovic', '0606960123456', '1996-06-06', 'Loznica', '064000111', 'milica.tod@nb.rs', 19, 1),
    ('Nenad', 'Zivkovic', '3008877123456', '1987-08-30', 'Kikinda', '066444333', 'nenad.ziv@nb.rs', 20, 3),
    ('Kristina', 'Jaksic', '1111978123456', '1978-11-11', 'Bečej', '063999000', 'kristina.jak@nb.rs', 21, 2),
    ('Aleksandar', 'Tosic', '0902906123456', '1990-02-09', 'Negotin', '067123789', 'aleks.tos@nb.rs', 22, 1),
    ('Teodora', 'Ivanovic', '0712934123456', '1993-12-07', 'Kraljevo', '061876543', 'teodora.iva@nb.rs', 23, 3),
    ('Danilo', 'Stevanovic', '2510941123456', '1994-10-25', 'Sremska Mitrovica', '062456789', 'danilo.ste@nb.rs', 24, 2),
    ('Isidora', 'Mitrovic', '1808922123456', '1992-08-18', 'Bor', '065789654', 'isidora.mit@nb.rs', 25, 1),
    ('Relja', 'Pavlovic', '2809973123456', '1997-09-28', 'Leskovac', '064789123', 'relja.pav@nb.rs', 26, 3),
    ('Jasmina', 'Zivanovic', '0506909123456', '1990-06-05', 'Prokuplje', '063456123', 'jasmina.ziv@nb.rs', 27, 2),
    ('Vladimir', 'Djukic', '1206885123456', '1988-06-12', 'Apatin', '066789321', 'vladimir.dju@nb.rs', 28, 1),
    ('Lana', 'Veselinovic', '0708971123456', '1997-08-07', 'Aranđelovac', '061234567', 'lana.ves@nb.rs', 29, 2),
    ('Ilija', 'Grbic', '3011956123456', '1995-11-30', 'Zubin Potok', '062123999', 'ilija.grb@nb.rs', 30, 3);

INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (3, '2019-04-10', 5);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (7, '2020-06-22', 8);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (11, '2018-10-03', 3);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (14, '2021-01-15', 1);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (18, '2017-12-08', 9);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (20, '2022-05-19', 2);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (22, '2020-09-28', 6);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (25, '2016-07-07', 12);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (27, '2023-03-14', 11);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (30, '2015-11-02', 14);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (5, '2021-06-01', 7);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (8, '2020-02-20', 13);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (13, '2019-09-11', 4);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (24, '2022-08-08', 10);
INSERT INTO psihoterapeut (psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (29, '2018-12-30', 15);


INSERT INTO supervizor (supervizor_id) VALUES (3);
INSERT INTO supervizor (supervizor_id) VALUES (5);
INSERT INTO supervizor (supervizor_id) VALUES (7);
INSERT INTO supervizor (supervizor_id) VALUES (11);
INSERT INTO supervizor (supervizor_id) VALUES (13);
INSERT INTO supervizor (supervizor_id) VALUES (14);
INSERT INTO supervizor (supervizor_id) VALUES (20);
INSERT INTO supervizor (supervizor_id) VALUES (22);
INSERT INTO supervizor (supervizor_id) VALUES (25);
INSERT INTO supervizor (supervizor_id) VALUES (30);


INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Centar BG', 'bg@centar.rs', '0112345678', 'Kralja Petra 1, Beograd');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Novi Sad Edu', 'ns.edu@centar.rs', '0213456789', 'Bulevar Oslobođenja 12');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Psiholozi NI', 'kontakt@psi-ni.rs', '0184567890', 'Nikole Pašića 44, Niš');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Savetovaliste KG', 'info@kgcentar.rs', '034111222', 'Jovana Cvijića 22, KG');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Sombor Centar', 'sombor@psi.rs', '025222333', 'Venac Radomira Putnika 8');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Zrenjanin Edu', 'zr@edukacija.rs', '023456789', 'Trg Slobode 14, ZR');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Užice Edukacija', 'uz@psi.rs', '031654321', 'Kneza Miloša 9, Užice');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Pančevo PT', 'pt@pancevo.rs', '013333444', 'Vojvode Stepe 5, Pančevo');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Leskovac Centar', 'les@psi.rs', '016555666', 'Južnomoravskih brigada 33');
INSERT INTO centar_za_obuku (naziv, email, broj_telefona, adresa) VALUES ('Niš Psihoterapija', 'npt@nis.rs', '018123123', 'Generala Milojka Lešjanina 7');


INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (1, 1, '2021-01-10', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (2, 2, '2020-09-15', '2023-05-20');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (4, 3, '2022-02-01', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (6, 4, '2019-11-20', '2022-12-15');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (9, 5, '2023-03-01', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (10, 6, '2021-06-15', '2024-01-30');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (12, 7, '2020-10-10', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (15, 8, '2022-08-05', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (16, 9, '2018-04-18', '2022-04-18');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (17, 10, '2021-12-01', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (19, 1, '2019-03-25', '2022-06-30');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (21, 2, '2023-01-01', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (23, 3, '2020-05-10', '2023-05-10');
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (26, 4, '2022-09-01', NULL);
INSERT INTO kandidat (kandidat_id, centar_za_obuku_id, pocetak_obuke, kraj_obuke) VALUES (28, 5, '2021-07-15', NULL);


INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (1, 3, '2021-02-01', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (2, 5, '2020-10-10', '2023-06-01');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (4, 7, '2022-03-15', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (6, 11, '2019-12-01', '2022-12-31');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (9, 13, '2023-03-10', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (10, 14, '2021-07-01', '2024-01-15');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (12, 20, '2020-11-15', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (15, 22, '2022-09-05', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (16, 25, '2018-05-10', '2022-05-10');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (17, 30, '2022-01-20', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (19, 3, '2019-04-01', '2022-06-15');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (21, 5, '2023-02-01', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (23, 7, '2020-06-06', '2023-06-06');
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (26, 11, '2022-10-10', NULL);
INSERT INTO kandidat_supervizor (kandidat_id, supervizor_id, pocetak_supervizije, kraj_supervizije) VALUES (28, 13, '2021-08-08', NULL);


INSERT INTO klijent (ime, prezime, datum_rodjenja, pol, email, broj_telefona, opis_problema, nacin_kontakta, prva_seansa) VALUES
    ('Jelena', 'Milenkovic', '1990-03-21', 'Ž', 'jelena.mil@kl.rs', '063123456', 'Anksioznost i napadi panike tokom studija.', 'telefon', 1),
    ('Nikola', 'Radic', '1985-07-15', 'M', 'nikola.rad@kl.rs', '062555777', 'Poteškoće sa koncentracijom i produktivnošću.', 'email', 0),
    ('Marija', 'Savic', '1992-11-09', 'Ž', 'marija.sav@kl.rs', '064789123', 'Problemi u partnerskim odnosima.', 'lično', 1),
    ('Luka', 'Jovanovic', '1998-01-05', 'M', 'luka.jov@kl.rs', '061333222', 'Sumnja na depresivne epizode.', 'telefon', 1),
    ('Teodora', 'Zoric', '1995-06-14', 'Ž', 'teodora.zor@kl.rs', '065987321', 'Problemi sa samopouzdanjem i perfekcionizam.', 'email', 0),
    ('Stefan', 'Matic', '1989-09-23', 'M', 'stefan.mat@kl.rs', '062333111', 'Burnout sindrom povezan sa poslom.', 'telefon', 1),
    ('Ivana', 'Ristic', '1993-02-17', 'Ž', 'ivana.ris@kl.rs', '066111000', 'Strah od javnog nastupa.', 'lično', 0),
    ('Marko', 'Pavlovic', '1987-12-12', 'M', 'marko.pav@kl.rs', '063222999', 'Trauma nakon saobraćajne nesreće.', 'telefon', 1),
    ('Tamara', 'Lazic', '1996-04-04', 'Ž', 'tamara.laz@kl.rs', '064333888', 'Nesigurnost u donošenju važnih odluka.', 'email', 0),
    ('Aleksandar', 'Vukovic', '1990-10-30', 'M', 'alek.vuk@kl.rs', '065555222', 'Teškoće u izražavanju emocija.', 'telefon', 1),
    ('Milica', 'Peric', '1984-05-20', 'Ž', 'milica.per@kl.rs', '067444333', 'Stres usled gubitka bliske osobe.', 'lično', 0),
    ('Uros', 'Tadic', '1997-08-08', 'M', 'uros.tad@kl.rs', '062777666', 'Problemi sa besom i impulsivnošću.', 'email', 1),
    ('Natalija', 'Petrovic', '1991-03-29', 'Ž', 'natalija.pet@kl.rs', '061999123', 'Osećaj hronične usamljenosti.', 'telefon', 0),
    ('Filip', 'Knezevic', '1994-07-19', 'M', 'filip.kne@kl.rs', '063888777', 'Problemi sa adaptacijom na novu sredinu.', 'email', 1),
    ('Isidora', 'Bogdanovic', '1999-12-11', 'Ž', 'isidora.bog@kl.rs', '064666555', 'Nervoza i anksioznost pred ispite.', 'lično', 1);


INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Empatija', 'Big Five', 3201, 'Prosečna kognitivna sposobnost');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Pamćenje', 'Raven', 2196, 'Umeren anksiozni odgovor');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Emocionalna', 'EQ Test', 2938, 'Nizak rezultat na skali depresije');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Stres', 'Beck Anx.', 2874, 'Snažno samopouzdanje');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Kognitivna', 'Beck Dep.', 2132, 'Odlična pažnja i fokus');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Anksioznost', 'PSS Skala', 1729, 'Visoka emocionalna stabilnost');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Ličnost', 'Stroop', 2775, 'Umeren anksiozni odgovor');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Empatija', 'Digit Span', 2642, 'Nizak rezultat na skali depresije');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Empatija', 'Self-Esteem', 2784, 'Blago smanjen kapacitet radnog pamćenja');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Ličnost', 'Empathy Quot.', 1891, 'Emotivna zrelost u granicama normale');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Kognitivna', 'MMPI-2', 1895, 'Umeren anksiozni odgovor');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Stres', 'MBTI', 2304, 'Prosečna kognitivna sposobnost');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Samopouzdanje', '16PF', 3381, 'Visoka emocionalna stabilnost');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Pamćenje', 'WISC-IV', 1586, 'Nizak rezultat na skali depresije');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Ličnost', 'TAT', 3040, 'Empatično ponašanje prisutno');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Ličnost', 'CPT', 2536, 'Odlična pažnja i fokus');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Stres', 'WAIS-IV', 2709, 'Blago smanjen kapacitet radnog pamćenja');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Empatija', 'Bender', 2921, 'Prosečna kognitivna sposobnost');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Depresija', 'Rorschach', 3099, 'Empatično ponašanje prisutno');
INSERT INTO psiholoski_test (oblast, naziv, cena, rezultat) VALUES ('Kognitivna', 'NEO-PI', 2148, 'Nizak rezultat na skali depresije');


INSERT INTO seansa (beleske, cena, poslednja_promena_cene, priznao_krivicno_delo) VALUES
    ('Uvodna seansa – upoznavanje i postavljanje ciljeva.', 2500, '2023-01-10', 0),
    ('Analiza snova iz prethodne nedelje.',                  2800, '2023-02-14', 0),
    ('Razgovor o stresu na radnom mestu.',                  2700, '2023-03-01', 0),
    ('Rad na emocionalnoj regulaciji.',                        3000, '2023-01-22', 0),
    ('Priznanje u vezi nasilnog incidenta iz prošlosti.',                        3200, '2023-02-01', 1),
    ('Vežbanje tehnika disanja i relaksacije.',                                  2600, '2023-01-30', 0),
    ('Otkrivanje disfunkcionalnih obrazaca ponašanja.',                          2900, '2023-03-05', 0),
    ('Tematizovanje osećaja usamljenosti.',                                      2500, '2023-01-17', 0),
    ('Rad na osećaju krivice zbog prošlih izbora.',                              3100, '2023-03-10', 0),
    ('Razrada porodičnih odnosa i konflikata.',                                  3000, '2023-02-28', 0),
    ('Priznao krivično delo krađe u mladosti.',                                  3300, '2023-03-18', 1),
    ('Simulacija konflikta sa roditeljima.',                                    2700, '2023-03-15', 0),
    ('Razgovor o identitetu i samoprihvatanju.',                                 2600, '2023-03-20', 0),
    ('Ponavljanje tehnika kognitivne restrukturacije.',                          2400, '2023-01-25', 0),
    ('Obrada trauma vezane za saobraćajnu nesreću.',                             3500, '2023-04-01', 0),
    ('Iskaz o fizičkom napadu u alkoholisanom stanju.',                          3400, '2023-03-29', 1),
    ('Vežbe komunikacije za asertivnost.',                                       2500, '2023-02-12', 0),
    ('Seansa relaksacije i fokusiranja.',                                        2300, '2023-02-18', 0),
    ('Refleksija o partnerskim odnosima.',                                       2900, '2023-02-25', 0),
    ('Razrada osećaja bespomoćnosti.',                                           3100, '2023-03-07', 0),
    ('Tehnike za prevazilaženje perfekcionizma.',                                2700, '2023-03-22', 0),
    ('Iskaz o prevari i posledicama istog.',                                     3300, '2023-04-05', 1),
    ('Osvrt na detinjstvo i razvoj ličnosti.',                                   2600, '2023-02-10', 0),
    ('Trening usmerene pažnje (mindfulness).',                                    2400, '2023-03-14', 0),
    ('Vežba neverbalne komunikacije.',                                           2550, '2023-03-17', 0),
    ('Razrada ciljeva za naredni period.',                                       2500, '2023-04-02', 0),
    ('Kognitivna mapa anksioznih misli.',                                        2700, '2023-02-28', 0),
    ('Dnevnik osećanja i njegova analiza.',                                      2650, '2023-03-03', 0),
    ('Iskaz o agresivnom ponašanju u porodici.',                                 3500, '2023-03-25', 1),
    ('Diskusija o razočaranjima u bliske osobe.',                                2700, '2023-02-20', 0),
    ('Samopouzdanje i postavljanje granica.',                                    2850, '2023-03-12', 0),
    ('Nesvesni obrasci u izboru partnera.',                                       3000, '2023-04-07', 0),
    ('Iskaz o prikrivanju dela u grupi vršnjaka.',                               3200, '2023-02-22', 1),
    ('Razgovor o očekivanjima od terapeuta.',                                    2600, '2023-03-06', 0),
    ('Razrada osećaja tuge zbog gubitka.',                                       2800, '2023-02-16', 0),
    ('Vežba asocijacija i analiza rezultata.',                                   2450, '2023-03-11', 0),
    ('Praktikovanje tehnike "stojeći dijalog".',                                  2550, '2023-03-09', 0),
    ('Iskaz o falsifikovanju dokumenta.',                                        3600, '2023-04-10', 1),
    ('Rad sa figurama unutrašnjih uloga.',                                       2700, '2023-03-28', 0),
    ('Aktivno slušanje u simulaciji dijaloga.',                                  2600, '2023-03-19', 0),
    ('Tehnike stabilizacije u anksioznim stanjima.',                             2800, '2023-02-27', 0),
    ('Priznao krađu imovine bliske osobe.',                                      3500, '2023-04-08', 1),
    ('Simulacija sukoba sa nadređenima.',                                        2900, '2023-03-23', 0),
    ('Vežbanje strategija za prevazilaženje socijalne anksioznosti.',            2750, '2023-02-14', 0),
    ('Obrada psihološkog profila kroz testove.',                                 3000, '2023-03-30', 0),
    ('Priznao saučesništvo u vandalizmu.',                                       3450, '2023-03-31', 1),
    ('Refleksija o napuštanju školovanja.',                                       2650, '2023-02-06', 0),
    ('Kratkoročni ciljevi u ponašanju.',                                          2500, '2023-04-09', 0),
    ('Suočavanje sa telesnim simptomima anksioznosti.',                          2600, '2023-03-26', 0),
    ('Diskusija o ponavljajućim snovima i nesvesnim željama.',                   2800, '2023-04-11', 0);

INSERT INTO klijent_seansa_psihoterapeut (klijent_id, seansa_id, osoba_id, datum, vreme, trajanje, psiholoski_test_id) VALUES
    (1, 47, 3, '2023-04-16', '17:00:00', 50, 2),
    (14, 34, 30, '2023-03-07', '15:30:00', 45, NULL),
    (5, 18, 20, '2023-02-06', '15:30:00', 50, 7),
    (12, 3, 27, '2023-03-26', '17:30:00', 45, 20),
    (11, 22, 27, '2023-04-25', '08:00:00', 50, 1),
    (8, 36, 5, '2023-03-22', '18:00:00', 50, 19),
    (8, 5, 27, '2023-04-03', '08:00:00', 60, NULL),
    (6, 32, 25, '2023-03-31', '11:30:00', 45, 12),
    (6, 15, 13, '2023-02-11', '12:30:00', 45, 3),
    (8, 47, 5, '2023-03-29', '18:00:00', 45, 2),
    (14, 33, 25, '2023-04-25', '11:00:00', 45, 16),
    (11, 39, 30, '2023-02-16', '11:30:00', 45, 14),
    (15, 28, 11, '2023-04-30', '16:30:00', 50, NULL),
    (13, 28, 29, '2023-04-23', '08:30:00', 60, NULL),
    (12, 21, 24, '2023-02-15', '13:30:00', 50, NULL),
    (8, 47, 3, '2023-02-20', '17:00:00', 60, 2),
    (9, 34, 22, '2023-04-15', '11:00:00', 50, NULL),
    (5, 19, 3, '2023-03-05', '12:30:00', 50, 13),
    (14, 28, 29, '2023-04-29', '16:00:00', 60, NULL),
    (8, 41, 8, '2023-04-07', '08:00:00', 60, 6),
    (11, 21, 20, '2023-04-07', '10:30:00', 50, NULL),
    (9, 41, 7, '2023-03-26', '13:00:00', 50, 6),
    (8, 28, 3, '2023-03-06', '09:00:00', 45, NULL),
    (4, 18, 8, '2023-04-14', '14:00:00', 45, 7),
    (6, 23, 24, '2023-02-17', '18:30:00', 50, NULL),
    (4, 7, 14, '2023-03-17', '13:00:00', 60, NULL),
    (3, 38, 14, '2023-04-26', '08:30:00', 60, NULL),
    (4, 20, 7, '2023-02-18', '08:00:00', 60, NULL),
    (13, 25, 5, '2023-03-17', '09:30:00', 50, NULL),
    (13, 22, 5, '2026-03-17', '09:30:00', 50, NULL),
    (2, 3, 13, '2023-01-20', '16:00:00', 60, 20);


INSERT INTO valute (naziv_valute, vrednost_u_dinarima) VALUES
                                                           ('RSD - Dinar', 1.00),
                                                           ('EUR - Euro', 117.35),
                                                           ('USD - Američki dolar', 109.50),
                                                           ('CHF - Švajcarski franak', 122.80),
                                                           ('GBP - Britanska funta', 137.10),
                                                           ('BAM - Konvertibilna marka', 59.90),
                                                           ('AUD - Australski dolar', 70.45),
                                                           ('CAD - Kanadski dolar', 80.75),
                                                           ('SEK - Švedska kruna', 10.35),
                                                           ('JPY - Japanski jen', 0.85);


INSERT INTO placanje (placanje_id, klijent_id, psiholoski_test_id, seansa_id, iznos, strana_valuta, svrha_placanja, kesh, naziv_valute, datum_uplate) VALUES
                                                                                                                                                          (1, 1, 2, 47, 4887, 1, 'Seansa i test', 0, 'GBP - Britanska funta', '2023-04-16'),
                                                                                                                                                          (2, 14, NULL, 34, 3523, 1, 'Samo seansa', 1, 'EUR - Euro', '2023-03-07'),
                                                                                                                                                          (3, 5, 7, 18, 6273, 1, 'Seansa i test', 0, 'GBP - Britanska funta', '2023-02-06'),
                                                                                                                                                          (4, 12, 20, 3, 5087, 0, 'Seansa i test', 1, 'RSD - Dinar', '2023-03-26'),
                                                                                                                                                          (5, 11, 1, 22, 5760, 1, 'Seansa i test', 0, 'USD - Američki dolar', '2023-04-25'),
                                                                                                                                                          (6, 8, 19, 36, 6569, 0, 'Seansa i test', 1, 'RSD - Dinar', '2023-03-22'),
                                                                                                                                                          (7, 8, NULL, 5, 2768, 0, 'Samo seansa', 0, 'RSD - Dinar', '2023-04-03'),
                                                                                                                                                          (8, 6, 12, 32, 5189, 0, 'Seansa i test', 1, 'RSD - Dinar', '2023-03-31'),
                                                                                                                                                          (9, 6, 3, 15, 5859, 0, 'Seansa i test', 1, 'RSD - Dinar', '2023-02-11'),
                                                                                                                                                          (10, 14, 16, 33, 5423, 0, 'Seansa i test', 1, 'RSD - Dinar', '2023-04-25'),
                                                                                                                                                          (11, 11, 14, 39, 4902, 1, 'Seansa i test', 0, 'EUR - Euro', '2023-02-16'),
                                                                                                                                                          (12, 15, NULL, 28, 2832, 1, 'Samo seansa', 1, 'EUR - Euro', '2023-04-30'),
                                                                                                                                                          (13, 12, NULL, 21, 2543, 1, 'Samo seansa', 0, 'GBP - Britanska funta', '2023-02-15'),
                                                                                                                                                          (14, 5, 13, 19, 6685, 1, 'Seansa i test', 1, 'EUR - Euro', '2023-03-05'),
                                                                                                                                                          (15, 8, 6, 41, 4609, 1, 'Seansa i test', 0, 'CHF - Švajcarski franak', '2023-04-07'),
                                                                                                                                                          (16, 6, NULL, 23, 2792, 1, 'Samo seansa', 1, 'GBP - Britanska funta', '2023-02-17'),
                                                                                                                                                          (17, 4, NULL, 7, 3044, 0, 'Samo seansa', 0, 'RSD - Dinar', '2023-03-17'),
                                                                                                                                                          (18, 3, NULL, 38, 2633, 0, 'Samo seansa', 1, 'RSD - Dinar', '2023-04-26'),
                                                                                                                                                          (19, 4, NULL, 20, 2568, 1, 'Samo seansa', 1, 'EUR - Euro', '2023-02-18'),
                                                                                                                                                          (20, 13, NULL, 25, 3499, 0, 'Samo seansa', 0, 'RSD - Dinar', '2023-03-17');


INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (1, 1870, 3017, '2023-04-16', '2023-05-27');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (2, 2263, 1260, '2023-03-07', '2023-04-25');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (3, 2986, 3287, '2023-02-06', '2023-04-06');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (4, 2410, 2677, '2023-03-26', '2023-04-15');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (5, 3280, 2480, '2023-04-25', '2023-06-09');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (6, 3413, 3156, '2023-03-22', '2023-04-24');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (7, 1878, 890, '2023-04-03', '2023-05-31');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (8, 2362, 2827, '2023-03-31', '2023-05-12');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (9, 2520, 3339, '2023-02-11', '2023-04-11');

INSERT INTO rate (rata_id, prva_rata_iznos, druga_rata_iznos, datum_prve_rate, datum_druge_rate) VALUES
    (10, 2005, 3418, '2023-04-25', '2023-05-14');


INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'EUR - Euro', 117.10),
                                                                                            ('2025-04-15', 'EUR - Euro', 117.25),
                                                                                            ('2025-05-01', 'EUR - Euro', 117.35);

INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'USD - Američki dolar', 108.80),
                                                                                            ('2025-04-15', 'USD - Američki dolar', 109.10),
                                                                                            ('2025-05-01', 'USD - Američki dolar', 109.50);

INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'CHF - Švajcarski franak', 122.30),
                                                                                            ('2025-04-15', 'CHF - Švajcarski franak', 122.50),
                                                                                            ('2025-05-01', 'CHF - Švajcarski franak', 122.80);

INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'GBP - Britanska funta', 136.70),
                                                                                            ('2025-04-15', 'GBP - Britanska funta', 136.90),
                                                                                            ('2025-05-01', 'GBP - Britanska funta', 137.10);

INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'BAM - Konvertibilna marka', 59.70),
                                                                                            ('2025-04-15', 'BAM - Konvertibilna marka', 59.80),
                                                                                            ('2025-05-01', 'BAM - Konvertibilna marka', 59.90);

INSERT INTO promena_kursa_prema_dinaru (datum, naziv_valute, stara_vrednost_u_dinarima) VALUES
                                                                                            ('2025-04-01', 'JPY - Japanski jen', 0.80),
                                                                                            ('2025-04-15', 'JPY - Japanski jen', 0.82),
                                                                                            ('2025-05-01', 'JPY - Japanski jen', 0.85);

INSERT INTO objavljeni_podatci (seansa_id, kada, kome, zasto) VALUES
    (2,  '2023-02-14', 'Marija Janković', 'Psihoterapeut je kandidat pod supervizijom.'),
    (5,  '2023-02-01', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (11, '2023-03-18', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (16, '2023-03-29', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (22, '2023-04-05', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (29, '2023-03-25', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (33, '2023-02-22', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (38, '2023-04-10', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (42, '2023-04-08', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.'),
    (46, '2023-03-31', 'Policija', 'Predaja podataka policiji zbog priznanja krivičnog dela.');


-- Trigger za edukaciju INSERT
DELIMITER $$

CREATE TRIGGER trg_kandidat_provera
    BEFORE INSERT ON kandidat
    FOR EACH ROW
BEGIN
    IF TIMESTAMPDIFF(YEAR, NEW.pocetak_obuke, NEW.kraj_obuke) < 4 THEN
     SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Obuka mora trajati najmanje 4 godine';
END IF;
IF NEW.pocetak_obuke > new.kraj_obuke
	THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Pocetak obuke mora biti manji datum od kraja obuke!';
END IF;
END$$

DELIMITER ;

-- Triggeer za edukaciju UPDATE
DELIMITER $$

CREATE TRIGGER trg_kandidat_provera_update
    BEFORE UPDATE ON kandidat
    FOR EACH ROW
BEGIN
    IF TIMESTAMPDIFF(YEAR, NEW.pocetak_obuke, NEW.kraj_obuke) < 4
  THEN
     SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Obuka mora trajati najmanje 4 godine';
END IF;
IF NEW.pocetak_obuke > new.kraj_obuke
	THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Pocetak obuke mora biti manji datum od kraja obuke!';
END IF;
END$$

DELIMITER ;


-- Trigger za superviziju INSERT
DELIMITER $$

CREATE TRIGGER trg_supervizor_duplikat
    BEFORE INSERT ON supervizor
    FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM supervizor
        WHERE supervizor_id = NEW.supervizor_id) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Psihoterapeut je već supervizor';
END IF;
END$$

DELIMITER ;


-- INSERT Trigger da supervizor ne moze da bude supervizor vise kandidata u istom vremenskom periodu i usput ne moze biti manji kraj supervizije od pocetka supervizije
DELIMITER $$

CREATE TRIGGER trg_kandidat_supervizor_chk
    BEFORE INSERT ON kandidat_supervizor
    FOR EACH ROW
BEGIN
    IF NEW.kraj_supervizije < NEW.pocetak_supervizije THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT =
            'Greška: kraj supervizije mora biti isti ili posle pocetka supervizije.';
END IF;

IF EXISTS (
        SELECT 1
          FROM kandidat_supervizor ks
         WHERE ks.supervizor_id = NEW.supervizor_id
           AND (ks.kraj_supervizije IS NULL
                OR NEW.pocetak_supervizije <= ks.kraj_supervizije)
           AND (NEW.kraj_supervizije IS NULL
                OR NEW.kraj_supervizije  >= ks.pocetak_supervizije)
     ) THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT =
            'Supervizor već vodi drugog kandidata u tom periodu.';
END IF;
END$$

DELIMITER ;


-- UPDATE Trigger da supervizor ne moze da bude supervizor vise kandidata u istom vremenskom periodu i usput ne moze bioti manji kraj supervizije od pocetka supervizije
DELIMITER $$

CREATE TRIGGER trg_kandidat_supervizor_chk_update
    BEFORE UPDATE ON kandidat_supervizor
    FOR EACH ROW
BEGIN
    IF NEW.kraj_supervizije < NEW.pocetak_supervizije THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT =
            'Greška: kraj supervizije mora biti isti ili posle pocetka supervizije.';
END IF;

IF EXISTS (
        SELECT 1
          FROM kandidat_supervizor ks
         WHERE ks.supervizor_id = NEW.supervizor_id
           AND (ks.kraj_supervizije IS NULL
                OR NEW.pocetak_supervizije <= ks.kraj_supervizije)
           AND (NEW.kraj_supervizije IS NULL
                OR NEW.kraj_supervizije  >= ks.pocetak_supervizije)
     ) THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT =
            'Supervizor već vodi drugog kandidata u tom periodu.';
END IF;
END$$

DELIMITER ;


-- Trigger za superviziju UPDATE
DELIMITER $$

CREATE TRIGGER trg_supervizor_duplikat_update
    BEFORE UPDATE ON supervizor
    FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM supervizor
        WHERE supervizor_id = NEW.supervizor_id) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Psihoterapeut je već supervizor';
END IF;
END$$

DELIMITER ;


-- Trigger za 1 seansu INSERT
DELIMITER $$

DROP TRIGGER IF EXISTS trg_1_put_kod_psihologa $$
CREATE TRIGGER trg_1_put_kod_psihologa
AFTER INSERT ON klijent_seansa_psihoterapeut
FOR EACH ROW
BEGIN
    DECLARE cena_po_satu INT;
    DECLARE cena_seanse  INT;
    DECLARE cena_testa   INT DEFAULT 0;
    DECLARE total_iznos  INT;
    DECLARE din_valuta   VARCHAR(30);

    SELECT naziv_valute
      INTO din_valuta
      FROM valute
     WHERE vrednost_u_dinarima = 1
     LIMIT 1;

    IF NOT EXISTS (
        SELECT 1
          FROM klijent_seansa_psihoterapeut
         WHERE klijent_id = NEW.klijent_id
           AND (seansa_id, osoba_id) <> (NEW.seansa_id, NEW.osoba_id))
    THEN
        UPDATE klijent
           SET prva_seansa = 1
         WHERE klijent_id = NEW.klijent_id;

        SELECT cena
          INTO cena_po_satu
          FROM seansa
         WHERE seansa_id = NEW.seansa_id;

        SET cena_seanse := cena_po_satu * NEW.trajanje;

        IF NEW.psiholoski_test_id IS NOT NULL THEN
              SELECT cena
                INTO cena_testa
                FROM psiholoski_test
               WHERE psiholoski_test_id = NEW.psiholoski_test_id;
        END IF;

        SET total_iznos := cena_seanse + cena_testa;

        INSERT INTO placanje
              (klijent_id, psiholoski_test_id, seansa_id,
               iznos, strana_valuta, svrha_placanja, kesh,
               naziv_valute, datum_uplate)
        VALUES (NEW.klijent_id,
                NEW.psiholoski_test_id,
                NEW.seansa_id,
                total_iznos,
                0,
                'Prva seansa — plaćeno u celosti',
                1,
                din_valuta,
                CURDATE());
    END IF;
END$$
DELIMITER ;


-- Trigger za 1 seansu DELETE
DELIMITER $$

CREATE TRIGGER trg_1_put_kod_psihologa_delete
    AFTER DELETE ON klijent_seansa_psihoterapeut
    FOR EACH ROW
BEGIN

    IF NOT EXISTS (
        SELECT 1 FROM klijent_seansa_psihoterapeut
        WHERE klijent_id = OLD.klijent_id
        ) THEN

    UPDATE klijent
    SET prva_seansa = 0
    WHERE klijent_id = OLD.klijent_id;

END IF;
END$$

DELIMITER ;


-- Trigger za psihoterapeut po struci INSERT
DELIMITER $$

CREATE TRIGGER trg_po_struci_psihoterapeut
    BEFORE INSERT ON klijent_seansa_psihoterapeut
    FOR EACH ROW
BEGIN
    DECLARE oblast INT;
	IF NEW.psiholoski_test_id IS NOT NULL THEN
    SELECT oblast_psihoterapije_id
    INTO oblast
    FROM oblast_psihoterapije
    WHERE naziv = 'Psihoterapeut';

    IF NOT EXISTS(SELECT 1 FROM psihoterapeut
			WHERE psihoterapeut_id = NEW.osoba_id
			AND oblast_psihoterapije_id = oblast) THEN
				SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Ovaj psihoterapeut nije specijalizovan da da test.';
END IF;
END IF;
END$$

DELIMITER ;


-- Treba i za update
DELIMITER $$

CREATE TRIGGER trg_po_struci_psihoterapeut_update
    BEFORE UPDATE ON klijent_seansa_psihoterapeut
    FOR EACH ROW
BEGIN
    DECLARE oblast INT;
	IF NEW.psiholoski_test_id IS NOT NULL THEN
    SELECT oblast_psihoterapije_id
    INTO oblast
    FROM oblast_psihoterapije
    WHERE naziv = 'Psihoterapeut';

    IF NOT EXISTS(SELECT 1 FROM psihoterapeut
			WHERE psihoterapeut_id = NEW.osoba_id
			AND oblast_psihoterapije_id = oblast) THEN
				SIGNAL SQLSTATE '45000'
					SET MESSAGE_TEXT = 'Ovaj psihoterapeut nije specijalizovan da da test.';
END IF;
END IF;
END$$

DELIMITER ;


-- Trigger za menjanje cene seanse po satu
DELIMITER $$

CREATE TRIGGER trg_cena_seansa
    BEFORE UPDATE ON seansa
    FOR EACH ROW
BEGIN
    IF NEW.cena <> OLD.cena THEN
        SET NEW.poslednja_promena_cene = CURDATE();
END IF;
END$$

DELIMITER ;


-- Trigger za proviziju itd
DELIMITER $$

DROP TRIGGER IF EXISTS trg_placanje_chk $$
CREATE TRIGGER trg_placanje_chk
    BEFORE INSERT ON placanje
    FOR EACH ROW
BEGIN
    DECLARE cena_po_satu INT DEFAULT 0;
  DECLARE trajanje_h   INT DEFAULT 0;
  DECLARE cena_seanse  INT DEFAULT 0;
  DECLARE cena_testa   INT DEFAULT 0;
  DECLARE ukupno_rsds  INT DEFAULT 0;
  DECLARE din_po_jed   INT DEFAULT 1;
  DECLARE ocek_iznos   INT DEFAULT 0;
  DECLARE err_msg      VARCHAR(255);

  IF NEW.seansa_id IS NOT NULL THEN
    SELECT cena INTO cena_po_satu
    FROM seansa
    WHERE seansa_id = NEW.seansa_id;

    SELECT trajanje
    INTO trajanje_h
    FROM klijent_seansa_psihoterapeut
    WHERE klijent_id = NEW.klijent_id
      AND seansa_id  = NEW.seansa_id
        LIMIT 1;

    IF trajanje_h IS NULL THEN
             SIGNAL SQLSTATE '45000'
               SET MESSAGE_TEXT =
                   'Trajanje nije pronađeno u klijent_seansa_psihoterapeut.';
END IF;

SET cena_seanse = cena_po_satu * trajanje_h;
END IF;

  IF NEW.psiholoski_test_id IS NOT NULL THEN
SELECT cena INTO cena_testa
FROM psiholoski_test
WHERE psiholoski_test_id = NEW.psiholoski_test_id;
END IF;

  SET ukupno_rsds = cena_seanse + cena_testa;

  IF NEW.strana_valuta = 1 THEN
SELECT vrednost_u_dinarima
INTO din_po_jed
FROM valute
WHERE naziv_valute = NEW.naziv_valute;

IF din_po_jed IS NULL THEN
            SIGNAL SQLSTATE '45000'
              SET MESSAGE_TEXT = 'Nepoznata strana valuta.';
END IF;
END IF;

  IF NEW.strana_valuta = 0 THEN
        SET ocek_iznos = ukupno_rsds;
ELSE
        SET ocek_iznos = ROUND(ukupno_rsds / din_po_jed);
        IF NEW.naziv_valute <> 'EUR' THEN
            SET ocek_iznos = ROUND(ocek_iznos * 1.05);
END IF;
END IF;

  IF NEW.strana_valuta = 1
     AND EXISTS (SELECT 1 FROM rate WHERE rata_id = NEW.placanje_id) THEN
        SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT = 'Plaćanje u stranoj valuti ne može biti na rate.';
END IF;

  IF NEW.iznos <> ocek_iznos THEN
        SET err_msg = CONCAT(
           'Pogrešan iznos: očekivano je ',
           ocek_iznos, ' ',
           CASE WHEN NEW.strana_valuta = 0 THEN 'RSD' ELSE NEW.naziv_valute END, '.');
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = err_msg;
END IF;
END$$
DELIMITER ;


-- Trigger za rate
DELIMITER $$

CREATE TRIGGER trg_rate_chk
    BEFORE INSERT ON rate
    FOR EACH ROW
BEGIN
    DECLARE pun_iznos  INT;
  DECLARE strana_val TINYINT(1);
  DECLARE datum_prve DATETIME;

    SELECT iznos, strana_valuta, datum_uplate
    INTO pun_iznos, strana_val, datum_prve
    FROM placanje
    WHERE placanje_id = NEW.rata_id;

    IF strana_val = 1 THEN
       SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT = 'Rate nisu dozvoljene kada je strana_valuta = 1';
END IF;

IF NEW.prva_rata_iznos < 0.30 * pun_iznos THEN
       SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT = 'Prva rata mora biti najmanje 30 % ukupne cene.';
END IF;

  IF NEW.druga_rata_iznos IS NOT NULL
     AND DATEDIFF(NOW(), datum_prve) > 30 THEN
       SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT =
           'Druga rata mora biti uplaćena najkasnije 30 dana od prve.';
END IF;
END$$
DELIMITER ;


-- Trigger da kada se uzmeni valuta stara vrednost se upisuje u tabelu istorija valuta
DELIMITER $$

CREATE TRIGGER trg_log_kurs
    AFTER UPDATE ON valute
    FOR EACH ROW
BEGIN
    IF NEW.vrednost_u_dinarima <> OLD.vrednost_u_dinarima THEN
        INSERT INTO promena_kursa_prema_dinaru
               (datum, naziv_valute, stara_vrednost_u_dinarima)
        VALUES (CURDATE(),
                NEW.naziv_valute,
                OLD.vrednost_u_dinarima);
END IF;
END$$

DELIMITER ;


-- Svi view
CREATE OR REPLACE VIEW v_oblast          AS
SELECT oblast_id, naziv
FROM   oblast;

CREATE OR REPLACE VIEW v_usmerenje       AS
SELECT usmerenje_id, naziv, naziv_srodne_oblasti
FROM   usmerenje;

CREATE OR REPLACE VIEW v_stepen_studija  AS
SELECT stepen_studija_id
FROM   stepen_studija;

CREATE OR REPLACE VIEW v_valute          AS
SELECT naziv_valute       AS valuta_skr,
       vrednost_u_dinarima  AS kurs_din
FROM   valute;

CREATE OR REPLACE VIEW v_univerzitet AS
SELECT u.univerzitet_id,
       u.naziv                       AS univerzitet,
       us.naziv                      AS usmerenje
FROM   univerzitet u
           JOIN   usmerenje  us ON us.usmerenje_id = u.usmerenje_id;

CREATE OR REPLACE VIEW v_fakultet AS
SELECT f.fakultet_id,
       f.naziv            AS fakultet,
       v_u.univerzitet,
       o.naziv            AS oblast
FROM   fakultet f
           JOIN   v_univerzitet v_u ON v_u.univerzitet_id = f.univerzitet_id
           JOIN   fakultet_oblast fo ON fo.fakultet_id = f.fakultet_id
           JOIN   oblast o ON o.oblast_id = fo.oblast_id;

CREATE OR REPLACE VIEW v_fakultet_oblast AS
SELECT v_f.fakultet_id,
       v_f.fakultet,
       v_f.univerzitet,
       v_o.naziv   AS oblast
FROM   fakultet_oblast fo
           JOIN   v_fakultet     v_f ON v_f.fakultet_id = fo.fakultet_id
           JOIN   oblast         v_o ON v_o.oblast_id   = fo.oblast_id;

CREATE OR REPLACE VIEW v_osoba AS
SELECT o.osoba_id,
       o.ime,
       o.prezime,
       o.jmbg,
       o.datum_rodjenja,
       o.prebivaliste,
       o.broj_telefona,
       o.email,
       v_f.fakultet,
       v_f.univerzitet,
       v_st.stepen_studija_id AS stepen
FROM   osoba o
           JOIN   v_fakultet       v_f  ON v_f.fakultet_id = o.fakultet_id
           JOIN   v_stepen_studija v_st ON v_st.stepen_studija_id = o.stepen_studija_id;

CREATE OR REPLACE VIEW v_oblast_psihoterapije AS
SELECT oblast_psihoterapije_id,
       naziv AS oblast_terapije
FROM   oblast_psihoterapije;

CREATE OR REPLACE VIEW v_psihoterapeut AS
SELECT p.psihoterapeut_id,
       v_o.ime,
       v_o.prezime,
       p.datum_sertifikacije,
       v_op.oblast_terapije
FROM   psihoterapeut        p
           JOIN   v_osoba             v_o  ON v_o.osoba_id              = p.psihoterapeut_id
           JOIN   v_oblast_psihoterapije v_op ON v_op.oblast_psihoterapije_id = p.oblast_psihoterapije_id;

CREATE OR REPLACE VIEW v_supervizor AS
SELECT s.supervizor_id,
       v_p.ime,
       v_p.prezime,
       v_p.oblast_terapije
FROM   supervizor s
           JOIN   v_psihoterapeut v_p ON v_p.psihoterapeut_id = s.supervizor_id;

CREATE OR REPLACE VIEW v_centar_za_obuku AS
SELECT centar_za_obuku_id,
       naziv         AS centar,
       email,
       broj_telefona,
       adresa
FROM   centar_za_obuku;

CREATE OR REPLACE VIEW v_kandidat AS
SELECT k.kandidat_id,
       v_o.ime,
       v_o.prezime,
       v_c.centar,
       k.pocetak_obuke,
       k.kraj_obuke
FROM   kandidat k
           JOIN   v_osoba          v_o ON v_o.osoba_id        = k.kandidat_id
           JOIN   v_centar_za_obuku v_c ON v_c.centar_za_obuku_id = k.centar_za_obuku_id;

CREATE OR REPLACE VIEW v_kandidat_supervizor AS
SELECT v_k.kandidat_id,
       v_k.ime        AS kandidat_ime,
       v_k.prezime    AS kandidat_prezime,
       v_s.supervizor_id,
       v_s.ime        AS supervizor_ime,
       v_s.prezime    AS supervizor_prezime,
       ks.pocetak_supervizije,
       ks.kraj_supervizije
FROM   kandidat_supervizor ks
           JOIN   v_kandidat   v_k ON v_k.kandidat_id   = ks.kandidat_id
           JOIN   v_supervizor v_s ON v_s.supervizor_id = ks.supervizor_id;

CREATE OR REPLACE VIEW v_klijent AS
SELECT klijent_id,
       ime,
       prezime,
       datum_rodjenja,
       pol,
       email,
       broj_telefona,
       opis_problema,
       nacin_kontakta,
       prva_seansa
FROM   klijent;

CREATE OR REPLACE VIEW v_psiholoski_test AS
SELECT psiholoski_test_id,
       oblast,
       naziv      AS test_naziv,
       cena       AS test_cena,
       rezultat
FROM   psiholoski_test;

CREATE OR REPLACE VIEW v_seansa AS
SELECT seansa_id,
       beleske,
       cena   AS cena_po_satu,
       poslednja_promena_cene,
       priznao_krivicno_delo
FROM   seansa;

CREATE OR REPLACE VIEW v_klijent_seansa AS
SELECT ks.klijent_id,
       v_kl.ime   AS klijent_ime,
       v_kl.prezime AS klijent_prezime,
       ks.seansa_id,
       v_s.cena_po_satu,
       ks.trajanje         AS trajanje_sati,
       v_s.cena_po_satu * ks.trajanje AS cena_seanse,
       ks.psiholoski_test_id,
       v_t.test_naziv,
       v_t.test_cena,
       ks.osoba_id  AS terapeut_id,
       v_o.ime      AS terapeut_ime,
       v_o.prezime  AS terapeut_prezime,
       ks.datum,
       ks.vreme
FROM   klijent_seansa_psihoterapeut ks
           JOIN   v_klijent          v_kl ON v_kl.klijent_id = ks.klijent_id
           JOIN   v_seansa           v_s  ON v_s.seansa_id   = ks.seansa_id
           LEFT  JOIN v_psiholoski_test v_t ON v_t.psiholoski_test_id = ks.psiholoski_test_id
           JOIN   v_osoba            v_o  ON v_o.osoba_id    = ks.osoba_id;

CREATE OR REPLACE VIEW v_placanje AS
SELECT p.placanje_id,
       v_kl.ime   AS klijent_ime,
       v_kl.prezime AS klijent_prezime,
       p.seansa_id,
       v_sess.cena_seanse,
       p.psiholoski_test_id,
       v_t.test_cena,
       p.iznos,
       CASE p.strana_valuta WHEN 1 THEN 'DA' ELSE 'NE' END AS strana_valuta,
       p.naziv_valute,
       v_val.kurs_din,
       p.svrha_placanja,
       CASE p.kesh WHEN 1 THEN 'KEŠ' ELSE 'BEZG.' END AS način_isplate,
       p.datum_uplate
FROM   placanje p
           JOIN   v_klijent          v_kl    ON v_kl.klijent_id = p.klijent_id
           LEFT  JOIN v_psiholoski_test v_t  ON v_t.psiholoski_test_id = p.psiholoski_test_id
           LEFT  JOIN v_valute         v_val ON v_val.valuta_skr       = p.naziv_valute
           LEFT  JOIN v_klijent_seansa v_sess
                      ON v_sess.klijent_id = p.klijent_id
                          AND v_sess.seansa_id  = p.seansa_id;

CREATE OR REPLACE VIEW v_rate AS
SELECT r.rata_id,
       v_p.klijent_ime,
       v_p.klijent_prezime,
       v_p.iznos             AS ukupan_iznos,
       r.prva_rata_iznos,
       r.druga_rata_iznos
FROM   rate r
           JOIN   v_placanje v_p ON v_p.placanje_id = r.rata_id;

CREATE OR REPLACE VIEW v_promena_kursa AS
SELECT pkp.datum,
       v_val.valuta_skr,
       v_val.kurs_din       AS novi_kurs,
       pkp.stara_vrednost_u_dinarima AS stari_kurs
FROM   promena_kursa_prema_dinaru pkp
           JOIN   v_valute v_val ON v_val.valuta_skr = pkp.naziv_valute;

-- Procedure za nadgledanje seansi
DELIMITER $$

CREATE PROCEDURE sp_pregled_seansi (
    IN p_role VARCHAR(12),
    IN p_id   INT
)
BEGIN

    IF p_role = 'KLIJENT' THEN
SELECT s.seansa_id,
       s.beleske,
       s.cena,
       s.poslednja_promena_cene,
       ksp.datum,
       ksp.vreme,
       ksp.trajanje,
       ksp.psiholoski_test_id,
       pt.naziv AS naziv_psih_testa
FROM   klijent_seansa_psihoterapeut AS ksp
           JOIN   seansa               AS s  ON s.seansa_id = ksp.seansa_id
           LEFT  JOIN psiholoski_test  AS pt ON pt.psiholoski_test_id = ksp.psiholoski_test_id
WHERE  ksp.klijent_id = p_id
ORDER  BY ksp.datum, ksp.vreme;


ELSEIF p_role = 'TERAPEUT' THEN
SELECT s.seansa_id,
       s.beleske,
       s.cena,
       s.poslednja_promena_cene,
       ksp.datum,
       ksp.vreme,
       ksp.trajanje,
       ksp.klijent_id,
       ksp.psiholoski_test_id,
       pt.naziv AS naziv_psih_testa
FROM   klijent_seansa_psihoterapeut AS ksp
           JOIN   seansa               AS s  ON s.seansa_id = ksp.seansa_id
           LEFT  JOIN psiholoski_test  AS pt ON pt.psiholoski_test_id = ksp.psiholoski_test_id
WHERE  ksp.osoba_id = p_id
ORDER  BY ksp.datum, ksp.vreme;


ELSEIF p_role = 'SUPERVIZOR' THEN

SELECT *
FROM (
         SELECT s.seansa_id,
                s.beleske,
                s.cena,
                s.poslednja_promena_cene,
                ksp.datum,
                ksp.vreme,
                ksp.trajanje,
                NULL           AS kandidat_id,
                ksp.klijent_id,
                ksp.psiholoski_test_id,
                pt.naziv       AS naziv_psih_testa
         FROM   klijent_seansa_psihoterapeut ksp
                    JOIN   seansa              s  ON s.seansa_id = ksp.seansa_id
                    LEFT  JOIN psiholoski_test pt ON pt.psiholoski_test_id = ksp.psiholoski_test_id
         WHERE  ksp.osoba_id = p_id

         UNION ALL

         SELECT s.seansa_id,
                s.beleske,
                s.cena,
                s.poslednja_promena_cene,
                ksp.datum,
                ksp.vreme,
                ksp.trajanje,
                ks.kandidat_id,
                ksp.klijent_id,
                ksp.psiholoski_test_id,
                pt.naziv       AS naziv_psih_testa
         FROM   kandidat_supervizor        ks
                    JOIN   klijent_seansa_psihoterapeut  ksp
                           ON ks.kandidat_id = ksp.osoba_id
                    JOIN   seansa               s    ON s.seansa_id = ksp.seansa_id
                    LEFT  JOIN psiholoski_test  pt   ON pt.psiholoski_test_id = ksp.psiholoski_test_id
         WHERE  ks.supervizor_id = p_id
           AND  ksp.datum BETWEEN ks.pocetak_supervizije
             AND IFNULL(ks.kraj_supervizije, CURDATE())
     ) AS sve_seanse
ORDER BY datum, vreme;

END IF;
END$$
DELIMITER ;


-- Funkcija za pregled buducih seansi
DELIMITER $$

CREATE PROCEDURE sp_pregled_buducih_seansi (
    IN p_psihoterapeut_id INT
)
BEGIN
    SELECT  ks.seansa_id,
            ks.datum,
            ks.vreme,
            ks.trajanje,
            s.beleske,
            s.cena              AS cena_po_satu,
            (s.cena * ks.trajanje) AS cena_ukupno,
            ks.psiholoski_test_id,
            pt.naziv            AS naziv_testa,
            k.klijent_id,
            k.ime               AS klijent_ime,
            k.prezime           AS klijent_prezime
    FROM    klijent_seansa_psihoterapeut ks
    JOIN    seansa            s  ON s.seansa_id            = ks.seansa_id
    LEFT JOIN psiholoski_test pt ON pt.psiholoski_test_id  = ks.psiholoski_test_id
    JOIN    klijent           k  ON k.klijent_id           = ks.klijent_id
    WHERE   ks.osoba_id = p_psihoterapeut_id
      AND  ( ks.datum  > CURDATE()
             OR ( ks.datum = CURDATE() AND ks.vreme >= CURTIME() ) )
    ORDER BY ks.datum, ks.vreme;
END$$

DELIMITER ;


-- Za odredjeni role TERAPEUT ili KLIJENT ili SUPERVIZOR
DELIMITER $$

DROP FUNCTION IF EXISTS fn_rola $$
CREATE FUNCTION fn_rola (p_id INT)
RETURNS VARCHAR(15)
DETERMINISTIC
BEGIN
    DECLARE v_role VARCHAR(15);

    IF EXISTS (SELECT 1 FROM supervizor
               WHERE supervizor_id = p_id) THEN
        SET v_role := 'SUPERVIZOR';

    ELSEIF EXISTS (SELECT 1 FROM psihoterapeut
                   WHERE psihoterapeut_id = p_id)
        OR EXISTS (SELECT 1 FROM kandidat
                   WHERE kandidat_id = p_id) THEN
        SET v_role := 'TERAPEUT';

    ELSE
        SET v_role := 'KLIJENT';
    END IF;

    RETURN v_role;
END$$
DELIMITER ;

-- Za nove klijente koji nisu do sada bili
DELIMITER $$

DROP PROCEDURE IF EXISTS sp_prijave_novih_klijenata $$
CREATE PROCEDURE sp_prijave_novih_klijenata (
    IN p_psihoterapeut_id INT
)
BEGIN
    SELECT  ksp.klijent_id,
            k.ime,
            k.prezime,
            k.email,
            k.broj_telefona,
            k.opis_problema,
            k.nacin_kontakta,
            ksp.datum   AS zakazani_datum,
            ksp.vreme   AS zakazano_vreme,
            ksp.trajanje AS trajanje_sati
    FROM    klijent_seansa_psihoterapeut ksp
    JOIN    klijent k ON k.klijent_id = ksp.klijent_id
    WHERE   ksp.osoba_id = p_psihoterapeut_id
      AND   (ksp.datum  > CURDATE()
             OR (ksp.datum = CURDATE() AND ksp.vreme >= CURTIME()))
      AND NOT EXISTS (
            SELECT 1
              FROM klijent_seansa_psihoterapeut past
             WHERE past.klijent_id = ksp.klijent_id
               AND past.osoba_id  = p_psihoterapeut_id
               AND ( past.datum  <  CURDATE()
                   OR (past.datum = CURDATE() AND past.vreme < CURTIME()) )
          )
    ORDER BY ksp.datum, ksp.vreme;
END$$

DELIMITER ;


-- Procedura za dugove i ostalo
DELIMITER $$

CREATE PROCEDURE sp_pregled_uplata_dugovanja (IN p_klijent_id INT)
BEGIN
    DROP TEMPORARY TABLE IF EXISTS tmp_placeno;

    CREATE TEMPORARY TABLE tmp_placeno
    SELECT  p.klijent_id,
            p.seansa_id,
            p.psiholoski_test_id,
            SUM( CASE WHEN p.strana_valuta = 1
                      THEN p.iznos * v.vrednost_u_dinarima
                      ELSE p.iznos
                 END
               + IFNULL(r.prva_rata_iznos,0)
               + IFNULL(r.druga_rata_iznos,0) ) AS placeno_rsd
    FROM    placanje p
    LEFT JOIN valute v  ON v.naziv_valute = p.naziv_valute
    LEFT JOIN rate   r  ON r.rata_id      = p.placanje_id
    GROUP BY p.klijent_id, p.seansa_id, p.psiholoski_test_id;

    SELECT  k.klijent_id                      AS klijentID,
            CONCAT(k.ime,' ',k.prezime)       AS klijent,
            ksp.seansa_id                     AS seansaID,
            ksp.datum                         AS datum_seanse,
            ROUND( s.cena * ksp.trajanje
                   + IFNULL(t.cena,0), 2)     AS ukupna_cena_rsd,
            ROUND( IFNULL(pl.placeno_rsd,0),2)               AS placeno_rsd,
            ROUND( ( s.cena * ksp.trajanje
                     + IFNULL(t.cena,0) )
                   - IFNULL(pl.placeno_rsd,0),2)             AS dug_rsd,

            COALESCE(r1.datum_prve_rate, ksp.datum)          AS datum_pocetka_roka,

            CASE
              WHEN ( ( s.cena * ksp.trajanje + IFNULL(t.cena,0) )
                     - IFNULL(pl.placeno_rsd,0) ) > 0
                   AND DATEDIFF( CURDATE(),
                                 COALESCE(r1.datum_prve_rate, ksp.datum) ) > 30
              THEN 'DA' ELSE 'NE'
            END                                             AS prekoracen_rok,

            CASE
              WHEN ( ( s.cena * ksp.trajanje + IFNULL(t.cena,0) )
                     - IFNULL(pl.placeno_rsd,0) ) > 0
                   AND DATEDIFF( CURDATE(),
                                 COALESCE(r1.datum_prve_rate, ksp.datum) ) > 30
              THEN DATEDIFF( CURDATE(),
                             COALESCE(r1.datum_prve_rate, ksp.datum) ) - 30
              ELSE 0
            END                                             AS dana_preko_30
    FROM    klijent_seansa_psihoterapeut ksp
    JOIN    klijent k           ON k.klijent_id  = ksp.klijent_id
    JOIN    seansa  s           ON s.seansa_id   = ksp.seansa_id
    LEFT JOIN psiholoski_test t ON t.psiholoski_test_id = ksp.psiholoski_test_id
    LEFT JOIN placanje p1
               ON p1.klijent_id = ksp.klijent_id
              AND p1.seansa_id  = ksp.seansa_id
              AND (p1.psiholoski_test_id <=> ksp.psiholoski_test_id)
    LEFT JOIN rate r1          ON r1.rata_id = p1.placanje_id
    LEFT JOIN tmp_placeno pl
               ON pl.klijent_id = ksp.klijent_id
              AND pl.seansa_id  = ksp.seansa_id
              AND (pl.psiholoski_test_id <=> ksp.psiholoski_test_id)
    WHERE   (p_klijent_id IS NULL OR p_klijent_id = 0
             OR k.klijent_id = p_klijent_id)
    ORDER BY k.klijent_id, ksp.datum, ksp.vreme;

    DROP TEMPORARY TABLE IF EXISTS tmp_placeno;
END$$
DELIMITER ;

CALL sp_pregled_uplata_dugovanja(NULL);

-- Svi pozivi za view
SELECT * FROM v_oblast;
SELECT * FROM v_usmerenje;
SELECT * FROM v_stepen_studija;
SELECT * FROM v_valute;
SELECT * FROM v_univerzitet;
SELECT * FROM v_fakultet;
SELECT * FROM v_fakultet_oblast;
SELECT * FROM v_osoba;
SELECT * FROM v_oblast_psihoterapije;
SELECT * FROM v_psihoterapeut;
SELECT * FROM v_supervizor;
SELECT * FROM v_centar_za_obuku;
SELECT * FROM v_kandidat;
SELECT * FROM v_kandidat_supervizor;
SELECT * FROM v_klijent;
SELECT * FROM v_psiholoski_test;
SELECT * FROM v_seansa;
SELECT * FROM v_klijent_seansa;
SELECT * FROM v_placanje;
SELECT * FROM v_rate;
SELECT * FROM v_promena_kursa;

-- Neki random testeri
call sp_prijave_novih_klijenata(3);
insert into klijent_seansa_psihoterapeut values (10, 23, 3, '2026-05-12', '17:00:00', 60, NULL);
CALL sp_pregled_buducih_seansi(5);
SELECT * FROM klijent_seansa_psihoterapeut;
SELECT * FROM klijent;
SELECT * FROM placanje;
SELECT * FROM seansa;
SELECT * FROM valute;
SELECT * FROM psihoterapeut;
SELECT * FROM osoba;
SELECT * FROM kandidat;
SELECT * FROM oblast_psihoterapije;
SELECT * FROM kandidat_supervizor;
CALL sp_pregled_seansi(fn_rola(3), 3);
CALL sp_pregled_seansi('SUPERVIZOR', 3)