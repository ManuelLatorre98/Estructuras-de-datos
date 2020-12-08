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
public class ClaveCliente implements Comparable{
    private String tipo;
    private String numero;

    public ClaveCliente(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.tipo);
        hash = 23 * hash + Objects.hashCode(this.numero);
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
        final ClaveCliente other = (ClaveCliente) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
    @Override
    public int compareTo(Object otroCliente){
        ClaveCliente otraClave = (ClaveCliente) otroCliente;
        int condicion = this.tipo.compareTo(otraClave.tipo);
        if (condicion == 0){
            condicion = this.numero.compareTo(otraClave.numero);
        }
        return condicion;
    }
    @Override
    public String toString(){
        return this.tipo+" "+this.numero;
    }
}
