/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_sis258;

/**
 *
 * @author PC-Solution 3-8-21
 */
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServerMultihilo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Servidor iniciado...");

        while (true) {
            Socket socket = serverSocket.accept();
            pool.execute(new ManejadorCliente(socket));
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket socket;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                Operaciones operaciones = new Operaciones();
                int opcion;
                do {
                    opcion = in.readInt();
                    switch (opcion) {
                        case 1:
                            operaciones.insertarNumero(in.readInt());
                            break;
                        case 2:
                            out.writeLong(operaciones.calcularFibonacci());
                            out.flush();
                            break;
                        case 3:
                            out.writeLong(operaciones.calcularFactorial());
                            out.flush();
                            break;
                        case 4:
                            out.writeLong(operaciones.calcularSumatoria());
                            out.flush();
                            break;
                        case 5:
                            System.out.println("Cliente desconectado.");
                            break;
                        default:
                            out.writeUTF("Opción no válida.");
                            out.flush();
                    }
                } while (opcion != 5);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}