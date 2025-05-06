package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Univerzitet {

    private static int idCounter = 1;
    private int univerzitetId;
    private String naziv;
    private int usmerenjeId;

    public Univerzitet(String naziv, int usmerenjeId) {
        this.naziv = naziv;
        this.usmerenjeId = usmerenjeId;
    }

    public Univerzitet(String naziv) {
        this.naziv = naziv;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
