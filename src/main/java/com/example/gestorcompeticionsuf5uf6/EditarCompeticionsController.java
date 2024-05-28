package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;

public class EditarCompeticionsController {
    @FXML
    private TextField numEquipsField;
    @FXML
    private ChoiceBox<String> tipusCompeticioChoiceBox;
    @FXML
    private ChoiceBox<String> categoriaChoiceBox;
    @FXML
    private ChoiceBox<String> genereChoiceBox;
    @FXML
    private ChoiceBox<Integer> competicionsChoiceBox;

    private final CompeticioDAO competicioDAO = new CompeticioDAO();

    @FXML
    public void initialize(Competicio competicio) {
        // Configurar el ChoiceBox para mostrar los códigos de las competiciones disponibles
        competicionsChoiceBox.getItems().addAll(competicioDAO.getAvailableCompetitionsCodes());
    }

    @FXML
    public void cargarDetallesCompeticion() {
        int selectedCodigo = competicionsChoiceBox.getValue();
        Competicio competicio = competicioDAO.getCompeticio(selectedCodigo);
        if (competicio != null) {
            numEquipsField.setText(String.valueOf(competicio.getNumEquips()));
            tipusCompeticioChoiceBox.setValue(competicio.getTipus());
            categoriaChoiceBox.setValue(competicio.getCategoria());
            genereChoiceBox.setValue(competicio.getGenere());
            mostrarAlerta("Éxito", "Detalles cargados", "Los detalles de la competición han sido cargados.");
        } else {
            mostrarAlerta("Error", "Competición no encontrada", "No se encontró ninguna competición con el código seleccionado.");
        }
    }

    @FXML
    public void guardarCambios() {
        // Obtener los valores de los campos
        int codigo = competicionsChoiceBox.getValue();
        String tipus = tipusCompeticioChoiceBox.getValue();
        int numEquips = Integer.parseInt(numEquipsField.getText());
        String categoria = categoriaChoiceBox.getValue();
        String genere = genereChoiceBox.getValue();

        // Crear el objeto Competicio con los nuevos valores
        Competicio competicio = new Competicio(codigo, tipus, numEquips, categoria, genere);

        // Actualizar la competición en la base de datos
        competicioDAO.actualizarCompeticio(competicio);
        mostrarAlerta("Éxito", "Competición actualizada", "Los cambios han sido guardados correctamente.");
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
