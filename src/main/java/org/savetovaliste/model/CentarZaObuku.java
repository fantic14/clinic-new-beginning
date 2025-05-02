package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentarZaObuku {

    private static int idCounter = 1;
    private int centarZaObukuId;
    private String naziv;
    private String email;
    private String brojTelefona;
    private String adresa;

    public CentarZaObuku(String naziv, String email, String brojTelefona, String adresa) {
        this.centarZaObukuId = idCounter++;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
    }
}
