package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.util.List;

public class VeureCompeticionsController {

    @FXML
    private TextArea competicionsTextArea;

    private final CompeticioDAO competicioDAO = new CompeticioDAO();

    public void mostrarCompeticions() {
        List<Competicio> competicions = competicioDAO.getAllCompeticions();
        if (competicions.isEmpty()) {
            mostrarNotificacion("No hay competiciones disponibles.", Alert.AlertType.INFORMATION);
            return; // Detiene la ejecución del método aquí si no hay competiciones
        } else {
            mostrarCompetitions(competicions);
        }
    }

    private void mostrarCompetitions(List<Competicio> competicions) {
        StringBuilder sb = new StringBuilder();
        for (Competicio competicio : competicions) {
            sb.append(formatCompeticio(competicio)).append("\n\n");
        }
        competicionsTextArea.setText(sb.toString());
    }

    public void initData(List<Competicio> competicions) {
        mostrarCompetitions(competicions);
    }

    private String formatCompeticio(Competicio competicio) {
        return String.format("Competición: %d\nTipo: %s\nNúmero de equipos: %d\nCategoría: %s\nGénero: %s",
                competicio.getCodigo(), competicio.getTipus(), competicio.getNumEquips(),
                competicio.getCategoria(), competicio.getGenere());
    }

    private void mostrarNotificacion(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Notificación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}


