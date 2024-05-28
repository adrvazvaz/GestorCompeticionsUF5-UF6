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

            output.clear();
            output.appendText("Contingut de Veure Competicions:\n\n");

            VeureCompeticionsController controller = loader.getController();
            String content = "";
            for (Competicio competicio : GestorCompeticions.getCompeticions()) {
                content += competicio.toString() + "\n";
            }
            output.appendText(content);
        } catch (IOException e) {
            e.printStackTrace();
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
            CompeticioDAO competicioDAO = new CompeticioDAO();
            competicioDAO.guardarCompeticio(competicio);

            mostrarAlerta("Èxit", "Competició Creada", "La competició s'ha creat correctament.");
            netejarCamps();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Número d'Equips Invàlid", "Si us plau, ingressa un número vàlid per al número d'equips.");
        }
    }

    @FXML
    private void editarCompeticions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarCompeticionsController.fxml"));
            Parent root = loader.load();
            EditarCompeticionsController controller = loader.getController();

            // Obtener la competición seleccionada desde la tabla
            Competicio selectedCompeticio = tablaCompeticions.getSelectionModel().getSelectedItem();
            if (selectedCompeticio == null) {
                mostrarAlerta("Error", "Competició no Seleccionada", "Si us plau, selecciona una competició per editar.");
                return;
            }

            // Obtener el código de la competición seleccionada
            int selectedCodigo = selectedCompeticio.getCodigo();

            // Cargar los datos de la competición desde la base de datos
            CompeticioDAO competicioDAO = new CompeticioDAO();
            Competicio competicio = competicioDAO.recuperarCompeticioPorCodigo(selectedCodigo);

            // Actualizar los campos de la interfaz gráfica con los datos de la competición
            controller.initialize(competicio);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Competicions");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void jugarCompeticio(ActionEvent actionEvent) {
        String tipus = tipusCompeticio.getValue();
        String numEquipsStr = numEquips.getText();
        String cat = categoria.getValue();
        String gen = genere.getValue();

        if (tipus == null || numEquipsStr.isEmpty() || cat == null || gen == null) {
            mostrarAlerta("Error", "Falten Dades", "Si us plau, completa tots els camps abans de jugar la competició.");
            return;
        }

        try {
            int numEquipsValue = Integer.parseInt(numEquipsStr);

            if (tipus.equals("Lliga")) {
                CompeticioDAO competicioDAO = new CompeticioDAO();
                int codigo = tablaCompeticions.getSelectionModel().getSelectedItem().getCodigo();
                jugarLliga(codigo, tipus, numEquipsValue, cat, gen);
            } else if (tipus.equals("Eliminatòria")) {
                jugarEliminatoria(numEquipsValue, cat, gen);
            }
            netejarCamps();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Número d'Equips Invàlid", "Si us plau, ingressa un número vàlid per al número d'equips.");
        }
    }

    private void mostrarAlerta(String title, String header, String content) {
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

    private void jugarLliga(int codigo, String tipus, int numEquipsValue, String cat, String gen) {
        Lliga lliga = new Lliga(codigo, tipus, numEquipsValue, cat, gen);
    }

    private void jugarEliminatoria(int numEquipsValue, String cat, String gen) {
        Eliminatoria eliminatoria = new Eliminatoria(numEquipsValue, cat, gen);
    }

    public void setMainApp(Main main) {
    }
}



