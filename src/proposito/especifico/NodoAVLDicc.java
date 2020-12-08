/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proposito.especifico;

/**
 *
 * @author Martín
 */
public class NodoAVLDicc {
    private Comparable clave;
    private Comparable dato;
    private int altura;
    private NodoAVLDicc hijoIzq;
    private NodoAVLDicc hijoDer;
    
    public NodoAVLDicc(Comparable newClave, Comparable newDato){
        clave = newClave;
        dato = newDato;
        hijoIzq = null;
        hijoDer = null;
        altura = 0;
    }
    public NodoAVLDicc(Comparable newClave, Comparable newDato, NodoAVLDicc newIzq, NodoAVLDicc newDer){
        clave = newClave;
        dato = newDato;
        hijoIzq = newIzq;
        hijoDer = newDer;
        recalcularAltura();
    }
    public Comparable getClave(){
        return clave;
    }
    public Comparable getDato(){
        return dato;
    }
    public int getAltura(){
        recalcularAltura();
        return altura;
    }
    public NodoAVLDicc getIzquierdo(){
        return hijoIzq;
    }
    public NodoAVLDicc getDerecho(){
        return hijoDer;
    }
    public void setElem(Comparable newClave){
        clave = newClave;
    }
    public void setDato(Comparable newDato){
        dato = newDato;
    }
    public void setIzquierdo(NodoAVLDicc izq){
        hijoIzq = izq;
    }
    public void setDerecho (NodoAVLDicc der){
        hijoDer = der;
    }
    public void recalcularAltura(){
        int altI = -1, altD = -1;
        if(getIzquierdo() != null)
            altI = getIzquierdo().getAltura();
        if (getDerecho() != null)
            altD = getDerecho().getAltura();
        if (altI>altD)
            altura = altI+1;
        else
            altura = altD+1;
    }
}
