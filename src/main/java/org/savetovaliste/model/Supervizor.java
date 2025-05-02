package org.savetovaliste.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supervizor extends Psihoterapeut{

    private int supervizorId;

    public Supervizor(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, String nazivStepenStudija, LocalDate datumSertifikacije, int oblastPsihoterapijeId) {
        super(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, nazivStepenStudija, datumSertifikacije, oblastPsihoterapijeId);
        this.supervizorId = super.getPsihoterapeutId();
    }
}
