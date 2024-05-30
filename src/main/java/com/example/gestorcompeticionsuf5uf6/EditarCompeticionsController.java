package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class EditarCompeticionsController {

    @FXML
    private ChoiceBox<Integer> competicionsChoiceBox;
    @FXML
    private TextField numEquipsField;
    @FXML
    private ChoiceBox<String> tipusChoiceBox;
    @FXML
    private ChoiceBox<String> categoriaChoiceBox;
    @FXML
    private ChoiceBox<String> genereChoiceBox;

    private final CompeticioDAO competicioDAO;
    private Competicio competicioSeleccionada;

    public EditarCompeticionsController() {
        competicioDAO = new CompeticioDAO();
    }

    @FXML
    public void initialize() throws SQLException {
        tipusChoiceBox.getItems().addAll("Lliga", "Eliminatòria");
        categoriaChoiceBox.getItems().addAll("Benjamí", "Infantil", "Junior", "sub-20", "sub-25", "Senior");
        genereChoiceBox.getItems().addAll("Masculí", "Femení");

        List<Integer> competicioCodes = competicioDAO.getAvailableCompetitionsCodes();
        if (competicioCodes.isEmpty()) {
            mostrarAlerta("¡No hay mucho trabajo que hacer por aquí!");
        } else {
            competicionsChoiceBox.getItems().addAll(competicioCodes);
            competicionsChoiceBox.setOnAction(event -> {
                try {
                    cargarDetallesCompeticion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



    @FXML
    private void cargarDetallesCompeticion() throws SQLException {
        Integer selectedCodi = competicionsChoiceBox.getValue();
        if (selectedCodi != null) {
            competicioSeleccionada = competicioDAO.getCompeticio(selectedCodi);
            if (competicioSeleccionada != null) {
                numEquipsField.setText(String.valueOf(competicioSeleccionada.getNumEquips()));
                tipusChoiceBox.setValue(competicioSeleccionada.getTipus());
                categoriaChoiceBox.setValue(competicioSeleccionada.getCategoria());
                genereChoiceBox.setValue(competicioSeleccionada.getGenere());
            }
        }
    }

    @FXML
    private void guardarCambios() throws SQLException {
        if (competicioSeleccionada != null) {
            competicioSeleccionada.setTipus(tipusChoiceBox.getValue());
            competicioSeleccionada.setNumEquips(Integer.parseInt(numEquipsField.getText()));
            competicioSeleccionada.setCategoria(categoriaChoiceBox.getValue());
            competicioSeleccionada.setGenere(genereChoiceBox.getValue());
            competicioDAO.actualizarCompeticio(competicioSeleccionada);
        }
    }

    public void initData(Competicio selectedCompeticio) {
        if (selectedCompeticio != null) {
            competicioSeleccionada = selectedCompeticio;
            numEquipsField.setText(String.valueOf(selectedCompeticio.getNumEquips()));
            tipusChoiceBox.setValue(selectedCompeticio.getTipus());
            categoriaChoiceBox.setValue(selectedCompeticio.getCategoria());
            genereChoiceBox.setValue(selectedCompeticio.getGenere());
        }
    }

}


