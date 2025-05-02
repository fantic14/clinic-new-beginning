package org.savetovaliste.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KlijentSeansaPsihoterap {

    private int klijentId;
    private int seansaId;
    private int osobaId;
    private LocalDate datum;
    private LocalTime vreme;
    private int trajanje;
    private Integer psiholoskiTestId;

    public KlijentSeansaPsihoterap(int klijentId, int seansaId, int osobaId, LocalDate datum, LocalTime vreme, int trajanje, Integer psiholoskiTestId) {
        this.klijentId = klijentId;
        this.seansaId = seansaId;
        this.osobaId = osobaId;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.psiholoskiTestId = psiholoskiTestId;
    }

    public KlijentSeansaPsihoterap(int klijentId, int seansaId, int osobaId, LocalDate datum, LocalTime vreme, int trajanje) {
        this.klijentId = klijentId;
        this.seansaId = seansaId;
        this.osobaId = osobaId;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
    }
}
