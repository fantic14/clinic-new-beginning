package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PregledUplataFunc {

    private int klijentID;
    private String klijentNaziv;
    private int seansaID;
    private LocalDate datum;
    private int ukupnaCena;
    private double placeno;
    private double dug;
    private LocalDate pocetakRoka;
    private String prekoracenje;
    private int prekoDana;

    public PregledUplataFunc(int klijentID, String klijentNaziv, int seansaID, LocalDate datum, int ukupnaCena, double placeno, double dug, LocalDate pocetakRoka, String prekoracenje, int prekoDana) {
        this.klijentID = klijentID;
        this.klijentNaziv = klijentNaziv;
        this.seansaID = seansaID;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
        this.placeno = placeno;
        this.dug = dug;
        this.pocetakRoka = pocetakRoka;
        this.prekoracenje = prekoracenje;
        this.prekoDana = prekoDana;
    }
}
