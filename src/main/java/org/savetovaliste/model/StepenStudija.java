package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepenStudija {

    private String nazivStepenStudija;

    public StepenStudija(String nazivStepenStudija) {
        this.nazivStepenStudija = nazivStepenStudija;
    }
}
