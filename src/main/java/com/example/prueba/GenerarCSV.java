package com.example.prueba;

import com.fasterxml.jackson.databind.ObjectMapper; // Importa la clase ObjectMapper de Jackson para deserializar JSON
import java.io.*; // Importa clases para trabajar con entrada y salida de archivos
import java.net.HttpURLConnection; // Importa clases para manejar conexiones HTTP
import java.net.URL; // Importa la clase URL para trabajar con direcciones web
import java.text.SimpleDateFormat; // Importa la clase SimpleDateFormat para manejar fechas
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas
import java.util.Date; // Importa la clase Date para manejar fechas y horas

public class GenerarCSV {

    // Lista para almacenar las URLs de los concellos
    private static ArrayList<String> linksConcellos = new ArrayList<>();
    // Clave de API para realizar las solicitudes a la API de predicción del tiempo
    private static final String API_KEY = "2YU93g17HLHd3kQNwWf65x1bMK7Xu50x8PPrdc4NA6ZRj31GG8p6d26a26Ki8S82";

    public static void main(String[] args) {
        // Agregar URLs de los concellos para consultar
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15030&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=32054&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=36057&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15036&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=36038&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=27028&request_locale=gl");
        linksConcellos.add("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc=15078&request_locale=gl");

        try {
            // Obtener la fecha actual en formato "dd-MM-yyyy"
            String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            // Crear el nombre del archivo CSV usando la fecha
            String csvFile = fechaActual + "-galicia.csv";

            // Abrir el archivo CSV en modo sobrescritura (no append)
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false)); // false asegura que se sobrescriba el archivo si ya existe

            // Escribimos los encabezados solo una vez en el archivo CSV
            writer.write("IDConcello,NombreConcello,Fecha,TemperaturaMaxima,TemperaturaMinima,VientoManha,VientoNoite,VientoTarde,PrecipitacionManha,PrecipitacionNoite,PrecipitacionTarde,CeoManha,CeoNoite,CeoTarde,UVMax\n");

            // Iterar sobre todos los links de concellos
            for (String url : linksConcellos) {
                // Obtener la respuesta JSON desde la API para cada URL
                String jsonResponse = getJsonFromApi(url);

                // Deserializar el JSON en un objeto Prediccion usando ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                Prediccion prediccion = objectMapper.readValue(jsonResponse, Prediccion.class);

                // Obtener la información del concello de la predicción
                Prediccion.PredConcello concello = prediccion.getPredConcello();
                int idConcello = concello.getIdConcello(); // Obtener el ID del concello
                String nombreConcello = concello.getNome(); // Obtener el nombre del concello

                // Obtener el primer día de predicción para el concello
                Prediccion.PredConcello.PredDiaConcello detalle = concello.getListaPredDiaConcello().get(0);

                // Extraer los valores de los detalles de predicción
                String fecha = detalle.getDataPredicion();
                int tMax = detalle.gettMax(); // Temperatura máxima
                int tMin = detalle.gettMin(); // Temperatura mínima
                String vientoManha = String.valueOf(detalle.getVento().getManha()); // Viento mañana
                String vientoNoite = String.valueOf(detalle.getVento().getNoite()); // Viento noche
                String vientoTarde = String.valueOf(detalle.getVento().getTarde()); // Viento tarde
                String precipManha = String.valueOf(detalle.getPchoiva().getManha()); // Precipitación mañana
                String precipNoite = String.valueOf(detalle.getPchoiva().getNoite()); // Precipitación noche
                String precipTarde = String.valueOf(detalle.getPchoiva().getTarde()); // Precipitación tarde
                String ceoManha = String.valueOf(detalle.getCeo().getManha()); // Estado cielo mañana
                String ceoNoite = String.valueOf(detalle.getCeo().getNoite()); // Estado cielo noche
                String ceoTarde = String.valueOf(detalle.getCeo().getTarde()); // Estado cielo tarde
                int uvMax = detalle.getUvMax(); // UV máximo

                // Imprimir los detalles en la consola
                System.out.println("==========================================");
                System.out.println("Concello: " + nombreConcello + " (ID: " + idConcello + ")");
                System.out.println("Fecha: " + fecha);
                System.out.println("Estado del cielo:");
                System.out.println("  - Mañana: " + ceoManha);
                System.out.println("  - Tarde: " + ceoTarde);
                System.out.println("  - Noche: " + ceoNoite);
                System.out.println("Temperatura:");
                System.out.println("  - Máxima: " + tMax + "°C");
                System.out.println("  - Mínima: " + tMin + "°C");
                System.out.println("Viento:");
                System.out.println("  - Mañana: " + vientoManha + " km/h");
                System.out.println("  - Tarde: " + vientoTarde + " km/h");
                System.out.println("  - Noche: " + vientoNoite + " km/h");
                System.out.println("Precipitaciones:");
                System.out.println("  - Mañana: " + precipManha + " mm");
                System.out.println("  - Tarde: " + precipTarde + " mm");
                System.out.println("  - Noche: " + precipNoite + " mm");
                System.out.println(calcularHumedadRelativa(tMax, tMin)); // Calcular la humedad relativa
                System.out.println("Cobertura nubosa: " + calcularCobNubosa(detalle.getCeo().getManha(), detalle.getCeo().getNoite(), detalle.getCeo().getTarde())); // Calcular la cobertura nubosa
                System.out.println("==========================================");

                // Escribir los valores en una línea del archivo CSV
                writer.write(String.join(",",
                        String.valueOf(idConcello),
                        nombreConcello,
                        fecha,
                        String.valueOf(tMax),
                        String.valueOf(tMin),
                        vientoManha,
                        vientoNoite,
                        vientoTarde,
                        precipManha,
                        precipNoite,
                        precipTarde,
                        ceoManha,
                        ceoNoite,
                        ceoTarde,
                        String.valueOf(uvMax)) + "\n");
            }

            // Cerrar el archivo CSV después de escribir los datos
            writer.close();

            // Mensaje indicando que el archivo CSV se generó correctamente
            System.out.println("Archivo CSV generado exitosamente: " + csvFile);
        } catch (Exception e) {
            // Capturar cualquier error y mostrar un mensaje en caso de fallo
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener la respuesta JSON de la API a través de una conexión HTTP
    private static String getJsonFromApi(String API_URL) throws IOException {
        // Crear la URL completa agregando la clave API
        String urlString = API_URL + "&key=" + API_KEY;

        // Realizar la conexión HTTP
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Verificar si la respuesta HTTP es exitosa (código 200)
        if (connection.getResponseCode() != 200) {
            throw new IOException("Error en la respuesta de la API: " + connection.getResponseCode());
        }

        // Leer la respuesta JSON de la conexión
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        // Leer línea por línea y construir la respuesta JSON
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString(); // Retornar la respuesta como un String
    }

    // Método para calcular la cobertura nubosa
    public static String calcularCobNubosa(int estadoCeoManha, int estadoCeoTarde, int estadoCeoNoite) {
        // Encontrar el valor máximo observado entre los tres periodos
        int maxObservado = Math.max(estadoCeoManha, Math.max(estadoCeoTarde, estadoCeoNoite));

        // Calcular el valor medio de los tres estados del cielo
        int valorMedio = (estadoCeoManha + estadoCeoTarde + estadoCeoNoite) / 3;

        // Escalar a porcentaje basado en el valor máximo observado
        int porcentajeBase100 = (valorMedio * 100) / maxObservado;

        // Devolver el resultado basado en el rango del porcentaje
        if (porcentajeBase100 <= 20) {
            return "10%, Despejado";
        } else if (porcentajeBase100 <= 50) {
            return "40%, Parcialmente nublado";
        } else if (porcentajeBase100 <= 80) {
            return "80%, Nublado";
        } else {
            return "100%, Muy nublado";
        }
    }

    // Metodo para calcular la humedad relativa estimada
    public static String calcularHumedadRelativa(int tMax, int tMin) {
        // Evitar división por cero en caso de que tMax sea 0
        if (tMax == 0) {
            return "Datos insuficientes para calcular la humedad relativa";
        }

        // Estimación simple de la humedad relativa como un porcentaje
        int humedadRelativa = (tMin * 100) / tMax;

        // Devolver el resultado como una cadena con el porcentaje
        return "Humedad relativa estimada: " + humedadRelativa + "%";
    }
}
