/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;
import jerarquicas.ArbolBin;
/**
 *
 * @author Martín
 */
public class TestArbolBin {
    public static void main(String [] args){
        ArbolBin a = new ArbolBin();
        
        System.out.println(a.insertar(1, 0, 'I'));
        System.out.println(a.insertar(2, 1, 'I'));
        System.out.println(a.insertar(3, 1, 'D'));
        System.out.println(a.insertar(4, 2, 'I'));
        System.out.println(a.insertar(5, 2, 'D'));
        System.out.println(a.insertar(6, 3, 'D'));
        System.out.println(a.insertar(7, 5, 'I'));
        System.out.println(a.insertar(7, 5, 'D'));
        
        System.out.println("es vacio: "+a.esVacio());
       
        System.out.println("padre de raíz(1): "+a.padre(1)); 
        System.out.println("padre de 6: "+a.padre(6));
        System.out.println("padre de 9: "+a.padre(9));
        
        System.out.println("altura: "+a.altura());
        
        System.out.println("nivel de 5: "+a.nivel(5));
        
        System.out.println("arbol original: "+a.toString());
        System.out.println("arbol clon: "+a.clone().toString());
        
        System.out.println("arbol preorden: "+a.listarPreorden());
        System.out.println("arbol inorden: "+a.listarInorden());
        System.out.println("arbol posorden: "+a.listarPosorden());
        System.out.println("arbol por niveles: "+a.listarPorNiveles().toString());
        
        a.vaciar();
        System.out.println("es vacio: "+a.esVacio());
    }
}
