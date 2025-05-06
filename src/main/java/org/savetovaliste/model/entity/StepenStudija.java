package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepenStudija {

    private static int idCounter = 1;
    private int stepenStudijaId;
    private String nazivStepenStudija;

    public StepenStudija(int stepenStudijaId, String nazivStepenStudija) {
        this.stepenStudijaId = stepenStudijaId;
        this.nazivStepenStudija = nazivStepenStudija;
    }

    public StepenStudija(String nazivStepenStudija) {
        this.nazivStepenStudija = nazivStepenStudija;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }

    @Override
    public String toString() {
        return nazivStepenStudija;
    }
}
