package org.savetovaliste.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromenaKursaPremaDinaru {

    private LocalDate datum;
    private String nazivValute;
    private int staraVrednostUDinari;

    public PromenaKursaPremaDinaru(LocalDate datum, String nazivValute, int staraVrednostUDinari) {
        this.datum = datum;
        this.nazivValute = nazivValute;
        this.staraVrednostUDinari = staraVrednostUDinari;
    }
}
