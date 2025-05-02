package org.savetovaliste.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rate {

    private static int idCounter = 1;
    private int rataId;
    private int prvaRataIznos;
    private int drugaRataIznos;
    private LocalDate datumPrveRate;
    private LocalDate datumDrugeRate;

    public Rate(int prvaRataIznos, int drugaRataIznos, LocalDate datumPrveRate) {
        this.rataId = idCounter++;
        this.prvaRataIznos = prvaRataIznos;
        this.drugaRataIznos = drugaRataIznos;
        this.datumPrveRate = datumPrveRate;
    }
}
