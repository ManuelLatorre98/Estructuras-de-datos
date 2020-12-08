/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author Martín
 */
public class Cola {
    private static final int TAMANIO=10;
    private Object[] array;
    private int frente;
    private int fin;
    
    public Cola(){
        //crea cola con un arreglo del tamaño indicado por el atributo TAMANIO
        //con frente y fin en posicion 0
        this.array = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    public boolean poner(Object elem){
        //inserta elem en fin si el arreglo no está lleno y retorna true
        boolean salida = false;
        if ((fin+1)%TAMANIO != frente){
            this.array[this.fin] = elem;
            this.fin = (this.fin+1)%this.TAMANIO;
            salida = true;
        }
        return salida;
    }
    public boolean sacar(){
        //si el arreglo no está vacío, saca el frente y retorna true
        boolean salida = false;
        if (this.fin != this.frente){
            this.array[this.frente] = null;
            this.frente = (this.frente+1)%TAMANIO;
            salida = true;
        }
        return salida;
    }
    public Object obtenerFrente(){
        //retorna el elemento de frente
        Object salida = null;
        if (this.frente != this.fin){
            salida = this.array[this.frente];
        }
        return salida;
    }
    public boolean esVacia(){
        //true si es vacía
        boolean salida = true;
        if (this.frente != this.fin)
            salida = false;
        return salida;
    }
    public void vaciar(){
        //vacía el arreglo
        while (this.frente != this.fin){
            this.array[this.frente] = null;
            this.frente = (this.frente+1)%TAMANIO;
        }
    }
    //public Cola clone(){
    //    Cola clon = new Cola();
        //Por si la cola original fue modificada, ponemos el fin y el frente del clon igual al de la cola
    //    clon.frente = this.frente;
    //    clon.fin = clon.frente;
    //    while (clon.fin != this.fin){
    //        clon.array[clon.fin] = this.array[clon.fin];
    //        clon.fin = (clon.fin+1)%TAMANIO;
    //    }
    //    return clon;
    //}
    public Cola clone(){
        //crea copia exacta de la cola
        Cola clon = new Cola();
        clon.frente = this.frente;
        clon.fin = this.fin;
        clon.array = this.array.clone();
        return clon;
    }
    public String toString(){
        //retorna una cadena con los elementos de la cola
        String salida = "";
        int aux = this.frente;
        while (aux != this.fin){
            salida += this.array[aux] + " ";
            aux = (aux+1)%TAMANIO;
        }
        return salida;
    }
}
