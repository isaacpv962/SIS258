/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Dell
 */
public class Server {
    
    public static void main(String[] Args)
    {
        int port=6789;  
    try {
      
      DatagramSocket socketUDP = new DatagramSocket(port);
      byte[] bufer = new byte[1000];
      Operaciones operaciones= new Operaciones();

      while (true) {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion =
          new DatagramPacket(bufer, bufer.length);

        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);
          String mensaje = new String(peticion.getData(), 0, peticion.getLength());
          String[] partes = mensaje.split(":");
          String comando = partes[0];
          String respuesta = "";


          switch (comando) {
              case "INSERTAR":
                  int numero = Integer.parseInt(partes[1]);
                  operaciones.insertarNumero(numero);
                  respuesta = "Número insertado: " + numero;
                  break;
              case "FIBONACCI":
                  respuesta = "Fibonacci: " + operaciones.calcularFibonacci();
                  break;
              case "FACTORIAL":
                  respuesta = "Factorial: " + operaciones.calcularFactorial();
                  break;
              case "SUMATORIA":
                  respuesta = "Sumatoria: " + operaciones.calcularSumatoria();
                  break;
              default:
                  respuesta = "Comando no válido";
          }
                         ;
        // Construimos el DatagramPacket para enviar la respuesta
          byte[] datosRespuesta = respuesta.getBytes();
          DatagramPacket paqueteRespuesta = new DatagramPacket(
                  datosRespuesta, datosRespuesta.length,
                  peticion.getAddress(), peticion.getPort()
          );

          // Enviamos la respuesta, que es un eco
          socketUDP.send(paqueteRespuesta);




      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
        
    }
    
}
