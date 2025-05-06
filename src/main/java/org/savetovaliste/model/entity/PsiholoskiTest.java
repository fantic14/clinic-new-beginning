package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PsiholoskiTest {

    private static int idCounter = 1;
    private int psiholoskiTestId;
    private String oblast;
    private String naziv;
    private int cena;
    private String rezultat;

    public PsiholoskiTest(String oblast, String naziv, int cena, String rezultat) {
        this.oblast = oblast;
        this.naziv = naziv;
        this.cena = cena;
        this.rezultat = rezultat;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
