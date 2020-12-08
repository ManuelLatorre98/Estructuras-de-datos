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
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    public NodoGen(Object nuevoElem, NodoGen hijoIzq, NodoGen hermanoDer){
        elem = nuevoElem;
        hijoIzquierdo = hijoIzq;
        hermanoDerecho = hermanoDer;
    }
    public NodoGen(Object nuevoElem){
        elem = nuevoElem;
        hijoIzquierdo = null;
        hermanoDerecho = null;
    }
    public Object getElem(){
        return this.elem;
    }
    public NodoGen getHEI(){
        return this.hijoIzquierdo;
    }
    public NodoGen getHD(){
        return this.hermanoDerecho;
    }
    public void setElem(Object nuevoElem){
        this.elem = nuevoElem;
    }
    public void setHEI(NodoGen hijoIzq){
        this.hijoIzquierdo = hijoIzq;
    }
    public void setHD(NodoGen hmnoDer){
        this.hermanoDerecho = hmnoDer;
    }
}
