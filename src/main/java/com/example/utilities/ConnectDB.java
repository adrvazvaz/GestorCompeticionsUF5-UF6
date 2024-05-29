package com.example.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que implementa el patrón Singleton para establecer la conexión con la base de datos
 */
public class ConnectDB {
    private static Connection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/gestiocompeticions";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private ConnectDB() {}

    /**
     * Método para obtener una instancia única de la conexión a la base de datos
     * @return la conexión a la base de datos
     * @throws SQLException si ocurre un error al conectar con la base de datos
     */
    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Conexión establecida con la base de datos");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Error al cargar el controlador JDBC", e);
            }
        }
        return instance;
    }

    /**
     * Método para cerrar la conexión a la base de datos
     * @throws SQLException si ocurre un error al cerrar la conexión
     */
    public static void closeConnection() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.close();
            System.out.println("Conexión cerrada");
        }
    }
}
