package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Placanje {

    private static int idCounter = 1;
    private int placanjeId;
    private int klijentId;
    private Integer psiholoskiTestId;
    private Integer seansaId;
    private int iznos;
    private boolean stranaValuta;
    private String svrhaPlacanja;
    private boolean kesh;
    private String nazivValute;
    private LocalDate datumUplate;

    public Placanje(int placanjeId, int klijentId, Integer psiholoskiTestId, Integer seansaId, int iznos, boolean stranaValuta, String svrhaPlacanja, boolean kesh, String nazivValute, LocalDate datumUplate) {
        this.placanjeId = placanjeId;
        this.klijentId = klijentId;
        this.psiholoskiTestId = psiholoskiTestId;
        this.seansaId = seansaId;
        this.iznos = iznos;
        this.stranaValuta = stranaValuta;
        this.svrhaPlacanja = svrhaPlacanja;
        this.kesh = kesh;
        this.nazivValute = nazivValute;
        this.datumUplate = datumUplate;
    }

    public Placanje(int klijentId, Integer psiholoskiTestId, Integer seansaId, int iznos, boolean stranaValuta, String svrhaPlacanja, boolean kesh, String nazivValute, LocalDate datumUplate) {
        this.klijentId = klijentId;
        this.psiholoskiTestId = psiholoskiTestId;
        this.seansaId = seansaId;
        this.iznos = iznos;
        this.stranaValuta = stranaValuta;
        this.svrhaPlacanja = svrhaPlacanja;
        this.kesh = kesh;
        this.nazivValute = nazivValute;
        this.datumUplate = datumUplate;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
