/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author Martín
 */
public class NodoBB {
    private Comparable elem;
    private NodoBB izquierdo;
    private NodoBB derecho;
    
    public NodoBB (Comparable nuevoElem){
        elem = nuevoElem;
        izquierdo = null;
        derecho = null;
    }
    public NodoBB(Comparable nuevoElem, NodoBB izq, NodoBB der){
        elem = nuevoElem;
        izquierdo = izq;
        derecho = der;
    }
    public Comparable getElem(){
        return elem;
    }
    public NodoBB getIzquierdo(){
        return izquierdo;
    }
    public NodoBB getDerecho(){
        return derecho;
    }
    public void setElem(Comparable elemNuevo){
        elem = elemNuevo;
    }
    public void setIzquierdo(NodoBB izq){
        izquierdo = izq;
    }
    public void setDerecho (NodoBB der){
        derecho = der;
    }
}
