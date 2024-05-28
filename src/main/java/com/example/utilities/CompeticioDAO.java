package com.example.utilities;

import com.example.gestorcompeticionsuf5uf6.Competicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompeticioDAO {

    public void guardarCompeticio(Competicio competicio) {
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "INSERT INTO competicions (codigo, tipus, num_equips, categoria, genere) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, competicio.getCodigo());
                stmt.setString(2, competicio.getTipus());
                stmt.setInt(3, competicio.getNumEquips());
                stmt.setString(4, competicio.getCategoria());
                stmt.setString(5, competicio.getGenere());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Competicio recuperarCompeticioPorCodigo(int codigo) {
        Competicio competicio = null;
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "SELECT codigo, tipus, num_equips, categoria, genere FROM competicions WHERE codigo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, codigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String tipus = rs.getString("tipus");
                    int numEquips = rs.getInt("num_equips");
                    String categoria = rs.getString("categoria");
                    String genere = rs.getString("genere");
                    competicio = new Competicio(codigo, tipus, numEquips, categoria, genere);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competicio;
    }

    public void actualizarCompeticio(Competicio competicio) {
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "UPDATE competicions SET tipus = ?, num_equips = ?, categoria = ?, genere = ? WHERE codigo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, competicio.getTipus());
                stmt.setInt(2, competicio.getNumEquips());
                stmt.setString(3, competicio.getCategoria());
                stmt.setString(4, competicio.getGenere());
                stmt.setInt(5, competicio.getCodigo());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompeticio(int codigo) {
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "DELETE FROM competicions WHERE codigo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, codigo);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> getAvailableCompetitionsCodes() {
        List<Integer> codes = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "SELECT codigo FROM competicions";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int code = rs.getInt("codigo");
                    codes.add(code);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codes;
    }

    public Competicio getCompeticio(int selectedCodigo) {
        Competicio competicio = null;
        try (Connection conn = ConnectDB.getInstance()) {
            String sql = "SELECT * FROM competicions WHERE codigo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, selectedCodigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String tipus = rs.getString("tipus");
                    int numEquips = rs.getInt("num_equips");
                    String categoria = rs.getString("categoria");
                    String genere = rs.getString("genere");
                    competicio = new Competicio(selectedCodigo, tipus, numEquips, categoria, genere);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competicio;
    }
}
