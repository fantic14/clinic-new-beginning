package org.savetovaliste.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakultetOblast {

    private int fakultetId;
    private int oblastId;

    public FakultetOblast(int fakultetId, int oblastId) {
        this.fakultetId = fakultetId;
        this.oblastId = oblastId;
    }
}
