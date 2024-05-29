module com.example.gestorcompeticionsuf5uf6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.gestorcompeticionsuf5uf6 to javafx.fxml;
    exports com.example.gestorcompeticionsuf5uf6;
    exports com.example.utilities;
    opens com.example.utilities to javafx.fxml;
}