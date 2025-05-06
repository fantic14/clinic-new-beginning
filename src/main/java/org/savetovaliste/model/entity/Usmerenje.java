package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usmerenje {

    private static int idCounter = 1;
    private int usmerenjeId;
    private String naziv;
    private String nazivSrodneOblasti;

    public Usmerenje(int usmerenjeId, String naziv) {
        this.usmerenjeId = usmerenjeId;
        this.naziv = naziv;
    }

    public Usmerenje(int usmerenjeId, String naziv, String nazivSrodneOblasti) {
        this.usmerenjeId = usmerenjeId;
        this.naziv = naziv;
        this.nazivSrodneOblasti = nazivSrodneOblasti;
    }

    public Usmerenje(String naziv, String nazivSrodneOblasti) {
        this.naziv = naziv;
        this.nazivSrodneOblasti = nazivSrodneOblasti;
    }

    public Usmerenje(String naziv) {
        this.naziv = naziv;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
