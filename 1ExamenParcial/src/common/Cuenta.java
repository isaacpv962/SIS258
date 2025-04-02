package common;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private Banco banco;
    private String nroCuenta;
    private String ci;
    private String nombres;
    private String apellidos;
    private double saldo;

    public Cuenta(Banco banco, String nroCuenta, String ci, String nombres, String apellidos, double saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    // Getters y setters
    public Banco getBanco() { return banco; }
    public String getNroCuenta() { return nroCuenta; }
    public String getCi() { return ci; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public double getSaldo() { return saldo; }

    @Override
    public String toString() {
        return "Banco: " + banco + 
               ", Cuenta: " + nroCuenta + 
               ", CI: " + ci + 
               ", Titular: " + nombres + " " + apellidos + 
               ", Saldo: " + saldo;
    }
}