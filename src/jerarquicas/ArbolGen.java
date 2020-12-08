/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
/**
 *
 * @author Martín
 */
public class ArbolGen {
    private NodoGen raiz;
    
    public ArbolGen(){
        this.raiz = null;
    }
    public boolean insertar(Object elemNuevo, Object elemPadre){
        boolean salida = true;
        if (this.raiz == null)
            this.raiz = new NodoGen(elemNuevo);
        else {
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null){
                if (nodoPadre.getHEI() == null)
                    nodoPadre.setHEI(new NodoGen(elemNuevo));
                else {
                    NodoGen nodoHermano = nodoPadre.getHEI();
                    while (nodoHermano.getHD() != null) {
                        nodoHermano = nodoHermano.getHD();
                    }
                    nodoHermano.setHD(new NodoGen(elemNuevo));
                }
            } else
                salida = false;
        }
        return salida;
    }
    private NodoGen obtenerNodo(NodoGen n, Object buscado){
        NodoGen salida = null;
        if (n != null){
            if (n.getElem() == buscado)
                salida = n;
            else {
                salida = obtenerNodo(n.getHEI(), buscado);
                if (salida == null){
                    salida = obtenerNodo(n.getHD(), buscado);
                }
            }
        }
        return salida;
    }
    public boolean pertenece(Object elem){
        boolean salida = false;
        if (obtenerNodo(this.raiz, elem) != null)
            salida = true;
        return salida;
    }
    public boolean esVacio(){
        boolean salida = true;
        if (this.raiz != null)
            salida = false;
        return salida;
    }
    public Object padre(Object elem){
        Object salida = null;
        NodoGen nodoSalida = padreAux(null, elem, this.raiz);
        if (nodoSalida != null)
            salida = nodoSalida.getElem();
        return salida;
    }
    private NodoGen padreAux(NodoGen padre, Object hijo, NodoGen n){
        NodoGen salida = null;
        if (n != null){
            if (n.getElem() == hijo)
                salida = padre;
            else {
                 salida = padreAux(n, hijo, n.getHEI());
                if (salida == null)
                    salida = padreAux(padre, hijo, n.getHD());
            }
        }
        return salida;
    }
    public int altura(){
        int salida = -1;
        if (this.raiz != null){
            int aux = 0;
            salida = 0;
            salida = alturaAux(this.raiz, aux, salida);
        } return salida;
    }
    private int alturaAux(NodoGen n, int altAux, int alt){
        if (n != null){
            if (n.getHEI() == null){
                if (altAux > alt){
                    alt = altAux;
                }
                alt = alturaAux(n.getHD(), altAux, alt);
            } else {
                alt = alturaAux(n.getHEI(), altAux+1, alt);
                alt = alturaAux(n.getHD(), altAux, alt);
            }
        }
        return alt;
    }
    public int nivel(Object elem){
        return nivelAux(this.raiz, elem, 0);
    }
    private int nivelAux(NodoGen n, Object elem, int profundidad){
        int salida = -1;
        if (n != null){
            if (n.getElem() == elem){
                salida = profundidad;
            }
            else {
                salida = nivelAux(n.getHEI(), elem, profundidad+1);
                if (salida == -1){
                    salida = nivelAux(n.getHD(), elem, profundidad);
                }
            }
        }
        return salida;
    }
    public Lista ancestros(Object elem){
        Lista salida = new Lista();
        if (this.raiz != null) {
            salida = ancestrosAux(this.raiz, elem);
            salida.eliminar(1);
        }
        return salida;
    }
    private Lista ancestrosAux(NodoGen n, Object elem){
        Lista salida = new Lista();
        if (n != null){
            if (n.getElem() == elem)
                salida.insertar(n.getElem(), salida.longitud()+1);
            else {
                salida = ancestrosAux(n.getHEI(), elem);
                if (salida.esVacia() == false)
                    salida.insertar(n.getElem(), salida.longitud()+1);
                else
                    salida = ancestrosAux(n.getHD(), elem);
            }
        }
        return salida;
    }
    public ArbolGen clone(){
        ArbolGen salida = new ArbolGen();
        if (this.raiz != null){
            salida.raiz = new NodoGen(this.raiz.getElem());
            cloneAux(this.raiz, salida.raiz, salida);
        }
        return salida;
    }
    private ArbolGen cloneAux(NodoGen n, NodoGen nodoClon, ArbolGen salida){
        if (n.getHEI() != null){
            nodoClon.setHEI(new NodoGen(n.getHEI().getElem()));
            salida = cloneAux(n.getHEI(), nodoClon.getHEI(), salida);
        }
        if (n.getHD() != null) {
            nodoClon.setHD(new NodoGen(n.getHD().getElem()));
            salida = cloneAux(n.getHD(), nodoClon.getHD(), salida);
        }
        return salida;
    }
    public void vaciar(){
        this.raiz = null;
    }
    public Lista listarPreorden(){
        Lista salida = new Lista();
        listaPreordenAux(this.raiz, salida);
        return salida;
    }
    private void listaPreordenAux(NodoGen n, Lista salida){
        if (n != null){
            salida.insertar(n.getElem(), salida.longitud()+1);
            if (n.getHEI() != null) {
                listaPreordenAux(n.getHEI(), salida);
                NodoGen hijo = n.getHEI().getHD();
                while (hijo != null){
                    listaPreordenAux(hijo, salida);
                    hijo = hijo.getHD();
                }
            }
        }
    }
    public Lista listarInorden(){
        Lista salida = new Lista();
        listaInordenAux(this.raiz, salida);
        return salida;
    }
    private void listaInordenAux(NodoGen n, Lista salida){
        if (n != null){
            if (n.getHEI() != null)
                listaInordenAux(n.getHEI(), salida);
            salida.insertar(n.getElem(), salida.longitud()+1);
            if (n.getHEI() != null){
                NodoGen hijo = n.getHEI().getHD();
                while (hijo != null){
                    listaInordenAux(hijo, salida);
                    hijo = hijo.getHD();
                }
            }
        }
    }
    public Lista listarPosorden(){
        Lista salida = new Lista();
        listaPosordenAux(this.raiz, salida);
        return salida;
    }
    private void listaPosordenAux(NodoGen n, Lista salida){
        if (n != null){
            if (n.getHEI() != null) {
                listaPosordenAux(n.getHEI(), salida);
                NodoGen hijo = n.getHEI().getHD();
                while (hijo != null){
                    listaPosordenAux(hijo, salida);
                    hijo = hijo.getHD();
                }
            }
            salida.insertar(n.getElem(), salida.longitud()+1);
        }
    }
    public Lista listaPorNiveles(){
        Lista salida = new Lista();
        if (this.raiz != null){
            Cola q = new Cola();
            q.poner(this.raiz.getElem());
            while (q.esVacia() == false){
                NodoGen nodoActual = obtenerNodo(this.raiz, q.obtenerFrente());
                salida.insertar(q.obtenerFrente(), salida.longitud()+1);
                q.sacar();
                if (nodoActual.getHEI() != null) {
                    q.poner(nodoActual.getHEI().getElem());
                    NodoGen hijo = nodoActual.getHEI().getHD();
                    while (hijo != null){
                        q.poner(hijo.getElem());
                        hijo = hijo.getHD();
                    }
                }
            }
        }
        return salida;
    }
    public String toString(){
        return toStringAux(this.raiz);
    }
    private String toStringAux(NodoGen n){
        String salida = "";
        if (n != null){
            salida += "Padre: "+n.getElem().toString()+", Hijos: ";
            NodoGen hijo = n.getHEI();
            while (hijo != null){
                salida += hijo.getElem().toString() + ", ";
                hijo = hijo.getHD();
            }
            hijo = n.getHEI();
            while (hijo != null){
                salida += "\n"+toStringAux(hijo);
                hijo = hijo.getHD();
            }
        }
        return salida;
    }
}
