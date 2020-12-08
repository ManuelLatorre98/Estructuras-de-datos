/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;
import conjuntistas.HashAbierto;
/**
 *
 * @author Martín
 */
public class TestTablaHashAbierto {
    public static void main(String [] args){
        
        HashAbierto h = new HashAbierto();
        System.out.println("es vacia? "+h.esVacia());
        System.out.println("Lista de elementos: "+h.listar());
        
        System.out.println("\n");
                
        System.out.println("insertar 10: "+h.insertar(10));
        System.out.println("insertar 4: "+h.insertar(4));
        System.out.println("insertar 14: "+h.insertar(14));
        System.out.println("insertar 7: "+h.insertar(7));
        System.out.println("insertar 17: "+h.insertar(17));
        System.out.println("insertar 27: "+h.insertar(27));
        
        System.out.println("\n");
        
        System.out.println("es vacia? "+h.esVacia());
        System.out.println("Lista de elementos: "+h.listar());
        System.out.println("\n");
        
        System.out.println("toString: \n"+h.toString());
        
        System.out.println("pertenece 30? "+h.pertenece(30)); 
        System.out.println("pertenece 4? "+h.pertenece(4));
        System.out.println("eliminar 4: "+h.eliminar(4));
        System.out.println("pertenece 4? "+h.pertenece(4));
        
        System.out.println("pertenece 27? "+h.pertenece(27));
        System.out.println("eliminar 27: "+h.eliminar(27));
        System.out.println("pertenece 27? "+h.pertenece(27));
        
        System.out.println("\n");
        
        System.out.println("es vacia? "+h.esVacia());
        System.out.println("Lista de elementos: "+h.listar());
        
        System.out.println("\n");
        
        System.out.println("toString: \n"+h.toString());
        h.toString();
    }
}
