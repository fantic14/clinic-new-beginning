package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OblastPsihoterapije {

    private static int idCounter = 1;
    private int oblastPsihoterapijeId;
    private String naziv;

    public OblastPsihoterapije(String naziv) {
        this.oblastPsihoterapijeId = idCounter++;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
