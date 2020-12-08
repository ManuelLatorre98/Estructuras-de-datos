/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;
import lineales.dinamicas.*;
/**
 *
 * @author Martín
 */
public class ArbolBin {
    private NodoArbol raiz;
    
    public ArbolBin(){
        raiz = null;
    }
    public boolean insertar(Object elemNuevo, Object elemPadre, char posicion){
        boolean salida = true;
        if (this.raiz == null){
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null){
                if (posicion == 'I' && nodoPadre.getIzquierdo()== null)
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                else {
                    if (posicion == 'D' && nodoPadre.getDerecho() == null)
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    else
                        salida = false;
                }
            } else {
                salida = false;
            }
        }
        return salida;
    }
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        NodoArbol salida = null;
        if (n != null){
            if (n.getElem().equals(buscado))
                salida = n;
            else {
                salida = obtenerNodo(n.getIzquierdo(), buscado);
                if (salida == null){
                    salida = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return salida;
    }
    public boolean esVacio(){
        boolean salida = false;
        if (this.raiz == null)
            salida = true;
        return salida;
    }
    public int altura(){
        int salida = alturaAux(this.raiz);
        return salida;
    }
    private int alturaAux(NodoArbol n){
        int salida = -1;
        if (n != null){
            if (n.getIzquierdo() == null && n.getDerecho() == null)
                salida = 0;
            else {
                int alturaIzq, alturaDer;
                alturaIzq = alturaAux(n.getIzquierdo());
                alturaDer = alturaAux(n.getDerecho());
                if (alturaIzq >= alturaDer)
                    salida = alturaIzq +1;
                else
                    salida = alturaDer +1;
            }
        }
        return salida;
    }
    public int nivel(Object elem){
        int salida = nivelAux(this.raiz, elem, 0);
        return salida;
    }
    private int nivelAux(NodoArbol n, Object elem, int contador){
        int salida = -1;
        if (n != null){
            if (n.getElem().equals(elem))
                salida = contador;
            else {
                salida = nivelAux(n.getIzquierdo(), elem, contador+1);
                if (salida < 0){
                    salida = nivelAux(n.getDerecho(), elem, contador+1);
                }
            }
        }
        return salida;
    }
    public Object padre(Object elem){
        Object salida = null;
        if (this.raiz != null && !this.raiz.getElem().equals(elem)){
            NodoArbol nodoSalida = padreAux(this.raiz, elem);
            if (nodoSalida != null)
                salida = nodoSalida.getElem();
        }
        return salida;
    }
    private NodoArbol padreAux(NodoArbol n, Object buscado){
        NodoArbol salida = null;
        if (n != null){
            if (n.getIzquierdo() != null && n.getIzquierdo().getElem().equals(buscado))
                salida = n;
            else {
                if (n.getDerecho() != null && n.getDerecho().getElem().equals(buscado))
                    salida = n;
                else {
                    salida = padreAux(n.getIzquierdo(), buscado);
                    if (salida == null)
                        salida = padreAux(n.getDerecho(), buscado);
                }
            }
        }
        return salida;
    }
    public Lista listarPreorden(){
        Lista salida = new Lista();
        salida = listarPreordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarPreordenAux(NodoArbol n, Lista salida){
        if (n != null){
            salida.insertar(n.getElem(), salida.longitud()+1);
            listarPreordenAux(n.getIzquierdo(), salida);
            listarPreordenAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public Lista listarInorden(){
        Lista salida = new Lista();
        salida = listarInordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarInordenAux(NodoArbol n, Lista salida){
        if (n != null){
            listarInordenAux(n.getIzquierdo(), salida);
            salida.insertar(n.getElem(), salida.longitud()+1);
            listarInordenAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public Lista listarPosorden(){
        Lista salida = new Lista();
        salida = listarPosordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarPosordenAux(NodoArbol n, Lista salida){
        if (n != null){
            listarPosordenAux(n.getIzquierdo(), salida);
            listarPosordenAux(n.getDerecho(), salida);
            salida.insertar(n.getElem(), salida.longitud()+1);
        }
        return salida;
    }
    public Lista listarPorNiveles(){
        Lista salida = new Lista();
        if (this.raiz != null){
            Cola q = new Cola();
            q.poner(this.raiz);
            while (!q.esVacia()){
                NodoArbol nodoActual;
                nodoActual = (NodoArbol) q.obtenerFrente();
                salida.insertar(nodoActual.getElem(), salida.longitud()+1);
                q.sacar();
                if (nodoActual.getIzquierdo() != null)
                    q.poner(nodoActual.getIzquierdo());
                if (nodoActual.getDerecho() != null)
                    q.poner(nodoActual.getDerecho());
            }
        }
        return salida;
    }
    public ArbolBin clone(){
        ArbolBin salida = new ArbolBin();
        if (this.raiz != null){
            salida.raiz = new NodoArbol(this.raiz.getElem());
            salida = cloneAux(this.raiz, salida.raiz, salida);
        }
        return salida;
    }
    private ArbolBin cloneAux(NodoArbol n, NodoArbol nodoClon, ArbolBin salida){
        if (n != null) {
            if (n.getIzquierdo() != null){
                nodoClon.setIzquierdo(new NodoArbol(n.getIzquierdo().getElem()));
                salida = cloneAux(n.getIzquierdo(), nodoClon.getIzquierdo(), salida);
            }
            if (n.getDerecho() != null){
                nodoClon.setDerecho(new NodoArbol(n.getDerecho().getElem()));
                salida = cloneAux(n.getDerecho(), nodoClon.getDerecho(), salida);
            }
        }
        return salida;
    }
    public void vaciar(){
        this.raiz = null;
    }
    public String toString(){
        String salida = "";
        if (this.raiz != null)
            salida = "La raíz es: "+this.raiz.getElem()+"\n"+toStringAux(this.raiz);
        return salida;
    }
    private String toStringAux(NodoArbol n){
        String salida = "";
        if (n != null){
            salida += "Nodo: "+n.getElem();
            if (n.getIzquierdo() != null)
                salida += "  Hijo Izquierdo: "+n.getIzquierdo().getElem();
            else
                salida += "  Hijo Izquierdo: ";
            if (n.getDerecho() != null)
                salida += "  Hijo Derecho: "+n.getDerecho().getElem()+"\n";
            else
                salida += "  Hijo Derecho: \n";
            salida += toStringAux(n.getIzquierdo());
            salida += toStringAux(n.getDerecho());
        }
        return salida;
    }
    public Lista obtenerAncestros(Object elem){
        //retorna los ancestros de elem
        Lista salida = new Lista();
        if (this.raiz != null){
            obtenerAncestrosAux(salida, this.raiz, elem);
            salida.eliminar(1);
        }
        return salida;
    }
    private void obtenerAncestrosAux(Lista salida, NodoArbol n, Object elem){
        //llena la lista salida con los ancestros de elem
        //entra si n no es un nodo nulo
        if (n != null){
            //entra si elem y el elemento de n tienen el mismo valor
            if (n.getElem().equals(elem))
                salida.insertar(elem, salida.longitud()+1);
            else {
                //visita hijo izquierdo
                obtenerAncestrosAux(salida, n.getIzquierdo(), elem);
                //si la lista sigue vacía, visita el hijo derecho
                if (salida.esVacia()){
                    obtenerAncestrosAux(salida, n.getDerecho(), elem);
                }
                //si la lista no está vacía, inserta al elemento de n como ancestro
                if (!salida.esVacia()){
                    salida.insertar(n.getElem(), salida.longitud()+1);
                }
            }
        }
    }
}
