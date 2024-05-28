package com.example.gestorcompeticionsuf5uf6;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class VeureCompeticionsController {

    @FXML
    private TextArea competicionsTextArea;

    public void initialize() {
        mostrarCompeticions();
    }

    void mostrarCompeticions() {
        StringBuilder sb = new StringBuilder();
        for (Competicio competicio : GestorCompeticions.getCompeticions()) {
            sb.append(competicio.toString()).append("\n");
        }
        competicionsTextArea.setText(sb.toString());
    }
}
