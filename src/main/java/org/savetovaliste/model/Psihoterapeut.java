package org.savetovaliste.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Psihoterapeut extends Osoba{

    private int psihoterapeutId;
    private LocalDate datumSertifikacije;
    private int oblastPsihoterapijeId;

    public Psihoterapeut(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, String nazivStepenStudija, LocalDate datumSertifikacije, int oblastPsihoterapijeId) {
        super(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, nazivStepenStudija);
        this.psihoterapeutId = super.getOsobaId();
        this.datumSertifikacije = datumSertifikacije;
        this.oblastPsihoterapijeId = oblastPsihoterapijeId;
    }
}
