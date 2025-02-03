package com.example.prueba;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIMeteoGalicia {
    private static final String API_KEY = "2YU93g17HLHd3kQNwWf65x1bMK7Xu50x8PPrdc4NA6ZRj31GG8p6d26a26Ki8S82"; // Clave API

    public static String obtenerDatosMeteorologicos(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Crear la solicitud HTTP con la clave API en los headers
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-api-key", API_KEY) // Añadir la API Key en el header
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar qué se devuelve en la respuesta (para depurar)
        System.out.println("📡 Respuesta de API (" + url + "):");
        System.out.println(response.body());

        // Comprobar si la respuesta es exitosa (código 200)
        if (response.statusCode() != 200) {
            throw new Exception("Error al obtener datos de la API. Código de estado: " + response.statusCode());
        }

        return response.body();
    }
}
