/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Dell
 */
public interface IUniversidad extends Remote{
    
    public  Diploma EmitirDiploma(String ci,String nombres,String primerApellido,String segundoapellido,String fecha_nacimiento,Carrera carrera) throws RemoteException;
    
    
}
