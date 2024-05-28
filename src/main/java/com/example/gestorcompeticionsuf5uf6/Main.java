package com.example.gestorcompeticionsuf5uf6;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GestioCompeticionsController.fxml"));
        Parent root = fxmlLoader.load();
        GestioCompeticionsController gestioCompeticionsController = fxmlLoader.getController();
        gestioCompeticionsController.setMainApp(this);

        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Gestor de Competicions FCBQ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void showVeureCompeticions() {
        loadFXML("VeureCompeticions.fxml", "Veure Competicions");
    }

    @FXML
    private void editarCompeticions() {
        loadFXML("EditarCompeticionsController.fxml", "Editar Competicions");
    }

    @FXML
    private void jugarCompeticions() {
        loadFXML("JugarCompeticions.fxml", "Jugar Competicions");
    }

    private void loadFXML(String fxmlFileName, String windowTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(windowTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

