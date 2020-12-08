/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;
import conjuntistas.ArbolBB;
/**
 *
 * @author Martín
 */
public class TestArbolBB {
    public static void main (String [] args){
        ArbolBB a = new ArbolBB();
        
        System.out.println("es vacio(debe dar 'true'): "+a.vacio());
        System.out.println("eliminar en arbol vacio(debe dar 'false'): "+a.eliminar(55));
        System.out.println("insertar 45(debe dar 'true'): "+a.insertar(45));
        System.out.println("insertar 34(debe dar 'true'): "+a.insertar(34));
        System.out.println("insertar 13(debe dar 'true'): "+a.insertar(13));
        System.out.println("insertar 65(debe dar 'true'): "+a.insertar(65));
        System.out.println("insertar 55(debe dar 'true'): "+a.insertar(55));
        System.out.println("insertar 73(debe dar 'true'): "+a.insertar(73));
        System.out.println("insertar 96(debe dar 'true'): "+a.insertar(96));
        System.out.println("insertar 13, elemento repetido(debe dar 'false'): "+a.insertar(13));
        
        System.out.println("es vacio(debe dar 'false'): "+a.vacio());
        System.out.println("listar(debe dar '13, 34, 45, 55, 65, 73, 96'): "+a.listar());
        System.out.println("listar desde 13 a 34(debe dar '13, 34'): "+a.listarPorRango(13,34));
        System.out.println("listar desde 34 a 73(debe dar '34, 45, 55, 65, 73'): "+a.listarPorRango(34,73));
        System.out.println("listar desde 55 a 96(debe dar '55, 65, 73, 96'): "+a.listarPorRango(55,96));
        System.out.println("minimo(debe dar '13'): "+a.minimoElem());
        System.out.println("maximo(debe dar '96'): "+a.maximoElem());
        
        System.out.println("pertenece 55?(debe dar 'true'): "+a.pertenece(55));
        System.out.println("eliminar hoja (55)(debe dar 'true'): "+a.eliminar(55));
        System.out.println("pertenece 55?(debe dar 'false'): "+a.pertenece(55));
        System.out.println("listar(debe dar '13, 34, 45, 65, 73, 96'): "+a.listar());
        System.out.println("insertar 55(debe dar 'true'): "+a.insertar(55));
        System.out.println("pertenece 73?(debe dar 'true'): "+a.pertenece(73));
        System.out.println("eliminar nodo con 1 hijo (73)(debe dar 'true'): "+a.eliminar(73));
        System.out.println("pertenece 73?(debe dar 'false'): "+a.pertenece(73));
        System.out.println("listar(debe dar '13, 34, 45, 55, 65, 96'): "+a.listar());
        System.out.println("insertar 73(debe dar 'true'): "+a.insertar(73));
        System.out.println("pertenece 65?(debe dar 'true'): "+a.pertenece(65));
        System.out.println("eliminar nodo con 2 hijos (65)(debe dar 'true'): "+a.eliminar(65));
        System.out.println("pertenece 65?(debe dar 'false'): "+a.pertenece(65));
        System.out.println("listar(debe dar '13, 34, 45, 55, 73, 96'): "+a.listar());
        System.out.println("insertar 65(debe dar 'true'): "+a.insertar(65));
        
        System.out.println("eliminar minimo(debe dar 'true'): "+a.eliminarMinimo());
        System.out.println("listar(debe dar '34, 45, 55, 65, 73, 96'): "+a.listar());
        System.out.println("eliminar maximo(debe dar 'true'): "+a.eliminarMaximo());
        System.out.println("listar(debe dar '34, 45, 55, 65, 73'): "+a.listar());
    }
}
