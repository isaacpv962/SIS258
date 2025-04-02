package asfi;

import common.Cuenta;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ASFIInterface extends Remote {
    List<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;
    boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException;
}