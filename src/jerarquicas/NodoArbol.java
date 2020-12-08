/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author Martín
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol (Object nuevoElem){
        elem = nuevoElem;
        izquierdo = null;
        derecho = null;
    }
    public NodoArbol(Object nuevoElem, NodoArbol izq, NodoArbol der){
        elem = nuevoElem;
        izquierdo = izq;
        derecho = der;
    }
    public Object getElem(){
        return elem;
    }
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    public NodoArbol getDerecho(){
        return derecho;
    }
    public void setElem(Object elemNuevo){
        elem = elemNuevo;
    }
    public void setIzquierdo(NodoArbol izq){
        izquierdo = izq;
    }
    public void setDerecho (NodoArbol der){
        derecho = der;
    }
}
