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
public class Cola {
    private Nodo frente;
    private Nodo fin;
    
    public Cola(){
        this.frente = null;
        this.fin = null;
    }
    public boolean poner(Object elem){
        //pone a elem en el fin y retorna true
        Nodo nuevoNodo = new Nodo(elem, null);
        //si está vacía, pone a elem en el frente tambíen
        if (this.frente==null){
            this.frente = nuevoNodo;
        } else {
            this.fin.setEnlace(nuevoNodo);
        }
        this.fin = nuevoNodo;
        return true;
    }
    public boolean sacar(){
        //saca al elemento del frente, si la pila no está vacía, y retorna true
        boolean salida = false;
        if (this.frente!=null){
            //si la cola tiene un solo elemento cargado, la deja vacía
            if (this.frente.getEnlace()==null){
                this.frente = null;
                this.fin = null;
            } 
            //si la cola tiene más de un elemento cargado, solo mueve el frente
            else {
                this.frente = this.frente.getEnlace();
            }
            salida = true;
        }
        return salida;
    }
    public Object obtenerFrente(){
        //retorna el elemento del frente, si la cola no está vacía
        Object salida = null;
        if (this.frente != null)
            salida = this.frente.getElem();
        return salida;
    }
    public boolean esVacia(){
        //true si la cola está vacía
        boolean salida = true;
        if (this.frente != null)
            salida = false;
        return salida;
    }
    public void vaciar(){
        //vacía la cola
        this.frente = null;
        this.fin = null;
    }
    public Cola clone(){
        //retorna copia exacta de la cola original
        Cola clon = new Cola();
        if (this.frente != null){
            //aux1 va a recorrer la cola original
            Nodo aux1 = this.frente;
            clon.frente = new Nodo(aux1.getElem(), null);
            aux1 = aux1.getEnlace();
            //aux2 va a recorrer la cola clon creando nuevos nodos y enlazandolos
            Nodo aux2 = clon.frente;
            while (aux1 != null){
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
            clon.fin = aux2;
        }
        return clon;
    }
    public String toString(){
        //retorna una cadena con los elementos de la cola
        String salida = "";
        if (this.frente != null){
            Nodo aux = this.frente;
            while (aux != null){
                salida += aux.getElem() + " ";
                aux = aux.getEnlace();
            }
        }
        return salida;
    }
}
