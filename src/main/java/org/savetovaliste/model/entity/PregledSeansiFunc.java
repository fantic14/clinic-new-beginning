package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PregledSeansiFunc {
    private int seansaID;
    private String beleske;
    private int cena;
    private LocalDate datumPromene;
    private LocalDate datum;
    private LocalTime vreme;
    private int trajanje;
    private int kandidatID;
    private int klijentID;
    private int psiholoskiTestID;
    private String nazivTesta;

    public PregledSeansiFunc(int seansaID, String beleske, int cena, LocalDate datumPromene, LocalDate datum, LocalTime vreme, int trajanje, int kandidatID, int klijentID, int psiholoskiTestID, String nazivTesta) {
        this.seansaID = seansaID;
        this.beleske = beleske;
        this.cena = cena;
        this.datumPromene = datumPromene;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.kandidatID = kandidatID;
        this.klijentID = klijentID;
        this.psiholoskiTestID = psiholoskiTestID;
        this.nazivTesta = nazivTesta;
    }
}
