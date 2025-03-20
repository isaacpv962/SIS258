package lab2;

import lab2.ISegip;
import lab2.Segip;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Servidor RMI para el servicio SEGIP
 */
public class ServidorSegip {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1092);
            ISegip segip = new Segip();
            Naming.rebind("rmi://localhost:1092/Segip", segip);
            System.out.println("Servidor SEGIP listo en puerto 1092...");
        } catch (RemoteException | java.net.MalformedURLException e) {
            e.printStackTrace();
        }
    }
}