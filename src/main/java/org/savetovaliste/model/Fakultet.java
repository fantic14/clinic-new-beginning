package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fakultet {

    private static int idCounter = 1;
    private int fakultetId;
    private String naziv;
    private int univerzitetId;
    private int oblastId;

    public Fakultet(String naziv, int univerzitetId, int oblastId) {
        this.fakultetId = idCounter++;
        this.naziv = naziv;
        this.univerzitetId = univerzitetId;
        this.oblastId = oblastId;
    }
}
