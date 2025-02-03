package com.example.prueba;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {

        ControladorDB.crearTabla();
        MeteoDAO meteoDAO = new MeteoDAO();

        try{
            String jsonResponse = APIMeteoGalicia.obtenerDatosMeteorologicos();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            int idConcello = rootNode.get("predConcello").get("idConcello").asInt();
            String nombre = rootNode.get("predConcello").get("nome").asText();
            String fecha = rootNode.get("predConcello").get("listaPredDiaConcello").get(0).get("dataPredicion").asText();
            int tMax = rootNode.get("predConcello").get("listaPredDiaConcello").get(0).get("tMax").asInt();
            int tMin = rootNode.get("predConcello").get("listaPredDiaConcello").get(0).get("tMin").asInt();

            meteoDAO.insertarPrediccion(idConcello, nombre, fecha, tMax, tMin, 0, 0, 0, 0, 0, 0, 0);
            meteoDAO.listarPredicciones();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
