/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;
import conjuntistas.ArbolAVL;
/**
 *
 * @author Martín
 */
public class TestArbolAVL {
    public static void main (String [] args){
        ArbolAVL a = new ArbolAVL();
        
        System.out.println("es vacio: "+a.vacio());
        
        System.out.println("insertar 8: "+a.insertar(16));
        System.out.println("insertar 4: "+a.insertar(10));
        System.out.println("insertar 10: "+a.insertar(24));
        System.out.println("insertar 2: "+a.insertar(7));
        System.out.println("insertar 6: "+a.insertar(18));
        System.out.println("insertar 7: "+a.insertar(26));
        System.out.println("insertar 7: "+a.insertar(22));
        
        System.out.println("es vacio: "+a.vacio());
        System.out.println("listar: "+a.listar());
        System.out.println("listar desde 9 a 27: "+a.listarPorRango(9,27));
        System.out.println("minimo: "+a.minimoElem());
        System.out.println("maximo: "+a.maximoElem());
        
        System.out.println("eliminar : "+a.eliminar(26));
        System.out.println(a.listar());
    }
}
