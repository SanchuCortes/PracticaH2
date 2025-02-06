package com.example.prueba;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ControladorDB.crearTabla();
        MeteoDAO meteoDAO = new MeteoDAO();

        List<String> urlsConcellos = Arrays.asList(
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15030&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=32054&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=36057&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15036&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=36038&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=27028&request_locale=gl",
                "https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15078&request_locale=gl"
        );

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            for (String url : urlsConcellos) {
                System.out.println("Obteniendo datos de: " + url);
                String jsonResponse = APIMeteoGalicia.obtenerDatosMeteorologicos(url);

                JsonNode rootNode = objectMapper.readTree(jsonResponse);

                int idConcello = rootNode.get("predConcello").get("idConcello").asInt();
                String nombre = rootNode.get("predConcello").get("nome").asText();
                String fecha = rootNode.get("predConcello").get("listaPredDiaConcello").get(0).get("dataPredicion").asText();
                int tMax = rootNode.get("predConcello").get("listaPredDiaConcello").get(0).get("tMax").asInt();

                meteoDAO.insertarPrediccion(idConcello, nombre, fecha, tMax, 0, 0, 0, 0, 0, 0, 0, 0);
            }

            System.out.println("📊 Datos almacenados en MySQL:");
            meteoDAO.listarPredicciones();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
