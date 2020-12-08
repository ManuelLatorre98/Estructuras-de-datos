/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

/**
 *
 * @author Martín
 */
public class NodoVertice {
    private Object elem;
    private NodoVertice sigVertice;
    private NodoAdy primerAdy;
    
    public NodoVertice(Object elemNew, NodoVertice sigVerticeNew, NodoAdy primerAdyNew){
        elem = elemNew;
        sigVertice = sigVerticeNew;
        primerAdy = primerAdyNew;
    }
    public Object getElem(){
        return elem;
    }
    public NodoVertice getSigVertice(){
        return sigVertice;
    }
    public NodoAdy getPrimerAdy(){
        return primerAdy;
    }
    public void setElem(Object elemNew){
        elem = elemNew;
    }
    public void setSigVertice(NodoVertice sigVerticeNew){
        sigVertice = sigVerticeNew;
    }
    public void setPrimerAdy(NodoAdy primerAdyNew){
        primerAdy = primerAdyNew;
    }
}
