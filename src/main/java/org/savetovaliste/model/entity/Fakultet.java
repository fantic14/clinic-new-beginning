package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fakultet {

    private static int idCounter = 1;
    private int fakultetId;
    private String naziv;
    private int univerzitetId;

    public Fakultet(String naziv, int univerzitetId) {
        this.naziv = naziv;
        this.univerzitetId = univerzitetId;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
