/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo;

/**
 *
 * @author Martín
 */
public class Pasaje {
    private Vuelo vuelo;
    private String fecha;
    private String asiento;
    private String estado;

    public Pasaje(Vuelo vuelo, String fecha, String asiento, String estado) {
        this.vuelo = vuelo;
        this.fecha = fecha;
        this.asiento = asiento;
        this.estado = estado;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString(){
        return vuelo.getClave()+" "+fecha;
    }
}
