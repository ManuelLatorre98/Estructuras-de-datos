/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpo;

import java.util.Objects;

/**
 *
 * @author Martín
 */
public class Aeropuerto {
    private String clave;
    private String ciudad;
    private String tel;

    public Aeropuerto(String clave, String ciudad, String tel) {
        this.clave = clave;
        this.ciudad = ciudad;
        this.tel = tel;
    }
    
    public Aeropuerto(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeropuerto other = (Aeropuerto) obj;
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString(){
        return this.clave;
    }
}
