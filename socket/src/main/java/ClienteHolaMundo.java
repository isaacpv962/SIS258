/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ClienteHolaMundo {
     public static void main(String[] args) {
        // TODO code application logic here
         int port = 5555;
        try {
            Socket client = new Socket("localhost", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            toServer.println("Hola Mundo desde el Cliente");
            String result = fromServer.readLine();
            System.out.println("cadena devuelta por el servidor es: " + result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public class InvertirString {
     public String invertirString(String str) {
        // Convertir el String a un array de caracteres
        char[] caracteres = str.toCharArray();
        
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
        
        // Convertir el array de caracteres de nuevo a un String
        return new String(caracteres);
    }

   
}

}