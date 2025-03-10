/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package holamundormi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dell
 */
public class Operaciones extends UnicastRemoteObject implements IOperaciones {

    public Operaciones()throws RemoteException {
        super();
    }

    private int n;

    // Método para establecer el valor de n
    public int setN(int n) throws RemoteException {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        this.n = n;
        return n;
    }

    // Método para calcular el factorial de n
    public long Factorial()  throws RemoteException{
        if (n < 0) {
            throw new IllegalStateException("n no ha sido establecido o es negativo");
        }
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    // Método para calcular el n-ésimo número de Fibonacci
    public long Fibonacci() throws RemoteException {
        if (n < 0) {
            throw new IllegalStateException("n no ha sido establecido o es negativo");
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long fibPrev = 0;
        long fibCurr = 1;
        for (int i = 2; i <= n; i++) {
            long fibNext = fibPrev + fibCurr;
            fibPrev = fibCurr;
            fibCurr = fibNext;
        }
        return fibCurr;
    }

    // Método para calcular la sumatoria de 1 a n
    public long Sumatoria() throws RemoteException {
        if (n < 0) {
            throw new IllegalStateException("n no ha sido establecido o es negativo");
        }
        return (long) n * (n + 1) / 2;
    }
    
}
