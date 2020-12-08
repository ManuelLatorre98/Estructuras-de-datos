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
public class CeldaHash {
    private Object elem;
    private int estado;
    
    public CeldaHash(){
        this.elem = null;
        this.estado = 0;
    }
    public int getEstado(){
        return this.estado;
    }
    public Object getElem(){
        return this.elem;
    }
    public void setElem(Object nuevoElem){
        this.elem = nuevoElem;
    }
    public void setEstado(int est){
        this.estado = est;
    }
}
