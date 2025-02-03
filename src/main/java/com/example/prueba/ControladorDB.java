package com.example.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ControladorDB {

    private static final String URL = "jdbc:h2:./meteogaliciaDB";
    private static final String USUARIO = "root";
    private static final String CONTRA = "";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USUARIO,CONTRA);
    }

    public static void crearTabla(){

        String sql = "CREATE TABLE IF NOT EXISTS predicciones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "idConcello INT, " +
                "nombre VARCHAR(255), " +
                "fecha DATE, " +
                "tMax INT, " +
                "tMin INT, " +
                "vientoManha INT, " +
                "vientoTarde INT, " +
                "vientoNoite INT, " +
                "precipitacionManha INT, " +
                "precipitacionTarde INT, " +
                "precipitacionNoite INT, " +
                "uvMax INT)";

        try(Connection conn = getConnection();
            Statement stm = conn.createStatement()){
            stm.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
