/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;
import lineales.dinamicas.Lista;
/**
 *
 * @author Martín
 */
public class HashAbierto {
    private static final int TAMANIO=10;
    private Nodo[] tabla;
    private int cant;
    
    public HashAbierto(){
        //Crea tabla sin elementos
        this.tabla = new Nodo[TAMANIO];
        this.cant = 0;
    }
    public boolean pertenece(Object elem){
        //devuelve true si elem está en la tabla
        boolean salida = false;
        //si hay elementos en la tabla, entra
        if (cant > 0){
            //calcula la posicion de elem
            int pos = elem.hashCode()%this.TAMANIO;
            Nodo aux = this.tabla[pos];
            //busca elem hasta encontrarlo o que dé null
            while(aux != null && !salida){
                salida = aux.getElem().equals(elem);
                aux = aux.getEnlace();
            }
        }
        return salida;
    }
    public boolean insertar(Object elem){
        //true si inserta, false si elem ya se encuentra en la tabla
        //calcula la posicion de elem
        int pos = elem.hashCode()%this.TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        //busca elem hasta encontrarlo o que dé null
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        //si no lo encuentra, inserta y aumenta en 1 la cantidad de nodos en la tabla
        if(!encontrado){
            Nodo temp = this.tabla[pos];
            this.tabla[pos] = new Nodo(elem, temp);
            this.cant++;
            encontrado = true;
        }
        return encontrado;
    }
    public boolean eliminar(Object elem){
        //true si elimina, false si elem no se encuentra en la tabla
        //calcula la posicion de elem
        int pos = elem.hashCode()%this.TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        //ejecuta esto si elem está en la cabecera de pos
        if(aux.getElem().equals(elem)){
            this.tabla[pos] = aux.getEnlace();
            this.cant--;
            encontrado = true;
        }
        //si elem no está en cabecera de pos, ejecuta esto
        else {
            //busca elem  en el enlace de aux hasta encontrarloo que dé null
            while(!encontrado && aux.getEnlace() != null){
                encontrado = aux.getEnlace().getElem().equals(elem);
                if(!encontrado)
                    aux = aux.getEnlace();
            }
            //si lo encuentra, lo elimina y reduce en 1 la cantidad de nodos en la tabla
            if(encontrado){
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.cant--;
            }
        }
        return encontrado;
    }
    public boolean esVacia(){
        return (this.cant == 0);
    }
    public Lista listar(){
        //Devuelve lista con los elementos de la tabla
        Lista l = new Lista();
        //entra si la tabla tiene elementos
        if(this.cant>0){
            Nodo aux = this.tabla[0];
            int cantAux= this.cant, i=0;
            //mientras haya elementos que listar, va a seguir iterando
            while(cantAux > 0){
                //mientras en la misma posicion hayan elementos, va a seguir iterando
                while(aux != null){
                    l.insertar(aux.getElem(), l.longitud()+1);
                    aux = aux.getEnlace();
                    cantAux--;
                }
                i++;
                aux = this.tabla[i];
            }
        }
        return l;
    }
    public String toString(){
        //Devuelve una cadena con todos los elementos de la tabla y sus posiciones
        String salida ="";
        int i;
        Nodo nodo;
        //Se utiliza a i para no pasarse del tamanio del arreglo
        for (i=0; i<this.TAMANIO; i++){
            //Si la celda no está vacía, entonces entra para listar sus elementos
            //Si está vacía entonces pone la posición pero ningún elemento
            if (this.tabla[i] != null){
                nodo = this.tabla[i];
                salida += "posición "+i+": ";
                do {
                    salida += nodo.getElem()+", ";
                    nodo = nodo.getEnlace();
                } while (nodo != null);
                salida += "\n";
            } else{
                salida += "posición "+i+": \n";
            }
        }
        return salida;
    }
}
