package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seansa {

    private static int idCounter = 1;
    private int seansaId;
    private String beleske;
    private int cena;
    private LocalDate poslednjaPromenaCene;
    private boolean priznaoKrivicnoDelo;

    public Seansa(String beleske, int cena, LocalDate poslednjaPromenaCene, boolean priznaoKrivicnoDelo) {
        this.beleske = beleske;
        this.cena = cena;
        this.poslednjaPromenaCene = poslednjaPromenaCene;
        this.priznaoKrivicnoDelo = priznaoKrivicnoDelo;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
