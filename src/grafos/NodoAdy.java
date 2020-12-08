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
public class NodoAdy {
    private NodoVertice vertice;
    private NodoAdy sigAdy;
    private int etiqueta;
    
    public NodoAdy(NodoVertice vert, NodoAdy ady, int etiq){
        this.vertice = vert;
        this.sigAdy = ady;
        this.etiqueta = etiq;
    }
    public NodoVertice getVertice(){
        return vertice;
    }
    public NodoAdy getSigAdy(){
        return sigAdy;
    }
    public int getEtiqueta(){
        return etiqueta;
    }
    public void setVertice(NodoVertice vert){
        vertice = vert;
    }
    public void setSigAdy(NodoAdy ady){
        sigAdy = ady;
    }
    public void setEtiqueta(int etiq){
        etiqueta = etiq;
    }
}
