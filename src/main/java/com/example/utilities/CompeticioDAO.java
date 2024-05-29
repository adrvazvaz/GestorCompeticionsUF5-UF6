package com.example.utilities;

import com.example.gestorcompeticionsuf5uf6.Competicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompeticioDAO {

    public void guardarCompeticio(Competicio competicio) throws SQLException {
        String sql = "INSERT INTO competicio (codi, tipus, num_equips, categoria, genere) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, competicio.getCodigo());
            stmt.setString(2, competicio.getTipus());
            stmt.setInt(3, competicio.getNumEquips());
            stmt.setString(4, competicio.getCategoria());
            stmt.setString(5, competicio.getGenere());
            stmt.executeUpdate();
            System.out.println("Competición guardada en la base de datos.");
        } catch (SQLException e) {
            throw new SQLException("Error al guardar la competición en la base de datos.", e);
        }
    }

    public List<Competicio> getAllCompeticions() {
        List<Competicio> competicions = new ArrayList<>();
        String sql = "SELECT codi, tipus, num_equips, categoria, genere FROM competicio";

        try (Connection conn = ConnectDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codi");
                String tipus = rs.getString("tipus");
                int numEquips = rs.getInt("num_equips");
                String categoria = rs.getString("categoria");
                String genere = rs.getString("genere");

                Competicio competicio = new Competicio(codigo, tipus, numEquips, categoria, genere);
                competicions.add(competicio);
            }
            return competicions;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void actualizarCompeticio(Competicio competicio) throws SQLException {
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "UPDATE competicio SET tipus = ?, num_equips = ?, categoria = ?, genere = ? WHERE codi = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, competicio.getTipus());
                stmt.setInt(2, competicio.getNumEquips());
                stmt.setString(3, competicio.getCategoria());
                stmt.setString(4, competicio.getGenere());
                stmt.setInt(5, competicio.getCodigo());
                stmt.executeUpdate();
                System.out.println("Competición actualizada en la base de datos.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la competición en la base de datos.", e);
        }
    }

    public List<Integer> getAvailableCompetitionsCodes() throws SQLException {
        List<Integer> codes = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement("SELECT codi FROM competicio");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int code = rs.getInt("codi");
                codes.add(code);
            }
            return codes;
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los códigos de las competiciones disponibles.", e);
        }
    }

    public Competicio getCompeticio(int selectedCodi) throws SQLException {
        try (Connection conn = ConnectDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM competicio WHERE codi = ?")) {
            stmt.setInt(1, selectedCodi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipus = rs.getString("tipus");
                    int numEquips = rs.getInt("num_equips");
                    String categoria = rs.getString("categoria");
                    String genere = rs.getString("genere");
                    return new Competicio(selectedCodi, tipus, numEquips, categoria, genere);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la competición de la base de datos.", e);
        }
        return null;
    }

    public void eliminarCompeticio(int codi) throws SQLException {
        try (Connection conn = ConnectDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM competicio WHERE codi = ?")) {
            stmt.setInt(1, codi);
            stmt.executeUpdate();
            System.out.println("Competición eliminada de la base de datos.");
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar la competición de la base de datos.", e);
        }
    }
}

