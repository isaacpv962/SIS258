package bcp;

import java.io.*;
import java.net.*;

public class BCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            System.out.println("Servidor Banco BCP (TCP) escuchando en puerto 9876...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String message = in.readLine();
            String response = processRequest(message);
            out.println(response);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String processRequest(String message) {
        if (message.startsWith("Buscar:")) {
            String[] parts = message.substring(7).split("-");
            if (parts.length == 3) {
                String ci = parts[0];
                String nombres = parts[1];
                String apellidos = parts[2];
                
                // Caso especial para el ejemplo
                if (ci.equals("11021654") && nombres.equals("Juan Perez") && apellidos.equals("Segovia")) {
                    return "657654-5000.00"; // Cuenta en BCP para este cliente
                }
                
                // En una implementación real, aquí se consultaría la base de datos
                // return "123456-1000.50:789012-2500.75";
            }
            return "";
        } else if (message.startsWith("Congelar:")) {
            String[] parts = message.substring(9).split("-");
            if (parts.length == 2) {
                String nroCuenta = parts[0];
                double monto = Double.parseDouble(parts[1]);
                
                // En una implementación real, aquí se realizaría la retención
                return "SI-" + nroCuenta;
            }
            return "No-no encontrado";
        }
        return "";
    }
}