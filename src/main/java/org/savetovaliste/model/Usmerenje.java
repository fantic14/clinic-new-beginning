package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usmerenje {

    private static int idCounter = 1;
    private int usmerenjeId;
    private String naziv;
    private String nazivSrodneOblasti;

    public Usmerenje(String naziv, String nazivSrodneOblasti) {
        this.usmerenjeId = idCounter++;
        this.naziv = naziv;
        this.nazivSrodneOblasti = nazivSrodneOblasti;
    }

    public Usmerenje(String naziv) {
        this.usmerenjeId = idCounter++;
        this.naziv = naziv;
    }
}
