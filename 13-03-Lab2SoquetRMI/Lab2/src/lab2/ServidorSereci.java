package lab2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorSereci {
    public static void main(String[] args) {
        final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciando el servidor UDP...");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                String mensaje = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                String respuesta;
                if (!mensaje.isEmpty()) {
                    respuesta = "verificación correcta";
                } else {
                    respuesta = "fecha nacimiento incorrecta";
                }

                InetAddress direccionCliente = peticion.getAddress();
                int puertoCliente = peticion.getPort();

                byte[] bufferRespuesta = respuesta.getBytes();
                DatagramPacket respuestaPacket = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
                socketUDP.send(respuestaPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}