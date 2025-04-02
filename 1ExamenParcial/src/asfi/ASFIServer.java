package asfi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ASFIServer {
    public static void main(String[] args) {
        try {
            ASFIImpl obj = new ASFIImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ASFI", (Remote) obj);
            System.out.println("Servidor ASFI listo...");
        } catch (Exception e) {
            System.err.println("Excepción del servidor ASFI: " + e.toString());
            e.printStackTrace();
        }
    }
}
