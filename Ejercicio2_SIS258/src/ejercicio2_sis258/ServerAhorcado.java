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
import java.util.*;

public class ServerAhorcado {
    private static final int MAX_ERRORES = 7; // Máximo de errores permitidos
    private static final int PUERTO = 12345; // Puerto del servidor
    private static List<String> palabras = Arrays.asList("java", "programacion", "netbeans", "socket", "ahorcado");
    private static String palabraSecreta;
    private static Set<Character> letrasAdivinadas = new HashSet<>();
    private static int errores = 0;
    private static List<PrintWriter> jugadores = new ArrayList<>(); // Lista de jugadores
    private static int turnoActual = 0; // Índice del jugador actual

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor del Ahorcado iniciado en el puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socket);

                // Iniciar un nuevo hilo para manejar al cliente
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                jugadores.add(out); // Agregar el jugador a la lista
                new Thread(new ManejadorCliente(socket, out)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase interna para manejar cada cliente en un hilo separado
    private static class ManejadorCliente implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ManejadorCliente(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Iniciar el juego si es el primer jugador
                if (jugadores.size() == 1) {
                    iniciarJuego();
                }

                // Notificar al jugador si es su turno
                if (jugadores.indexOf(out) == turnoActual) {
                    out.println("Es tu turno. Ingresa una letra:");
                } else {
                    out.println("Espera tu turno...");
                }

                // Manejar la interacción con el cliente
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (jugadores.indexOf(out) == turnoActual) {
                        if (inputLine.length() == 1) {
                            char letra = inputLine.charAt(0);
                            if (!letrasAdivinadas.contains(letra)) {
                                if (palabraSecreta.contains(String.valueOf(letra))) {
                                    letrasAdivinadas.add(letra);
                                    if (palabraAdivinada()) {
                                        broadcast("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
                                        break;
                                    }
                                } else {
                                    errores++;
                                    if (errores >= MAX_ERRORES) {
                                        broadcast("¡Perdiste! La palabra era: " + palabraSecreta);
                                        break;
                                    }
                                }
                            }
                            broadcast(estadoActual());
                            cambiarTurno(); // Cambiar al siguiente jugador
                        }
                    } else {
                        out.println("No es tu turno. Espera...");
                    }
                }
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

        private void iniciarJuego() {
            palabraSecreta = palabras.get(new Random().nextInt(palabras.size()));
            letrasAdivinadas.clear();
            errores = 0;
            broadcast("Bienvenido al juego del ahorcado. Tienes " + MAX_ERRORES + " intentos.");
            broadcast(estadoActual());
        }

        private String estadoActual() {
            StringBuilder estado = new StringBuilder();
            for (char letra : palabraSecreta.toCharArray()) {
                if (letrasAdivinadas.contains(letra)) {
                    estado.append(letra);
                } else {
                    estado.append("_");
                }
                estado.append(" ");
            }
            estado.append("\nErrores: ").append(errores).append("/").append(MAX_ERRORES);
            return estado.toString();
        }

        private boolean palabraAdivinada() {
            for (char letra : palabraSecreta.toCharArray()) {
                if (!letrasAdivinadas.contains(letra)) {
                    return false;
                }
            }
            return true;
        }

        private void cambiarTurno() {
            turnoActual = (turnoActual + 1) % jugadores.size(); // Cambiar al siguiente jugador
            broadcast("Es el turno del jugador " + (turnoActual + 1));
            jugadores.get(turnoActual).println("Es tu turno. Ingresa una letra:");
        }

        private void broadcast(String mensaje) {
            for (PrintWriter jugador : jugadores) {
                jugador.println(mensaje);
            }
        }
    }
}