package holamundormi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Dell
 */
public interface IOperaciones extends Remote {
    public int setN(int n) throws RemoteException;
    public long Factorial() throws RemoteException;
    public long Fibonacci() throws RemoteException;
    public long Sumatoria() throws RemoteException;
}
