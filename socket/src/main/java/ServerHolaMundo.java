/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServerHolaMundo {
    
     public static void main(String[] args) {
            
           
         
            int port = 5002;
            ServerSocket server;
            
            try {
            // TODO code application logic here
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion entre cliente y servidor para comunicacion bidireccional
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            System.out.println("Cliente se conecto");
            String recibido = fromClient.readLine();
            
             char[] caracteres = recibido.toCharArray();
        
        // Invertir el array de caracteres
            int i = 0;
            int j = caracteres.length - 1;
            while (i < j) {
                // Intercambiar caracteres
                char temp = caracteres[i];
                caracteres[i] = caracteres[j];
                caracteres[j] = temp;
            
            // Mover los índices
                i++;
                j--;
            }
        
           String reves= new String (caracteres); 
            System.out.println("El cliente envio el mensaje:"+recibido);
            toClient = new PrintStream(client.getOutputStream());
            toClient.println("String al reves="+reves);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
            
       
    }
    
    
}