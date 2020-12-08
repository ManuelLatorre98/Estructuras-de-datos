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
public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    
    public NodoAVL(Comparable nuevoElem){
        elem = nuevoElem;
        izquierdo = null;
        derecho = null;
        altura = 0;
    }
    public NodoAVL(Comparable nuevoElem, NodoAVL izq, NodoAVL der){
        elem = nuevoElem;
        izquierdo = izq;
        derecho = der;
        recalcularAltura();
    }
    public Comparable getElem(){
        return elem;
    }
    public NodoAVL getIzquierdo(){
        return izquierdo;
    }
    public NodoAVL getDerecho(){
        return derecho;
    }
    public void setElem(Comparable elemNuevo){
        elem = elemNuevo;
    }
    public void setIzquierdo(NodoAVL izq){
        izquierdo = izq;
    }
    public void setDerecho (NodoAVL der){
        derecho = der;
    }
    public int getAltura(){
        return altura;
    }
    public void recalcularAltura(){
        if(getIzquierdo() != null)
            altura = getIzquierdo().getAltura() +1;
        else if (getDerecho() != null)
            altura = getDerecho().getAltura() +1;
    }
}
