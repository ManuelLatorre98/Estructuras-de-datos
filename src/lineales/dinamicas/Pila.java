/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author Martín
 */
public class Pila {
    private Nodo tope;
    
    public Pila(){
        this.tope = null;
    }
    public boolean apilar(Object nuevoElem){
        //apila nuevoElem y retorna true
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        this.tope = nuevo;
        return true;
    }
    public boolean desapilar(){
        //desapila si la pila no está vacía y retorna true, sino retorna false
        boolean salida = false;
        if (this.tope != null){
            this.tope = this.tope.getEnlace();
            salida = true;
        }
        return salida;
    }
    public Object obtenerTope(){
        //retorna el elem que se encuentra en frente si la pila no está vacía
        Object salida = null;
        if (this.tope != null)
            salida = this.tope.getElem();
        return salida;
    }
    public boolean esVacia(){
        //true si está vacía
        boolean salida = false;
        if (this.tope == null)
            salida = true;
        return salida;
    }
    public void vaciar(){
        //vacía la pila
        while(this.tope != null)
            this.tope = this.tope.getEnlace();
    }
    public Pila clone(){
        //retorna copia exacta de la pila original
        Pila clon = new Pila();
        if (this.tope != null){
            //aux recorre la pila original
            Nodo aux = this.tope;
            clon.tope = new Nodo(aux.getElem(), null);
            //nodoClon va a crear la pila clon junto con auxClon
            Nodo nodoClon = clon.tope;
            Nodo auxClon;
            while (aux.getEnlace() != null){
                aux = aux.getEnlace();
                auxClon = new Nodo(aux.getElem(), null);
                nodoClon.setEnlace(auxClon);
                nodoClon = nodoClon.getEnlace();
            }
        }
        return clon;
    }
    public String toString(){
        //retorna una cadena con los elementos de la pila
        String salida="";
        if (this.tope != null){
            Nodo aux = this.tope;
            while (aux != null){
                salida = aux.getElem().toString()+" "+salida;
                aux = aux.getEnlace();
            }
        }
        return salida;
    }
}
