package mercantil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MercantilServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(6789)) {
            System.out.println("Servidor Banco Mercantil (UDP) escuchando en puerto 6789...");
            
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String message = new String(packet.getData(), 0, packet.getLength());
                String response = processRequest(message);
                
                byte[] responseBuffer = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(
                    responseBuffer, responseBuffer.length, 
                    packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                    return ""; // No hay cuentas en Mercantil para este cliente
                }
                
               
            }
            return "";
        } else if (message.startsWith("Congelar:")) {
            String[] parts = message.substring(9).split("-");
            if (parts.length == 2) {
                String nroCuenta = parts[0];
                double monto = Double.parseDouble(parts[1]);
                
               
                return "SI-" + nroCuenta;
            }
            return "No-no encontrado";
        }
        return "";
    }
}