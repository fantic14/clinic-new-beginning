package org.savetovaliste.model;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Klijent {

    private static int idCounter = 1;
    private int klijentId;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private char pol;
    private String email;
    private String brojTelefona;
    private String opisProblema;
    private String nacinKontakta;
    private boolean prvaSeansa;

    public Klijent(String ime, String prezime, LocalDate datumRodjenja, char pol, String email, String brojTelefona, String opisProblema, String nacinKontakta, boolean prvaSeansa) {
        this.klijentId = idCounter++;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.opisProblema = opisProblema;
        this.nacinKontakta = nacinKontakta;
        this.prvaSeansa = prvaSeansa;
    }
}
