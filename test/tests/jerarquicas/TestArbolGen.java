/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.ArbolGen;

/**
 *
 * @author Martín
 */
public class TestArbolGen {
    public static void main(String [] args){
        ArbolGen a = new ArbolGen();
        
        System.out.println(a.insertar('A', null));
        System.out.println(a.insertar('B', 'A'));
        System.out.println(a.insertar('C', 'A'));
        System.out.println(a.insertar('D', 'A'));
        System.out.println(a.insertar('E', 'B'));
        System.out.println(a.insertar('F', 'B'));
        System.out.println(a.insertar('J', 'F'));
        System.out.println(a.insertar('K', 'F'));
        System.out.println(a.insertar('L', 'F'));
        System.out.println(a.insertar('G', 'D'));
        System.out.println(a.insertar('H', 'D'));
        System.out.println(a.insertar('I', 'D'));
        System.out.println(a.insertar('M', 'G'));
        System.out.println(a.insertar('P', 'M'));
        System.out.println(a.insertar('Q', 'M'));
        System.out.println(a.insertar('N', 'I'));
        System.out.println(a.insertar('O', 'I'));
        
        System.out.println("es vacio: "+a.esVacio());
        
        System.out.println("H pertenece al árbol: "+a.pertenece('H'));
        System.out.println("M pertenece al árbol: "+a.pertenece('M'));
        
        System.out.println("ancestros de J: "+a.ancestros('J'));
        System.out.println("ancestros de M: "+a.ancestros('M'));
        
        System.out.println("padre de H: "+a.padre('H'));
        System.out.println("padre de M: "+a.padre('M'));
        
        System.out.println("altura: "+a.altura());
        
        System.out.println("nivel de C: "+a.nivel('C'));
        System.out.println("nivel de H: "+a.nivel('H'));
        System.out.println("nivel de I: "+a.nivel('I'));
        System.out.println("nivel de M: "+a.nivel('M'));
        
        System.out.println("arbol original: "+a.toString());
        System.out.println("arbol clon: "+a.clone().toString());
        
        System.out.println("arbol preorden: "+a.listarPreorden());
        System.out.println("arbol inorden: "+a.listarInorden());
        System.out.println("arbol posorden: "+a.listarPosorden());
        System.out.println("arbol por niveles: "+a.listaPorNiveles().toString());
        
        a.vaciar();
        System.out.println("es vacio: "+a.esVacio());
    }
}
