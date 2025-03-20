/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
class Diploma implements Serializable{
    String nombrecompleto;
    Carrera carrera;
    String fecha;
    String mensaje;

    public Diploma(String nombrecompleto, Carrera carrera, String fecha, String mensaje) {
        this.nombrecompleto = nombrecompleto;
        this.carrera = carrera;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Diploma{" + "nombrecompleto=" + nombrecompleto + ", carrera=" + carrera + ", fecha=" + fecha + ", mensaje=" + mensaje + '}';
    }
    

    
}
