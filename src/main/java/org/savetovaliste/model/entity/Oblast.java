package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oblast {

    private static int idCounter = 1;
    private int oblastId;
    private String naziv;

    public Oblast(int oblastId, String naziv) {
        this.oblastId = oblastId;
        this.naziv = naziv;
    }

    public Oblast(String naziv) {
        this.naziv = naziv;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
