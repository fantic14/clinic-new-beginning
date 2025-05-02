package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Valute {

    private String nazivValute;
    private int vrednostUDinari;

    public Valute(String nazivValute, int vrednostUDinari) {
        this.nazivValute = nazivValute;
        this.vrednostUDinari = vrednostUDinari;
    }
}
