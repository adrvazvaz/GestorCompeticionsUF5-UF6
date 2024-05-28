package com.example.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que implementa el patrón Singleton para establecer la conexión con la BBDD
 */
public class ConnectDB {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gestiocompeticions";
    private static final String JDBC_USER = "tu_usuario";
    private static final String JDBC_PASSWORD = "tu_contraseña";
    private static Connection instance;

    /* Se privatiza el constructor para que no sea posible hacer new ConnectDB()
       desde otro lugar que no sea esta misma clase */
    private ConnectDB() {}

    /* Para utilizar la única instancia de la clase, se llamará al método estático
    getInstance(). La primera vez que se llame, instance será null, las siguientes veces
    se devolverá el objeto Connection ya creado, asegurando así que solo se cree un
    objeto del tipo Connection */
    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Open Database");
        }
        return instance;
    }

    public static void closeConnection() throws SQLException {
        if (instance != null) {
            instance.close();
            System.out.println("Database Closed");
        }
    }
}

