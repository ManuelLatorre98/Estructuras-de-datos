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
public class ClaveVuelo implements Comparable{
    private String clave;

    public ClaveVuelo(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.clave);
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
        final ClaveVuelo other = (ClaveVuelo) obj;
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Object otraClave){
        ClaveVuelo otraCl = (ClaveVuelo) otraClave;
        return this.clave.compareTo(otraCl.clave);
    }
    @Override
    public String toString(){
        return this.clave;
    }
}
