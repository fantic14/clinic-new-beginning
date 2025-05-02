package org.savetovaliste.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Placanje {

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

    public Placanje(int placanjeId, int klijentId, Integer psiholoskiTestId, Integer seansaId, boolean stranaValuta, String svrhaPlacanja, boolean kesh, LocalDate datumUplate) {
        this.placanjeId = placanjeId;
        this.klijentId = klijentId;
        this.psiholoskiTestId = psiholoskiTestId;
        this.seansaId = seansaId;
        this.stranaValuta = stranaValuta;
        this.svrhaPlacanja = svrhaPlacanja;
        this.kesh = kesh;
        this.datumUplate = datumUplate;
    }

    public Placanje(int placanjeId, int klijentId, Integer psiholoskiTestId, Integer seansaId, boolean stranaValuta, String svrhaPlacanja, boolean kesh, LocalDate datumUplate, String nazivValute) {
        this.placanjeId = placanjeId;
        this.klijentId = klijentId;
        this.psiholoskiTestId = psiholoskiTestId;
        this.seansaId = seansaId;
        this.stranaValuta = stranaValuta;
        this.svrhaPlacanja = svrhaPlacanja;
        this.kesh = kesh;
        this.datumUplate = datumUplate;
        this.nazivValute = nazivValute;
    }
}
