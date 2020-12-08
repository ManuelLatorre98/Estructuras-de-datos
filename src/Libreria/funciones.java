/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria;

/**
 *
 * @author Martín
 */
public class funciones {
    public int hashCode(int clave){
        //retorna posicion para clave
        int digito, salida = 0;
        //suma los digitos de clave
        while(clave > 0){
            digito = clave;
            salida += digito;
            clave = clave/10;
        }
        return salida;
    }
    public int hashCode(String cadena){
        int suma = 0, i;
        //suma el valor ascii de cada char de cadena
        for (i=0; i<=cadena.length(); i++){
            int ascii = cadena.charAt(i);
            suma += ascii;
        }
        return suma;
    }
    public int rehash(int clave){
        do{
            clave = clave/10;
        } while (clave>=10);
        return clave;
        
    }
    public int rehash(String cadena){
        return cadena.charAt(0);
    }
}
