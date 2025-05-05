package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kandidat extends Osoba{

    private int kandidatId;
    private int centarZaObukuId;
    private LocalDate pocetakObuke;
    private LocalDate krajObuke;

    public Kandidat(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, int stepenStudijaId, int centarZaObukuId, LocalDate pocetakObuke, LocalDate krajObuke) {
        super(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId);
        this.kandidatId = super.getOsobaId();
        this.centarZaObukuId = centarZaObukuId;
        this.pocetakObuke = pocetakObuke;
        this.krajObuke = krajObuke;
    }

    public Kandidat(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, int stepenStudijaId, int centarZaObukuId, LocalDate pocetakObuke) {
        super(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId);
        this.kandidatId = super.getOsobaId();
        this.centarZaObukuId = centarZaObukuId;
        this.pocetakObuke = pocetakObuke;
    }
}
