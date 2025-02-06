package com.example.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ControladorDB {
    private static final String URL = "jdbc:mysql://localhost:3307/meteogalicia";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS predicciones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "idConcello INT, nombre VARCHAR(255), " +
                "fecha DATE, tMax INT, tMin INT, " +
                "vientoManha INT, vientoTarde INT, vientoNoite INT, " +
                "precipitacionManha INT, precipitacionTarde INT, precipitacionNoite INT, " +
                "uvMax INT)";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(" Tabla creada correctamente en MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
