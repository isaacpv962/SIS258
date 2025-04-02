package asfi;

import common.Banco;
import common.Cuenta;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ASFIImpl extends UnicastRemoteObject implements ASFIInterface {
    private BancoClient bancoClient;

    public ASFIImpl() throws RemoteException {
        super();
        bancoClient = new BancoClient();
    }

    @Override
    public List<Cuenta> consultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        List<Cuenta> cuentas = new ArrayList<>();
        
        // Consultar Banco Mercantil (UDP)
        List<Cuenta> cuentasMercantil = bancoClient.consultarMercantil(ci, nombres, apellidos);
        cuentas.addAll(cuentasMercantil);
        
        // Consultar Banco BCP (TCP)
        List<Cuenta> cuentasBCP = bancoClient.consultarBCP(ci, nombres, apellidos);
        cuentas.addAll(cuentasBCP);
        
        return cuentas;
    }

    @Override
    public boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException {
        return bancoClient.retenerMonto(cuenta, monto);
    }
}