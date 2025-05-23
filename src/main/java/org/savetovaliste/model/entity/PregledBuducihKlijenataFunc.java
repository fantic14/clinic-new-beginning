package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PregledBuducihKlijenataFunc {

    private int klijentID;
    private String klijentIme;
    private String klijentPrezime;
    private String email;
    private String telefon;
    private String problem;
    private String nacinKontakta;
    private LocalDate datum;
    private LocalTime vreme;
    private int trajanje;

    public PregledBuducihKlijenataFunc(int klijentID, String klijentIme, String klijentPrezime, String email, String telefon, String problem, String nacinKontakta, LocalDate datum, LocalTime vreme, int trajanje) {
        this.klijentID = klijentID;
        this.klijentIme = klijentIme;
        this.klijentPrezime = klijentPrezime;
        this.email = email;
        this.telefon = telefon;
        this.problem = problem;
        this.nacinKontakta = nacinKontakta;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
    }
}
