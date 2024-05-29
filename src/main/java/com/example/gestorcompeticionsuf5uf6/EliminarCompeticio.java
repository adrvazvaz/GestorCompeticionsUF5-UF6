package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.sql.SQLException;
import java.util.List;

public class EliminarCompeticio {

    @FXML
    private ChoiceBox<Integer> choiceBoxCodi;

    @FXML
    private ScrollPane scrollPaneCompeticio;

    @FXML
    private Label labelCompeticio;

    @FXML
    public void initialize() {
        System.out.println("Inicializando EliminarCompeticioController...");
        try {
            cargarCodigosCompeticions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        choiceBoxCodi.setOnAction(event -> {
            try {
                mostrarInformacionCompeticio();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        labelCompeticio.setWrapText(true); // Asegúrate de que el texto se ajuste
        scrollPaneCompeticio.setContent(labelCompeticio);
        scrollPaneCompeticio.setFitToWidth(true);
        scrollPaneCompeticio.setFitToHeight(true);
    }

    private void cargarCodigosCompeticions() throws SQLException {
        CompeticioDAO competicioDAO = new CompeticioDAO();
        List<Integer> codigos = competicioDAO.getAvailableCompetitionsCodes();
        choiceBoxCodi.getItems().setAll(codigos);
    }

    private void mostrarInformacionCompeticio() throws SQLException {
        Integer codi = choiceBoxCodi.getValue();
        if (codi != null) {
            CompeticioDAO competicioDAO = new CompeticioDAO();
            Competicio competicio = competicioDAO.getCompeticio(codi);
            if (competicio != null) {
                labelCompeticio.setText(formatCompeticio(competicio));
            } else {
                labelCompeticio.setText(""); // Si no hay competición, establecer el texto en blanco
            }
        }
    }

    @FXML
    public void eliminarCompeticio(ActionEvent actionEvent) {
        Integer codi = choiceBoxCodi.getValue();
        if (codi != null) {
            CompeticioDAO competicioDAO = new CompeticioDAO();
            try {
                competicioDAO.eliminarCompeticio(codi);
                cargarCodigosCompeticions();
                labelCompeticio.setText(""); // Limpia el label después de eliminar
            } catch (SQLException e) {
                mostrarAlerta("Error al eliminar competición", "No se pudo eliminar la competición.", e.getMessage());
                e.printStackTrace(); // Opcional: Imprimir traza de la excepción
            }
        }
    }

    private String formatCompeticio(Competicio competicio) {
        return String.format("Competició: %d\nTipo: %s\nNúmero de equipos: %d\nCategoría: %s\nGénero: %s",
                competicio.getCodigo(), competicio.getTipus(), competicio.getNumEquips(),
                competicio.getCategoria(), competicio.getGenere());
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}

