package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Fakultet;
import org.savetovaliste.model.entity.OblastPsihoterapije;
import org.savetovaliste.model.entity.Psihoterapeut;
import org.savetovaliste.model.entity.StepenStudija;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PsihoterapeutDAO {

    public static List<Integer> selectAllSeansaIdsFromPsihoterapeut(int psihoterapeutId) {
        String query = "SELECT DISTINCT seansa_id FROM psihoterapija.klijent_seansa_psihoterapeut WHERE osoba_id = ?";
        List<Integer> seansaIds = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, psihoterapeutId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                seansaIds.add(result.getInt("seansa_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching seansa ids", e);
        }
        return seansaIds;
    }

    public static Psihoterapeut selectFromPsihoterapeutById(int id) {
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
                "p.datum_sertifikacije,\n" +
                "p.oblast_psihoterapije_id\n" +
                "FROM psihoterapija.osoba o\n" +
                "JOIN psihoterapija.psihoterapeut p ON o.osoba_id = p.psihoterapeut_id\n";
        Psihoterapeut psihoterapeut = null;

        try (PreparedStatement statement = getConnection().prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int osobaId = result.getInt("osoba_id");
                String ime = result.getString("ime");
                String prezime = result.getString("prezime");
                String jmbg = result.getString("jmbg");
                LocalDate datumRodjenja = result.getDate("datum_rodjenja").toLocalDate();
                String prebivaliste = result.getString("prebivaliste");
                String brojTelefona = result.getString("broj_telefona");
                String email = result.getString("email");
                int fakultetId = result.getInt("fakultet_id");
                int stepenStudijaId = result.getInt("stepen_studija_id");
                LocalDate datumSertifikacije = result.getDate("datum_sertifikacije").toLocalDate();
                int oblastPsihoterapijeId = result.getInt("oblast_psihoterapije_id");
                psihoterapeut = new Psihoterapeut(osobaId, ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId, datumSertifikacije, oblastPsihoterapijeId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching psihoterapeuti", e);
        }

        return psihoterapeut;
    }

    public static List<Psihoterapeut> selectAllFromPsihoterapeut() {
        String query = "SELECT DISTINCT\n" +
                "o.osoba_id,\n" +
                "o.ime,\n" +
                "o.prezime,\n" +
                "o.jmbg,\n" +
                "o.datum_rodjenja,\n" +
                "o.prebivaliste,\n" +
                "o.broj_telefona,\n" +
                "o.email,\n" +
                "o.fakultet_id,\n" +
                "o.stepen_studija_id,\n" +
                "p.datum_sertifikacije,\n" +
                "p.oblast_psihoterapije_id\n" +
                "FROM psihoterapija.osoba o\n" +
                "JOIN psihoterapija.psihoterapeut p ON o.osoba_id = p.psihoterapeut_id\n";
        List<Psihoterapeut> psihoterapeuti = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int osobaId = result.getInt("osoba_id");
                String ime = result.getString("ime");
                String prezime = result.getString("prezime");
                String jmbg = result.getString("jmbg");
                LocalDate datumRodjenja = result.getDate("datum_rodjenja").toLocalDate();
                String prebivaliste = result.getString("prebivaliste");
                String brojTelefona = result.getString("broj_telefona");
                String email = result.getString("email");
                int fakultetId = result.getInt("fakultet_id");
                int stepenStudijaId = result.getInt("stepen_studija_id");
                LocalDate datumSertifikacije = result.getDate("datum_sertifikacije").toLocalDate();
                int oblastPsihoterapijeId = result.getInt("oblast_psihoterapije_id");
                Psihoterapeut p = new Psihoterapeut(osobaId, ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId, datumSertifikacije, oblastPsihoterapijeId);

                psihoterapeuti.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching psihoterapeuti", e);
        }

        return psihoterapeuti;
    }

    public static void insertPsihoterapeut(Psihoterapeut p) {
        String insertOsoba = "INSERT INTO psihoterapija.osoba " +
                "(ime, prezime, jmbg, datum_rodjenja, prebivaliste, broj_telefona, email, fakultet_id, stepen_studija_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertPsihoterapeut = "INSERT INTO psihoterapija.psihoterapeut " +
                "(psihoterapeut_id, datum_sertifikacije, oblast_psihoterapije_id) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement osobaStmt = connection.prepareStatement(insertOsoba, Statement.RETURN_GENERATED_KEYS);
            osobaStmt.setString(1, p.getIme());
            osobaStmt.setString(2, p.getPrezime());
            osobaStmt.setString(3, p.getJmbg());
            osobaStmt.setDate(4, Date.valueOf(p.getDatumRodjenja()));
            osobaStmt.setString(5, p.getPrebivaliste());
            osobaStmt.setString(6, p.getBrojTelefona());
            osobaStmt.setString(7, p.getEmail());
            osobaStmt.setInt(8, p.getFakultetId());
            osobaStmt.setInt(9, p.getStepenStudijaId());
            osobaStmt.executeUpdate();

            ResultSet keys = osobaStmt.getGeneratedKeys();
            if (keys.next()) {
                int osobaId = keys.getInt(1);

                PreparedStatement psihoterapeutStmt = connection.prepareStatement(insertPsihoterapeut);
                psihoterapeutStmt.setInt(1, osobaId);
                psihoterapeutStmt.setDate(2, Date.valueOf(p.getDatumSertifikacije()));
                psihoterapeutStmt.setInt(3, p.getOblastPsihoterapijeId());
                psihoterapeutStmt.executeUpdate();

                connection.commit();
            } else {
                connection.rollback();
                throw new SQLException("Failed to retrieve osoba_id.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting psihoterapeut", e);
        }
    }

    public static Psihoterapeut selectFromPsihoterapeut(String ime_ulogovanog, String prezime_ulogovanog, String jmbg_ulogovanog) {
        String query = "SELECT DISTINCT\n" +
                "o.osoba_id,\n" +
                "o.ime,\n" +
                "o.prezime,\n" +
                "o.jmbg,\n" +
                "o.datum_rodjenja,\n" +
                "o.prebivaliste,\n" +
                "o.broj_telefona,\n" +
                "o.email,\n" +
                "o.fakultet_id,\n" +
                "o.stepen_studija_id,\n" +
                "p.datum_sertifikacije,\n" +
                "p.oblast_psihoterapije_id\n" +
                "FROM psihoterapija.osoba o\n" +
                "JOIN psihoterapija.psihoterapeut p ON o.osoba_id = p.psihoterapeut_id\n" +
                "WHERE o.ime = ? AND o.prezime = ? AND o.jmbg = ?";



        Psihoterapeut p = null;
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, ime_ulogovanog);
            statement.setString(2, prezime_ulogovanog);
            statement.setString(3, jmbg_ulogovanog);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int osobaId = result.getInt("osoba_id");
                String ime = result.getString("ime");
                String prezime = result.getString("prezime");
                String jmbg = result.getString("jmbg");
                LocalDate datumRodjenja = result.getDate("datum_rodjenja").toLocalDate();
                String prebivaliste = result.getString("prebivaliste");
                String brojTelefona = result.getString("broj_telefona");
                String email = result.getString("email");
                int fakultetId = result.getInt("fakultet_id");
                int stepenStudijaId = result.getInt("stepen_studija_id");
                LocalDate datumSertifikacije = result.getDate("datum_sertifikacije").toLocalDate();
                int oblastPsihoterapijeId = result.getInt("oblast_psihoterapije_id");
                p = new Psihoterapeut(osobaId, ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultetId, stepenStudijaId, datumSertifikacije, oblastPsihoterapijeId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public static Fakultet getFakultetFromPsihoterapeut(Psihoterapeut p) {
        String query = "SELECT * FROM psihoterapija.fakultet WHERE fakultet_id = ?";
        Fakultet fakultet = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, p.getFakultetId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                fakultet = new Fakultet(result.getInt("fakultet_id"),result.getString("naziv"), result.getInt("univerzitet_id"));
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching fakultet", e);
        }
        return fakultet;
    }

    public static OblastPsihoterapije getOblastPsihoterapijefromPsihoterapeut(Psihoterapeut p){
        String query = "SELECT * FROM psihoterapija.oblast_psihoterapije WHERE oblast_psihoterapije_id = ?";
        OblastPsihoterapije o = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, p.getOblastPsihoterapijeId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                o = new OblastPsihoterapije(result.getInt("oblast_psihoterapije_id"),result.getString("naziv"));
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching oblast psihoterapije", e);
        }
        return o;
    }

    public static StepenStudija getStepenStudijafromPsihoterapeut(Psihoterapeut p){
        String query = "SELECT * FROM psihoterapija.stepen_studija WHERE stepen_studija_id = ?";
        StepenStudija s = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, p.getStepenStudijaId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                s = new StepenStudija(result.getInt("stepen_studija_id"),result.getString("naziv"));
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching stepen studija", e);
        }
        return s;
    }
}

