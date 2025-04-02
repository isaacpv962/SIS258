package juez;

import common.Cuenta;
import asfi.ASFIInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class JuezClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ASFIInterface asfi = (ASFIInterface) registry.lookup("ASFI");
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Sistema de Retenciones Judiciales");
            System.out.println("----------------------------------");
            
            System.out.print("Ingrese CI: ");
            String ci = scanner.nextLine();
            
            System.out.print("Ingrese Nombres: ");
            String nombres = scanner.nextLine();
            
            System.out.print("Ingrese Apellidos: ");
            String apellidos = scanner.nextLine();
            
            // Consultar cuentas
            List<Cuenta> cuentas = asfi.consultarCuentas(ci, nombres, apellidos);
            
            if (cuentas.isEmpty()) {
                System.out.println("No se encontraron cuentas para el cliente.");
                return;
            }
            
            System.out.println("\nCuentas encontradas:");
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + ". " + cuentas.get(i));
            }
            
            System.out.print("\nSeleccione una cuenta (1-" + cuentas.size() + "): ");
            int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (seleccion < 0 || seleccion >= cuentas.size()) {
                System.out.println("Selección inválida.");
                return;
            }
            
            Cuenta cuentaSeleccionada = cuentas.get(seleccion);
            
            System.out.print("Ingrese monto a retener: ");
            double monto = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Ingrese glosa: ");
            String glosa = scanner.nextLine();
            
            // Intentar retener monto
            boolean resultado = asfi.retenerMonto(cuentaSeleccionada, monto, glosa);
            
            if (resultado) {
                System.out.println("Transacción con éxito");
            } else {
                System.out.println("No se pudo realizar la retención");
            }
            
        } catch (Exception e) {
            System.err.println("Error en el cliente Juez: " + e.toString());
            e.printStackTrace();
        }
    }
}