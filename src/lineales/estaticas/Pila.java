/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author mvergara
 */
public class Pila {
    private static final int TAMANIO=10;
    private Object[] array;
    private int tope;
    
    public Pila(){
        this.array = new Object[TAMANIO];
        this.tope = -1;
    }
    public boolean apilar(Object elem){
        //Devuelve true si se pudo apilar el nuevo elemento, false si la pila está llena
        boolean exito=false;
        if (tope<TAMANIO-1){
            tope++;
            array[tope] = elem;
            exito = true;
        }
        return exito;
    }
    public boolean desapilar(){
        //Devuelve true si se pudo desapilar, false si la pila está vacía
        boolean exito=false;
        if (tope!=-1){
            array[tope] = null;
            tope--;
            exito = true;
        }
        return exito;
    }
    public Object obtenerTope(){
        //Devuelve el elemento del tope si la pila no está vacía
        Object elemTope=null;
        if (tope!=-1){
            elemTope=array[tope];
        }
        return elemTope;
    }
    public boolean esVacia(){
        //Devuelve true si la pila está vacía
        boolean vacia=false;
        if (tope==-1){
            vacia=true;
        }
        return vacia;
    }
    public void vaciar(){
        //Vacía la pila
        while (tope!=-1){
            array[tope]=null;
            tope--;
        }
    }
    //public Pila clone(){
        //Devuelve una copia exacta de esta pila
    //    int i;
    //    Pila clon = new Pila();
    //    for(i=0;i<=tope;i++){
    //        clon.tope++;
    //        clon.array[tope]=array[i];
    //    }
    //    return clon;
    //}
    public Pila clone(){
        //Devuelve una copia exacta de esta pila
        Pila clon = new Pila();
        clon.tope = this.tope;
        clon.array = this.array.clone();
        return clon;
    }
    public String toString(){
        //Devuelve una cadena con los elementos del array
        String salida="";
        int i=this.tope;
        while(i>=0){
            salida = array[i]+" "+salida;
            i--;
        }
        return salida;
    }
}
