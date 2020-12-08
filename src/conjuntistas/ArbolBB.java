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
public class ArbolBB {
    private NodoBB raiz;
    
    public ArbolBB(){
        raiz = null;
    }
    public boolean pertenece(Comparable elem){
        //Retorna true si elem pertenece al árbol
        boolean salida = false;
        //Si el árbol no está vacío, llama a perteneceAux
        if (this.raiz != null){
            salida = perteneceAux(this.raiz, elem);
        }
        return salida;
    }
    private boolean perteneceAux(NodoBB n, Comparable elem){
        //Retorna false si no encuentra a elem
        boolean salida = false;
        //Verifica que el nodo n no sea nulo
        if (n != null){
            //si encuentra elem, retorna true
            if (elem.compareTo(n.getElem()) == 0){
                salida = true;
            } 
            //Si no lo encontró y elem es menor a el valor del nodo n, avanza a
            //izquierda. Si no lo encontró pero elem es mayor al valor de n,
            //avanza por derecha
            else {
                if (elem.compareTo(n.getElem()) < 0)
                    salida = perteneceAux(n.getIzquierdo(), elem);
                else
                    salida = perteneceAux(n.getDerecho(), elem);
            }
        }
        return salida;
    }
    public boolean insertar(Comparable elem){
        //Retorna true si pudo insertar a elem
        boolean salida = true;
        //Si el árbol no está vacío llama a insertarAux, si lo está inserta a 
        //elem como raíz
        if (this.raiz != null)
            salida = insertarAux(this.raiz, elem);
        else
            this.raiz = new NodoBB(elem);
        return salida;
    }
    private boolean insertarAux(NodoBB n, Comparable elem){
        //Retorna true si pudo insertar a elem
        boolean salida = true;
        //Si encuentra un elemento igual a elem, retorna false
        if (elem.compareTo(n.getElem()) == 0) {
            salida = false;
        } else {
            //si elem es menor al valor de n, avanza por izquierda
            if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null)
                    salida = insertarAux(n.getIzquierdo(), elem);
                //si n no tiene hijo izquierdo, se inserta a elem en esa posición
                else
                    n.setIzquierdo(new NodoBB(elem));
            }
            //si elem es mayor al valor de n, avanza por derecha
            else {
                if (n.getDerecho() != null)
                    salida = insertarAux(n.getDerecho(), elem);
                //si n no tiene hijo derecho, se inserta a elem en esa posición
                else
                    n.setDerecho(new NodoBB(elem));
            }
        }
        return salida;
    }
    public boolean eliminar(Comparable elem){
        boolean salida = false;
        //Se verifica que el arbol no este vacio
        if (this.raiz != null) {
            boolean temp = false;
            NodoBB n = this.raiz, padre = null;
            //Itera hasta encontrar el nodo o recorrer todo el arbol sin exito
            while (n != null && temp == false) {
                //Si elem es distinto al contenido del nodo n..
                if (elem.compareTo(n.getElem()) != 0) {
                    //Si es menor, avanza por izquierda
                    if (elem.compareTo(n.getElem()) < 0){
                        padre = n;
                        n = n.getIzquierdo();
                    } //Si es mayor, avanza por derecha
                    else {
                        padre = n;
                        n = n.getDerecho();
                    }
                } //Si elem es igual al contenido del nodo n..
                else {
                    //temp es true porque se encontro elem
                    temp = true;
                }
            } 
            //Si elem se encontró llama a eliminarAux
            if (n != null && temp == true) {
                salida = eliminarAux(n, padre);
            }
        }
        return salida;
    }
    private boolean eliminarAux(NodoBB n, NodoBB padre){
        boolean salida = true;
        //Si es hoja, llama a eliminarCaso1
        if (n.getIzquierdo() == null && n.getDerecho() == null)
            eliminarCaso1(n, padre);
        else {
            //Si tiene 2 hijos, llama a eliminarCaso3
            if (n.getIzquierdo() != null && n.getDerecho() != null)
                eliminarCaso3(n);
            //Si tiene 1 hijo, llama a eliminarCaso2
            else
                eliminarCaso2(n, padre);
        }
        return salida;
    }
    private void eliminarCaso1(NodoBB n, NodoBB padre){
        //Si padre tiene un hijo izquierdo cuyo elem es igual al de n, lo elimina
        if (padre.getIzquierdo() != null && n.getElem().compareTo(padre.getIzquierdo().getElem()) == 0)
            padre.setIzquierdo(null);
        //Sino, se elimina el derecho
        else
            padre.setDerecho(null);
    }
    private void eliminarCaso2(NodoBB n, NodoBB padre){
        //Si padre tiene un hijo izquierdo cuyo elem es igual al de n, lo elimina
        if (padre.getIzquierdo()!= null && n.getElem().compareTo(padre.getIzquierdo().getElem()) == 0){
            //si n tiene hijo izquierdo, se lo enlaza con el padre de n
            if (n.getIzquierdo() != null)
                padre.setIzquierdo(n.getIzquierdo());
            //sino, se enlaza al derecho
            else
                padre.setIzquierdo(n.getDerecho());
        } 
        //Sino, se elimina el derecho
        else{
            //si n tiene hijo izquierdo, se lo enlaza con el padre de n
            if (n.getIzquierdo() != null)
                padre.setDerecho(n.getIzquierdo());
            //sino, se enlaza al derecho
            else
                padre.setDerecho(n.getDerecho());
        }
    }
    private void eliminarCaso3(NodoBB n){
        NodoBB candidato = n.getDerecho(), padre = n;
        //Itera hasta encontrar un candidato (el menor por derecha)
        while (candidato.getIzquierdo() != null){
            padre = candidato;
            candidato = candidato.getIzquierdo();
        }
        //coloca el valor de ese candidato en el nodo que se quiere sacar
        n.setElem(candidato.getElem());
        //si candidato no tiene hijos, se llama a eliminarCaso1
        if (candidato.getIzquierdo() == null && candidato.getDerecho() == null)
            eliminarCaso1(n, padre);
        //sino, se llama a eliminarCaso2
        else {
            eliminarCaso2(candidato, padre);
        }
    }
    public Lista listar(){
        //Lista los elementos del árbol de forma ascendente
        Lista salida = new Lista();
        //verifica que el árbol no esté vacío y llama a listarAux
        if (this.raiz != null)
            listarAux(this.raiz, salida);
        return salida;
    }
    private Lista listarAux(NodoBB n, Lista salida){
        //verifica que n no sea un nodo nulo
        //avanza por izquierda hasta no poder más
        //visita al nodo actual
        //avanza por derecha
        if (n != null){
            listarAux(n.getIzquierdo(), salida);
            salida.insertar(n.getElem(), salida.longitud()+1);
            listarAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public Lista listarPorRango(Comparable elemMin, Comparable elemMax){
        //Lista los elementos en forma ascendente desde elemMin hasta elemMax
        Lista salida = new Lista();
        //verifica que el árbol no esté vacío y llama a listarPorRangoAux
        if (this.raiz != null)
            listarPorRangoAux(this.raiz, salida, elemMin, elemMax);
        return salida;
    }
    private Lista listarPorRangoAux(NodoBB n, Lista salida, Comparable elemMin, Comparable elemMax){
        //verifica que n no sea nodo nulo
        if (n != null){
            //si elemento de n es mayor a elemMin, avanza por izquierda
            if (n.getElem().compareTo(elemMin) > 0)
                listarPorRangoAux(n.getIzquierdo(), salida, elemMin, elemMax);
            //si elemento de n es mayor o igual a elemMin y menor o igual a 
            //elemMax, inserta dicho elemento en la lista
            if (n.getElem().compareTo(elemMin) >= 0 && n.getElem().compareTo(elemMax) <= 0)
                salida.insertar(n.getElem(), salida.longitud()+1);
            //si elemento de n es menor a elemMax, avanza por derecha
            if (n.getElem().compareTo(elemMax) < 0)
                listarPorRangoAux(n.getDerecho(), salida, elemMin, elemMax);
        }
        return salida;
    }
    public Comparable minimoElem(){
        //Retorna el menor elemento del árbol
        NodoBB nodoMenor = this.raiz;
        //itera avanzando por izquierda hasta encontrarse con un null
        while (nodoMenor.getIzquierdo() != null)
            nodoMenor = nodoMenor.getIzquierdo();
        return nodoMenor.getElem();
    }
    public Comparable maximoElem(){
        //Retorna el mayor elemento del árbol
        NodoBB nodoMayor = this.raiz;
        //itera avanzando por derecha hasta encontrarse con un null
        while (nodoMayor.getDerecho() != null)
            nodoMayor = nodoMayor.getDerecho();
        return nodoMayor.getElem();
    }
    public boolean vacio(){
        //Retorna true si no hay elementos en el árbol
        boolean salida = true;
        //verifica que la raíz no sea nula
        if (this.raiz != null)
            salida = false;
        return salida;
    }
    public boolean eliminarMinimo(){
        //Elimina el menor elemento del árbol
        boolean salida = false;
        //Verifica que el árbol no esté vacío
        if (this.raiz != null){
            NodoBB nodoMenor = this.raiz, padre = null;
            //encuentra el menor elemento y su padre
            while (nodoMenor.getIzquierdo() != null){
                padre = nodoMenor;
                nodoMenor = nodoMenor.getIzquierdo();
            } //si el nodo del menor elemento tiene un hijo derecho, llama a 
            //eliminarCaso2
            if (nodoMenor.getDerecho() != null){
                eliminarCaso2(nodoMenor, padre);
            } 
            //si es una hoja, llama a eliminarCaso1
            else {
                eliminarCaso1(nodoMenor, padre);
            }
            salida = true;
        }
        return salida;
    }
    public boolean eliminarMaximo(){
        //Elimina el mayor elemento del árbol
        boolean salida = false;
        //Verifica que el árbol no esté vacío
        if (this.raiz != null){
            NodoBB nodoMayor = this.raiz, padre = null;
            //encuentra el mayor elemento y su padre
            while (nodoMayor.getDerecho() != null){
                padre = nodoMayor;
                nodoMayor = nodoMayor.getDerecho();
            } 
            //si el nodo del mayor elemento tiene un hijo izquierdo, llama a 
            //eliminarCaso2
            if (nodoMayor.getIzquierdo() != null){
                eliminarCaso2(nodoMayor, padre);
            }
            //si es una hoja, llama a eliminarCaso1
            else {
                eliminarCaso1(nodoMayor, padre);
            }
            salida = true;
        }
        return salida;
    }
}
