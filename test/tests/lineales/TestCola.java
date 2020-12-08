/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.Cola;
/**
 *
 * @author Martín
 */
public class TestCola {
    public static void main(String [] args){
        Cola cola = new Cola();
        
        //poner
        System.out.println(cola.poner(1));
        System.out.println(cola.poner(2));
        System.out.println(cola.poner(3));
        System.out.println(cola.poner(4));
        System.out.println(cola.poner(5));
        System.out.println(cola.poner(6));
        System.out.println(cola.poner(7));
        System.out.println(cola.poner(8));
        System.out.println(cola.poner(9));
        System.out.println(cola.poner(10));
        System.out.println(cola.toString());
        
        //sacar
        System.out.println("sacar: "+cola.sacar());
        System.out.println(cola.toString());
        
        System.out.println("frente: "+cola.obtenerFrente());
        System.out.println("clon: "+cola.clone().toString());
        System.out.println("es vacia: "+cola.esVacia());
        System.out.println("vaciar");
        cola.vaciar();
        System.out.println("es vacia: "+cola.esVacia());
        System.out.println("sacar con cola vacia: "+cola.sacar());
        System.out.println(Integer.toString(4).charAt(0) == '4');
    }
}
