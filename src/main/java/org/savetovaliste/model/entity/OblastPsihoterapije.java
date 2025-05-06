package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OblastPsihoterapije {

    private static int idCounter = 1;
    private int oblastPsihoterapijeId;
    private String naziv;

    public OblastPsihoterapije(int oblastPsihoterapijeId, String naziv) {
        this.oblastPsihoterapijeId = oblastPsihoterapijeId;
        this.naziv = naziv;
    }

    public OblastPsihoterapije(String naziv) {
        this.naziv = naziv;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
