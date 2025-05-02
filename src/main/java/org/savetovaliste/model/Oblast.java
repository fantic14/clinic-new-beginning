package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oblast {

    private static int idCounter = 1;
    private int oblastId;
    private String naziv;

    public Oblast(String naziv) {
        this.oblastId = idCounter++;
        this.naziv = naziv;
    }
}
