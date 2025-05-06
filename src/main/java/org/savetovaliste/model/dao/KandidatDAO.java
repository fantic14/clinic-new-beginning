package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Kandidat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class KandidatDAO {

    public static Kandidat selectKandidatById(int id) {
        String query = "SELECT DISTINCT\n" +
                "o.ime,\n" +
                "o.prezime,\n" +
                "o.jmbg,\n" +
                "o.datum_rodjenja,\n" +
                "o.prebivaliste,\n" +
                "o.broj_telefona,\n" +
                "o.email,\n" +
                "o.fakultet_id,\n" +
                "o.stepen_studija_id,\n" +
                "k.centar_za_obuku_id,\n" +
                "k.pocetak_obuke\n" +
                "k.kraj_obuke\n" +
                "FROM psihoterapija.osoba o\n" +
                "JOIN psihoterapija.kandidat k ON o.osoba_id = p.kandidat_id\n" +
                "WHERE o.osoba_id = ?";
        Kandidat kandidat = null;
        try (PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                kandidat = new Kandidat(
                        result.getString("ime"),
                        result.getString("prezime"),
                        result.getString("jmbg"),
                        result.getDate("datum_rodjenja").toLocalDate(),
                        result.getString("prebivaliste"),
                        result.getString("broj_telefona"),
                        result.getString("email"),
                        result.getInt("fakultet_id"),
                        result.getInt("stepen_studija_id"),
                        result.getInt("centar_za_obuku_id"),
                        result.getDate("pocetak_obuke").toLocalDate(),
                        result.getDate("kraj_obuke").toLocalDate()
                );
            }
        } catch (SQLException e){
            throw new RuntimeException("Error fetching kandidat", e);
        }
        return kandidat;
    }
}
