/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dell
 */
public class Segip extends UnicastRemoteObject implements ISegip  {

    public Segip() throws RemoteException {
        super();
    }

    @Override
    public boolean Verificar(String ci, String nombres, String apellidos) throws RemoteException {
        return ci != null && !ci.trim().isEmpty();
    }
    
    
}
