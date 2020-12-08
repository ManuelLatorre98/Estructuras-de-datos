/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;
//PARA TESTEAR CON DINAMICAS
import lineales.dinamicas.*;
//PARA TESTEAR CON ESTATICAS
//import lineales.dinamicas.Lista;
//import lineales.estaticas.*;
/**
 *
 * @author Martín
 */
public class MixLineales {
    public static void main (String [] args){
        testGenerarLista();
    }
    public static void testGenerarLista(){
        Lista lis = new Lista();
        System.out.println("EN LISTA VACIA");
        System.out.println("Lista original: "+lis.toString());
        System.out.println("Lista generada: "+generarLista(lis).toString());
        lis.insertar('A', lis.longitud()+1);
        lis.insertar('B', lis.longitud()+1);
        lis.insertar('*', lis.longitud()+1);
        lis.insertar('C', lis.longitud()+1);
        lis.insertar('*', lis.longitud()+1);
        lis.insertar('D', lis.longitud()+1);
        lis.insertar('E', lis.longitud()+1);
        lis.insertar('F', lis.longitud()+1);
        System.out.println("EN LISTA CON ELEMTOS");
        System.out.println("Lista original: "+lis.toString());
        System.out.println("Lista generada: "+generarLista(lis).toString());
    }
    public static Lista generarLista(Lista lis){
        Lista salida = new Lista();
        //si lis no está vacía..
        if (lis.longitud() > 0){
            Pila p = new Pila();
            Cola c = new Cola();
            while (!lis.esVacia()){
                int aux = lis.localizar('*'), i;
                //este if es para los últimos caracteres que no tienen un * al final
                if (aux==-1){
                    aux = lis.longitud()+1;
                }
                //inserta la cabecera al final de la lista nueva, la apila y la 
                //pone en la cola, después elimina la cabecera.
                //Todo esto hasta llegar al *
                for (i=1; i<aux; i++){
                    Object letra = lis.recuperar(1);
                    salida.insertar(letra, salida.longitud()+1);
                    p.apilar(letra);
                    c.poner(letra);
                    lis.eliminar(1);
                }
                //Mientras la pila no esté vacía, inserta el tope al final de la
                //lista nueva y desapila.
                while (!p.esVacia()){
                    Object letra = p.obtenerTope();
                    salida.insertar(letra, salida.longitud()+1);
                    p.desapilar();
                }
                //Mientras la cola no esté vacía, inserta el frente al final de 
                //la lista nueva y saca el mismo.
                while (!c.esVacia()){
                    Object letra = c.obtenerFrente();
                    salida.insertar(letra, salida.longitud()+1);
                    c.sacar();
                }
                //si lis no está vacía entonces pone un * y lo elimina de lis
                if (!lis.esVacia()){
                    salida.insertar('*', salida.longitud()+1);
                    lis.eliminar(1);
                }
            }
        }
        return salida;
    }
}
