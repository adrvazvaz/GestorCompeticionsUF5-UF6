package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

public class VeureCompeticionsController {

    @FXML
    private TextArea competicionsTextArea;

    private final CompeticioDAO competicioDAO = new CompeticioDAO();

    public void mostrarCompeticions() {
        List<Competicio> competicions = competicioDAO.getAllCompeticions();
        if (competicions.isEmpty()) {
            competicionsTextArea.setText("No hay competiciones disponibles.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Competicio competicio : competicions) {
                sb.append(formatCompeticio(competicio)).append("\n\n");
            }
            competicionsTextArea.setText(sb.toString());
        }
    }

    private String formatCompeticio(Competicio competicio) {
        return String.format("Código: %d\nTipo: %s\nNúmero de equipos: %d\nCategoría: %s\nGénero: %s",
                competicio.getCodigo(), competicio.getTipus(), competicio.getNumEquips(),
                competicio.getCategoria(), competicio.getGenere());
    }
}

