package com.example.prueba;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeteoDAO {
    public void insertarPrediccion(int idConcello, String nombre, String fechaISO, int tMax, int tMin,
                                   int vientoManha, int vientoTarde, int vientoNoite,
                                   int precipManha, int precipTarde, int precipNoite, int uvMax) {
        String sql = "INSERT INTO predicciones (idConcello, nombre, fecha, tMax, tMin, " +
                "vientoManha, vientoTarde, vientoNoite, precipitacionManha, precipitacionTarde, precipitacionNoite, uvMax) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ControladorDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String fechaFormateada = fechaISO.split("T")[0];
            Date fechaSQL = Date.valueOf(fechaFormateada);

            pstmt.setInt(1, idConcello);
            pstmt.setString(2, nombre);
            pstmt.setDate(3, fechaSQL);
            pstmt.setInt(4, tMax);
            pstmt.setInt(5, tMin);
            pstmt.setInt(6, vientoManha);
            pstmt.setInt(7, vientoTarde);
            pstmt.setInt(8, vientoNoite);
            pstmt.setInt(9, precipManha);
            pstmt.setInt(10, precipTarde);
            pstmt.setInt(11, precipNoite);
            pstmt.setInt(12, uvMax);

            pstmt.executeUpdate();
            System.out.println(" Datos insertados correctamente en MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarPredicciones() {
        String sql = "SELECT * FROM predicciones";

        try (Connection conn = ControladorDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Concello: " + rs.getString("nombre") +
                        ", Fecha: " + rs.getString("fecha") +
                        ", Temp Max: " + rs.getInt("tMax") +
                        ", Temp Min: " + rs.getInt("tMin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
