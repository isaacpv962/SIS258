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
import java.util.Scanner;

public class ClienteMultihilo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("1. Insertar número");
            System.out.println("2. Calcular Fibonacci");
            System.out.println("3. Calcular Factorial");
            System.out.println("4. Calcular Sumatoria");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            out.writeInt(opcion);
            out.flush();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un número: ");
                    int numero = scanner.nextInt();
                    out.writeInt(numero);
                    out.flush();
                    break;
                case 2:
                    System.out.println("Fibonacci: " + in.readLong());
                    break;
                case 3:
                    System.out.println("Factorial: " + in.readLong());
                    break;
                case 4:
                    System.out.println("Sumatoria: " + in.readLong());
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        in.close();
        out.close();
        socket.close();
        scanner.close();
    }
}