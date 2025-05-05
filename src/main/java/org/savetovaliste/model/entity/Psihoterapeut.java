package org.savetovaliste.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.savetovaliste.model.dao.FakultetDAO;
import org.savetovaliste.model.dao.StepenStudijaDAO;

@Getter
@Setter
public class Psihoterapeut extends Osoba{

    private int psihoterapeutId;
    private LocalDate datumSertifikacije;
    private int oblastPsihoterapijeId;

    public Psihoterapeut(String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, int fakultetId, int stepenStudijaId, LocalDate datumSertifikacije, int oblastPsihoterapijeId) {
        super(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId);
        this.psihoterapeutId = super.getOsobaId();
        this.datumSertifikacije = datumSertifikacije;
        this.oblastPsihoterapijeId = oblastPsihoterapijeId;
    }

    public String sastaviPsihoterapeutInfo(){
        String fakultet = FakultetDAO.getFakultetName(this.getFakultetId());
        String stepenStudija = StepenStudijaDAO.getStepenStudijaName(this.getStepenStudijaId());
        String oblastPsihoterapije;
        return this.getIme() +
                " " +
                this.getPrezime() +
                " " +
                this.getJmbg() +
                " " +
                this.getDatumRodjenja() +
                " " +
                this.getPrebivaliste() +
                " " +
                this.getBrojTelefona() +
                " " +
                this.getEmail() +
                " " +
                fakultet +
                " " +
                stepenStudija;
    }
}
