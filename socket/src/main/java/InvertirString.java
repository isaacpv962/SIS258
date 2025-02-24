/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC-Solution 3-8-21
 */
public class InvertirString {
     public static String invertirString(String str) {
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
