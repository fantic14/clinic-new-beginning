package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supervizor extends Psihoterapeut{

    private int supervizorId;

    public Supervizor(int osobaId, String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, int stepenStudijaId, LocalDate datumSertifikacije, int oblastPsihoterapijeId) {
        super(osobaId, ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId, datumSertifikacije, oblastPsihoterapijeId);
        this.supervizorId = super.getPsihoterapeutId();
    }
}
