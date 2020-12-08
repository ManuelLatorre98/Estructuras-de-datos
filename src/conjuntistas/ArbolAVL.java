/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

import lineales.dinamicas.Lista;

/**
 *
 * @author Martín
 */
public class ArbolAVL {
    private NodoAVL raiz;
    
    public ArbolAVL(){
        raiz = null;
    }
    public boolean pertenece(Comparable elem){
        boolean salida = false;
        if (this.raiz != null){
            salida = perteneceAux(this.raiz, elem);
        }
        return salida;
    }
    private boolean perteneceAux(NodoAVL n, Comparable elem){
        boolean salida = false;
        if (n != null){
            if (elem.compareTo(n.getElem()) == 0){
                salida = true;
            } else {
                if (elem.compareTo(n.getElem()) < 0)
                    salida = perteneceAux(n.getIzquierdo(), elem);
                else
                    salida = perteneceAux(n.getDerecho(), elem);
            }
        }
        return salida;
    }
    public boolean insertar(Comparable elem){
        boolean salida = true;
        if (this.raiz != null)
            salida = insertarAux(this.raiz, elem);
        else
            this.raiz = new NodoAVL(elem);
        return salida;
    }
    private boolean insertarAux(NodoAVL n, Comparable elem){
        boolean salida = true;
        if (elem.compareTo(n.getElem()) == 0) {
            salida = false;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null){
                    salida = insertarAux(n.getIzquierdo(), elem);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        reorganizar(n, n.getIzquierdo(), balance);
                    }
                }
                else
                    n.setIzquierdo(new NodoAVL(elem));
            }
            else {
                if (n.getDerecho() != null){
                    salida = insertarAux(n.getDerecho(), elem);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        reorganizar(n, n.getDerecho(), balance);
                    }
                }
                else
                    n.setDerecho(new NodoAVL(elem));
            }
        }
        return salida;
    }
    public boolean eliminar(Comparable elem){
        boolean salida = true;
        if (this.raiz != null)
            salida = eliminarAux(this.raiz, null, elem);
        else
            salida = false;
        return salida;
    }
    private boolean eliminarAux(NodoAVL n, NodoAVL padre, Comparable elem){
        boolean salida = true;
        if (elem.compareTo(n.getElem()) == 0) {
            salida = eliminarElegirCaso(n, padre);
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null){
                    salida = eliminarAux(n.getIzquierdo(), n, elem);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        reorganizar(n, n.getIzquierdo(), balance);
                    }
                }
                else
                    salida = false;
            }
            else {
                if (n.getDerecho() != null){
                    salida = eliminarAux(n.getDerecho(), n, elem);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        reorganizar(n, n.getDerecho(), balance);
                    }
                }
                else
                    salida = false;
            }
        }
        return salida;
    }
    private boolean eliminarElegirCaso(NodoAVL n, NodoAVL padre){
        boolean salida = true;
        if (n.getIzquierdo() == null && n.getDerecho() == null)
            eliminarCaso1(n, padre);
        else {
            if (n.getIzquierdo() != null && n.getDerecho() != null)
                eliminarCaso3(n);
            else
                eliminarCaso2(n, padre);
        }
        return salida;
    }
    private void eliminarCaso1(NodoAVL n, NodoAVL padre){
        if (padre.getIzquierdo() != null && n.getElem().compareTo(padre.getIzquierdo().getElem()) == 0)
            padre.setIzquierdo(null);
        else
            padre.setDerecho(null);
    }
    private void eliminarCaso2(NodoAVL n, NodoAVL padre){
        if (n.getElem().compareTo(padre.getIzquierdo().getElem()) == 0){
            if (n.getIzquierdo() != null)
                padre.setIzquierdo(n.getIzquierdo());
            else
                padre.setIzquierdo(n.getDerecho());
        } else{
            if (n.getIzquierdo() != null)
                padre.setDerecho(n.getIzquierdo());
            else
                padre.setDerecho(n.getDerecho());
        }
    }
    private void eliminarCaso3(NodoAVL n){
        NodoAVL candidato = n.getDerecho(), padre = n;
        while (candidato.getIzquierdo() != null){
            padre = candidato;
            candidato = candidato.getIzquierdo();
        }
        n.setElem(candidato.getElem());
        if (candidato.getIzquierdo() == null && candidato.getDerecho() == null)
            eliminarCaso1(candidato, padre);
        else {
            eliminarCaso2(candidato, padre);
        }
    }
    public Lista listar(){
        Lista salida = new Lista();
        if (this.raiz != null)
            listarAux(this.raiz, salida);
        return salida;
    }
    private Lista listarAux(NodoAVL n, Lista salida){
        if (n != null){
            listarAux(n.getIzquierdo(), salida);
            salida.insertar(n.getElem(), salida.longitud()+1);
            listarAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public Lista listarPorRango(Comparable elemMin, Comparable elemMax){
        Lista salida = new Lista();
        if (this.raiz != null)
            listarPorRangoAux(this.raiz, salida, elemMin, elemMax);
        return salida;
    }
    private Lista listarPorRangoAux(NodoAVL n, Lista salida, Comparable elemMin, Comparable elemMax){
        if (n != null){
            if (elemMin.compareTo(n.getElem()) < 0)
                listarPorRangoAux(n.getIzquierdo(), salida, elemMin, elemMax);
            else
                listarPorRangoAux(n.getDerecho(), salida, elemMin, elemMax);
            if (elemMin.compareTo(n.getElem()) < 0 && elemMax.compareTo(n.getElem()) > 0)
                salida.insertar(n.getElem(), salida.longitud()+1);
            if (elemMax.compareTo(n.getElem()) > 0)
                listarPorRangoAux(n.getDerecho(), salida, elemMin, elemMax);
            else
                listarPorRangoAux(n.getIzquierdo(), salida, elemMin, elemMax);
        }
        return salida;
    }
    public Comparable minimoElem(){
        NodoAVL nodoMenor = this.raiz;
        while (nodoMenor.getIzquierdo() != null)
            nodoMenor = nodoMenor.getIzquierdo();
        return nodoMenor.getElem();
    }
    public Comparable maximoElem(){
        NodoAVL nodoMayor = this.raiz;
        while (nodoMayor.getDerecho() != null)
            nodoMayor = nodoMayor.getDerecho();
        return nodoMayor.getElem();
    }
    public boolean vacio(){
        boolean salida = true;
        if (this.raiz != null)
            salida = false;
        return salida;
    }
    private NodoAVL rotarIzq(NodoAVL pivot){
        NodoAVL hijo = pivot.getDerecho(), temp = hijo.getIzquierdo();
        hijo.setIzquierdo(pivot);
        pivot.setDerecho(temp);
        return hijo;
    }
    private NodoAVL rotarDer(NodoAVL pivot){
        NodoAVL hijo = pivot.getIzquierdo(), temp = hijo.getDerecho();
        hijo.setDerecho(pivot);
        pivot.setIzquierdo(temp);
        return hijo;
    }
    private int balance(NodoAVL n){
        int hD, hI;
        if (n.getIzquierdo() != null)
            hI = n.getIzquierdo().getAltura();
        else
            hI = -1;
        if (n.getDerecho() != null)
            hD = n.getDerecho().getAltura();
        else
            hD = -1;
        return (hI - hD);
    }
    private void reorganizar(NodoAVL pivot, NodoAVL hijo, int balancePivot){
        int balHijo = balance(hijo);
        if (balancePivot == 2 && balHijo >= 0)
            rotarDer(pivot);
        if (balancePivot == 2 && balHijo == -1){
            rotarIzq(hijo);
            rotarDer(pivot);
        }
        if (balancePivot == -2 && balHijo <= 0)
            rotarIzq(pivot);
        if (balancePivot == -2 && balHijo == 1){
            rotarDer(hijo);
            rotarIzq(pivot);
        }
    }
}
