/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;
import conjuntistas.ArbolHeap;
/**
 *
 * @author Martín
 */
public class TestArbolHeap {
    public static void main(String [] args){
        ArbolHeap a = new ArbolHeap();
        
        System.out.println("es vacio? "+a.esVacio());
        System.out.println("elimina cima: "+a.eliminarCima());
        System.out.println("cima: "+a.recuperarCima());
        
        System.out.println("inserta 1: "+a.insertar(1));
        System.out.println("inserta 2: "+a.insertar(2));
        System.out.println("inserta 3: "+a.insertar(3));
        System.out.println("inserta 17: "+a.insertar(17));
        System.out.println("inserta 19: "+a.insertar(19));
        System.out.println("inserta 36: "+a.insertar(36));
        System.out.println("inserta 7: "+a.insertar(7));
        System.out.println("inserta 25: "+a.insertar(25));
        System.out.println("inserta 100: "+a.insertar(100));
        System.out.println(a.toString());
        System.out.println("es vacio? "+a.esVacio());
        
        System.out.println("inserta 4: "+a.insertar(4));
        System.out.println(a.toString());
        
        System.out.println("cima: "+a.recuperarCima());
        System.out.println(a.toString());
        System.out.println("elimina cima: "+a.eliminarCima());
        System.out.println("cima: "+a.recuperarCima());
        System.out.println(a.toString());
    }
}
