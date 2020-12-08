/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.Pila;
/**
 *
 * @author Martín
 */
public class TestPila {
    public static void main(String [] args){
        Pila pila = new Pila();
        
        //apilar
        System.out.println(pila.apilar(20));
        System.out.println(pila.apilar(19));
        System.out.println(pila.apilar(18));
        System.out.println(pila.apilar(17));
        System.out.println(pila.apilar(16));
        System.out.println(pila.apilar(15));
        System.out.println(pila.apilar(14));
        System.out.println(pila.apilar(13));
        System.out.println(pila.apilar(12));
        System.out.println(pila.apilar(11));
        System.out.println(pila.apilar(10));
        System.out.println(pila.apilar(9));
        System.out.println(pila.apilar(8));
        System.out.println(pila.apilar(7));
        System.out.println(pila.apilar(6));
        System.out.println(pila.apilar(5));
        System.out.println(pila.apilar(4));
        System.out.println(pila.apilar(3));
        System.out.println(pila.apilar(2));
        System.out.println(pila.apilar(1));
        System.out.println(pila.apilar(0));
        //obtenertope
        System.out.println(pila.obtenerTope());
        //desapilar
        System.out.println(pila.desapilar());
        //obtenertope
        System.out.println(pila.obtenerTope());
        //es vacia?
        System.out.println(pila.esVacia());
        //clone
        Pila clon = pila.clone();
        System.out.println(clon);
        //desapilar
        System.out.println(clon.desapilar());
        System.out.println(clon.toString());
        //toString
        System.out.println(pila.toString());
        //vaciar
        pila.vaciar();
        //es vacia?
        System.out.println(pila.esVacia());
        //desapilar
        System.out.println(pila.desapilar());
    }
}
