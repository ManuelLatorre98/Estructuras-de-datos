/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
import lineales.dinamicas.Lista;
/**
 *
 * @author Martín
 */
public class TestLista {
    public static void main(String [] args){
        Lista lista = new Lista();
        System.out.println("longitud: "+lista.longitud());
        System.out.println("toString: "+lista.toString());
        System.out.println("insertar 4 en 1 "+lista.insertar(4, 1));
        System.out.println("insertar 3 en 2 "+lista.insertar(3, 2));
        System.out.println("insertar 6 en 3 "+lista.insertar(6, 3));
        System.out.println("insertar 9 en 4 "+lista.insertar(9, 4));
        System.out.println("longitud: "+lista.longitud());
        System.out.println("toString: "+lista.toString());
        System.out.println("insertar 1 en 2 "+lista.insertar(1, 2));
        System.out.println("longitud: "+lista.longitud());
        System.out.println("toString: "+lista.toString());
        System.out.println("eliminar posición 1 "+lista.eliminar(1));
        System.out.println("longitud: "+lista.longitud());
        System.out.println("toString: "+lista.toString());
        System.out.println("eliminar posición 3 "+lista.eliminar(3));
        System.out.println("longitud: "+lista.longitud());
        System.out.println("toString: "+lista.toString());
        System.out.println("es vacía? "+lista.esVacia());
        System.out.println("recuperar pos 2 "+lista.recuperar(2));
        System.out.println("recuperar pos 7 "+lista.recuperar(7));
        System.out.println("recuperar pos 0 "+lista.recuperar(0));
        System.out.println("localizar num 6 "+lista.localizar(6));
        System.out.println("localizar num 9 "+lista.localizar(9));
        System.out.println("clonar: "+lista.clone().toString());
        System.out.println("vaciar: "); lista.vaciar();
        System.out.println("longitud: "+lista.longitud());
    }
}
