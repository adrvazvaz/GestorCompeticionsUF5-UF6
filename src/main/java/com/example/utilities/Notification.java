package com.example.utilities;

import javafx.scene.control.Alert;

public class Notification {

    public static void showInfo(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Information", message);
    }

    public static void showError(String message, String eMessage) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
