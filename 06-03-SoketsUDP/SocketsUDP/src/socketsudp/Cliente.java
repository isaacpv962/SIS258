/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socketsudp;

import java.net.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Dell
 */
public class Cliente {
    
    public static void main(String[] Args)
    {
        int puerto = 6789;
    Scanner sc=new Scanner(System.in);
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Insertar número");
                System.out.println("2. Calcular Fibonacci");
                System.out.println("3. Calcular Factorial");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                String mensaje = "";
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el número: ");
                        int numero = scanner.nextInt();
                        mensaje = "INSERTAR:" + numero;
                        break;
                    case 2:
                        mensaje = "FIBONACCI";
                        break;
                    case 3:
                        mensaje = "FACTORIAL";
                        break;
                    case 4:
                        mensaje = "SUMATORIA";
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                        continue;
                }

                // Enviar mensaje al servidor
                byte[] datosEnviados = mensaje.getBytes();
                DatagramPacket paqueteEnviado = new DatagramPacket(
                        datosEnviados, datosEnviados.length, direccionServidor, puerto
                );
                socket.send(paqueteEnviado);

                // Recibir respuesta del servidor
                byte[] buffer = new byte[1000];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
