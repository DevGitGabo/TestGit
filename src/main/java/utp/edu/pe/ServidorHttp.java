package utp.edu.pe;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ServidorHttp {

    public static void main(String[] args) throws Exception {
        // Crear un objeto servidor HTTP en el puerto 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // Adjuntar un controlador para la ruta "/test"
        server.createContext("/test", new MyHandler());
        // Iniciar el servidor
        server.start();
    }

    // Clase que implementa el controlador HTTP
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Obtener el método de la petición (GET o POST)
            String method = t.getRequestMethod();
            // Si es GET, responder con un mensaje de "Hola mundo"
            if (method.equals("GET")) {
                // Crear una respuesta con código 200 (OK) y longitud del mensaje
                String response = "Hola mundo";
                t.sendResponseHeaders(200, response.length());
                // Obtener el stream de salida para escribir la respuesta
                OutputStream os = t.getResponseBody();
                // Escribir la respuesta y cerrar el stream
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}