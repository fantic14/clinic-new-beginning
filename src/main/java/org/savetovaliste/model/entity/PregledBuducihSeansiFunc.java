package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PregledBuducihSeansiFunc {

    private int seansaID;
    private LocalDate datum;
    private LocalTime vreme;
    private int trajanje;
    private String beleske;
    private int cenaSat;
    private int cenaUkupno;
    private int psiholoskiTestID;
    private String nazivTesta;
    private int klijentID;
    private String klijentIme;
    private String klijentPrezime;

    public PregledBuducihSeansiFunc(int seansaID, LocalDate datum, LocalTime vreme, int trajanje, String beleske, int cenaSat, int cenaUkupno, int psiholoskiTestID, String nazivTesta, int klijentID, String klijentIme, String klijentPrezime) {
        this.seansaID = seansaID;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.beleske = beleske;
        this.cenaSat = cenaSat;
        this.cenaUkupno = cenaUkupno;
        this.psiholoskiTestID = psiholoskiTestID;
        this.nazivTesta = nazivTesta;
        this.klijentID = klijentID;
        this.klijentIme = klijentIme;
        this.klijentPrezime = klijentPrezime;
    }
}
