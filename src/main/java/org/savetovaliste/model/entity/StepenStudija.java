package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepenStudija {

    private static int idCounter = 1;
    private int stepenStudijaId;
    private String nazivStepenStudija;

    public StepenStudija(String nazivStepenStudija) {
        this.stepenStudijaId = idCounter++;
        this.nazivStepenStudija = nazivStepenStudija;
    }

    @Override
    public String toString() {
        return nazivStepenStudija;
    }
}
