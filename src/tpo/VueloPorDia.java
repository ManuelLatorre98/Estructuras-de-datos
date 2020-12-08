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
public class VueloPorDia {
    private String fecha;
    private int cantAsientos;
    private int cantAsientosVendidos;

    public VueloPorDia(String fecha, int cantAsientos, int cantAsientosVendidos) {
        this.fecha = fecha;
        this.cantAsientos = cantAsientos;
        this.cantAsientosVendidos = cantAsientosVendidos;
    }
    
    public VueloPorDia(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public void setCantAsientos(int cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    public int getCantAsientosVendidos() {
        return cantAsientosVendidos;
    }

    public void setCantAsientosVendidos(int cantAsientosVendidos) {
        this.cantAsientosVendidos = cantAsientosVendidos;
    }
    @Override
    public String toString(){
        return this.fecha;
    }
}
