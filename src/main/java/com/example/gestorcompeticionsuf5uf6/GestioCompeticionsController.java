package com.example.gestorcompeticionsuf5uf6;

import com.example.utilities.CompeticioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GestioCompeticionsController {

    @FXML
    private ChoiceBox<String> tipusCompeticio;
    @FXML
    private TextField numEquips;
    @FXML
    private ChoiceBox<String> categoria;
    @FXML
    private ChoiceBox<String> genere;
    @FXML
    private TextArea output;
    @FXML
    private TableView<Competicio> tablaCompeticions;

    private final CompeticioDAO competicioDAO = new CompeticioDAO();

    @FXML
    public void initialize() {
        tipusCompeticio.getItems().addAll("Lliga", "Eliminatòria");
        categoria.getItems().addAll("Benjamí", "Infantil", "Junior", "sub-20", "sub-25", "Senior");
        genere.getItems().addAll("Masculí", "Femení");
    }

    @FXML
    private void veureCompeticionsCreades(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VeureCompeticions.fxml"));
            Parent root = loader.load();

            VeureCompeticionsController controller = loader.getController();
            controller.mostrarCompeticions();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Veure Competicions");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "Error al carregar la vista", "Hi ha hagut un error al carregar la vista de les competicions.", Alert.AlertType.INFORMATION);
        }
    }


    @FXML
    public void crearCompeticio() {
        String tipus = tipusCompeticio.getValue();
        String numEquipsStr = numEquips.getText();
        String cat = categoria.getValue();
        String gen = genere.getValue();

        try {
            int numEquipsValue = Integer.parseInt(numEquipsStr);
            Competicio competicio = new Competicio(0, tipus, numEquipsValue, cat, gen);

            GestorCompeticions.addCompeticio(competicio);

            // Guardar la competició a la base de dades
            competicioDAO.guardarCompeticio(competicio);

            mostrarAlerta("Èxit", "Competició Creada", "La competició s'ha creat correctament.", Alert.AlertType.INFORMATION);
            netejarCamps();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Número d'Equips Invàlid", "Si us plau, ingressa un número vàlid per al número d'equips.", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta("Error", "Error al crear la competició", "Hi ha hagut un error al crear la competició: " + ex.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void veureCompeticions() {
        Main mainApp = new Main();
        mainApp.showVeureCompeticions();
    }

    @FXML
    private void editarCompeticions() {
        Main mainApp = new Main();
        mainApp.showEditarCompeticions();
    }
    @FXML
    private void editarCompeticio(ActionEvent event) {
        Competicio selectedCompeticio = tablaCompeticions.getSelectionModel().getSelectedItem();
        if (selectedCompeticio != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarCompeticionsController.fxml"));
                Parent root = loader.load();
                EditarCompeticionsController controller = loader.getController();
                controller.initData(selectedCompeticio); // Puedes pasar datos adicionales si es necesario
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Competiciones");
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "Error al cargar la vista", "Ha ocurrido un error al cargar la vista de edición de competiciones: " + e.getMessage(), Alert.AlertType.INFORMATION);
            }
        } else {
            mostrarAlerta("Error", "Ninguna competición seleccionada", "Por favor, selecciona una competición para editar.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void eliminarCompeticio() {
        try {
            CompeticioDAO competicioDAO = new CompeticioDAO();
            if (competicioDAO.getAvailableCompetitionsCodes().isEmpty()) {
                mostrarAlerta("No hay competiciones", "No hay competiciones disponibles para eliminar.", "No hay mucho trabajo por aqui!!", Alert.AlertType.INFORMATION);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EliminarCompeticio.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Eliminar Competiciones");
                stage.show();
            }
        } catch (IOException | SQLException e) {
            mostrarAlerta("Error", "Error al cargar la vista", "Ha ocurrido un error al cargar la vista de eliminación de competiciones: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String title, String header, String content, Alert.AlertType information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void netejarCamps() {
        tipusCompeticio.getSelectionModel().clearSelection();
        numEquips.clear();
        categoria.getSelectionModel().clearSelection();
        genere.getSelectionModel().clearSelection();
    }

    public void setMainApp(Main main) {
    }
}


