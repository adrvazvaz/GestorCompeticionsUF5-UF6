package com.example.gestorcompeticionsuf5uf6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GestioCompeticionsController.fxml"));
        Parent root = fxmlLoader.load();
        GestioCompeticionsController gestioCompeticionsController = fxmlLoader.getController();
        gestioCompeticionsController.setMainApp(this);

        primaryStage.setTitle("Gestor de Competicions FCBQ");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void showEditarCompeticions() {
        loadFXML("EditarCompeticionsController.fxml", "Editar Competicions");
    }

    public void showEliminarCompeticio() {
        loadFXML("EliminarCompeticions.fxml", "Eliminar Competicions");
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

    public void showVeureCompeticions() {
        loadFXML("VeureCompeticions.fxml", "Veure Competicions");
    }
}


