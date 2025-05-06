package org.savetovaliste.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjavljeniPodatci {

    private static int idCounter = 1;
    private int objavljeniPodatciId;
    private int seansaId;
    private String kada;
    private String kome;
    private String zasto;

    public ObjavljeniPodatci(int objavljeniPodatciId, int seansaId, String kada, String kome, String zasto) {
        this.objavljeniPodatciId = objavljeniPodatciId;
        this.seansaId = seansaId;
        this.kada = kada;
        this.kome = kome;
        this.zasto = zasto;
    }

    public ObjavljeniPodatci(int seansaId, String kada, String kome, String zasto) {
        this.seansaId = seansaId;
        this.kada = kada;
        this.kome = kome;
        this.zasto = zasto;
    }

    public static void incrementIdCounter() {
        idCounter++;
    }
}
