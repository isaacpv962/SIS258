package asfi;

import common.Banco;
import common.Cuenta;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class BancoClient {
    // Configuración de puertos y direcciones
    private static final String MERCANTIL_HOST = "localhost";
    private static final int MERCANTIL_PORT = 6789;
    private static final String BCP_HOST = "localhost";
    private static final int BCP_PORT = 9876;

    public List<Cuenta> consultarMercantil(String ci, String nombres, String apellidos) {
        List<Cuenta> cuentas = new ArrayList<>();
        
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(MERCANTIL_HOST);
            String message = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
            byte[] buffer = message.getBytes();
            
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, MERCANTIL_PORT);
            socket.send(packet);
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            if (!response.isEmpty()) {
                String[] cuentasStr = response.split(":");
                for (String cuentaStr : cuentasStr) {
                    String[] parts = cuentaStr.split("-");
                    if (parts.length == 2) {
                        String nroCuenta = parts[0];
                        double saldo = Double.parseDouble(parts[1]);
                        cuentas.add(new Cuenta(Banco.MERCANTIL, nroCuenta, ci, nombres, apellidos, saldo));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cuentas;
    }

    public List<Cuenta> consultarBCP(String ci, String nombres, String apellidos) {
        List<Cuenta> cuentas = new ArrayList<>();
        
        try (Socket socket = new Socket(BCP_HOST, BCP_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            String message = "Buscar:" + ci + "-" + nombres + "-" + apellidos;
            out.println(message);
            
            String response = in.readLine();
            
            if (response != null && !response.isEmpty()) {
                String[] cuentasStr = response.split(":");
                for (String cuentaStr : cuentasStr) {
                    String[] parts = cuentaStr.split("-");
                    if (parts.length == 2) {
                        String nroCuenta = parts[0];
                        double saldo = Double.parseDouble(parts[1]);
                        cuentas.add(new Cuenta(Banco.BCP, nroCuenta, ci, nombres, apellidos, saldo));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return cuentas;
    }

    public boolean retenerMonto(Cuenta cuenta, double monto) {
        try {
            if (cuenta.getBanco() == Banco.MERCANTIL) {
                return retenerMontoMercantil(cuenta.getNroCuenta(), monto);
            } else if (cuenta.getBanco() == Banco.BCP) {
                return retenerMontoBCP(cuenta.getNroCuenta(), monto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean retenerMontoMercantil(String nroCuenta, double monto) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(MERCANTIL_HOST);
            String message = "Congelar:" + nroCuenta + "-" + monto;
            byte[] buffer = message.getBytes();
            
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, MERCANTIL_PORT);
            socket.send(packet);
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            return response.startsWith("SI-");
        }
    }

    private boolean retenerMontoBCP(String nroCuenta, double monto) throws IOException {
        try (Socket socket = new Socket(BCP_HOST, BCP_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            String message = "Congelar:" + nroCuenta + "-" + monto;
            out.println(message);
            
            String response = in.readLine();
            return response != null && response.startsWith("SI-");
        }
    }
}