package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Osoba {

    private static int idCounter = 1;
    private int osobaId;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datumRodjenja;
    private String prebivaliste;
    private String brojTelefona;
    private String email;
    private int fakultetId;
    private int stepenStudijaId;

    public Osoba(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, int stepenStudijaId) {
        this.osobaId = idCounter++;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.prebivaliste = prebivaliste;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.fakultetId = fakultetId;
        this.stepenStudijaId = stepenStudijaId;
    }
}
