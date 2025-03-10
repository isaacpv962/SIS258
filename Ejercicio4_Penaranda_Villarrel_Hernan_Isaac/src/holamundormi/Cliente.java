/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package holamundormi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Cliente {
    public static void main (String[] args)
    {
        IOperaciones operaciones;
        Scanner sc=new Scanner(System.in);
        try {
            operaciones = (IOperaciones)Naming.lookup("rmi://localhost/O"); // instanciar un objeto remoto

            System.out.println("introduzca un valor");
            int n= sc.nextInt();
            operaciones.setN(n);
            System.out.println("El valor del factorial de "+n+" es: "+operaciones.Factorial());
            System.out.println("El valor de Fibonacci de "+n+" es: "+operaciones.Fibonacci());
            System.out.println("El valor de la sumatoria de "+n+" es: "+operaciones.Sumatoria());
            
            
            
            
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
