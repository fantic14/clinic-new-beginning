package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KandidatSupervizor {

    private int kandidatId;
    private int supervizorId;
    private LocalDate pocetakSupervizije;
    private LocalDate krajSupervizije;

    public KandidatSupervizor(int kandidatId, int supervizorId, LocalDate pocetakSupervizije, LocalDate krajSupervizije) {
        this.kandidatId = kandidatId;
        this.supervizorId = supervizorId;
        this.pocetakSupervizije = pocetakSupervizije;
        this.krajSupervizije = krajSupervizije;
    }

    public KandidatSupervizor(int kandidatId, int supervizorId, LocalDate pocetakSupervizije) {
        this.kandidatId = kandidatId;
        this.supervizorId = supervizorId;
        this.pocetakSupervizije = pocetakSupervizije;
    }
}
