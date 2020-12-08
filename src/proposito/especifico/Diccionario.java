/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proposito.especifico;
import lineales.dinamicas.Lista;
/**
 *
 * @author Martín
 */
public class Diccionario {
    private NodoAVLDicc raiz;
    
    public Diccionario(){
        //Crea diccionario sin elementos
        raiz = null;
    }
    private NodoAVLDicc rotarIzq(NodoAVLDicc pivotPadre, NodoAVLDicc pivot){
        //Coloca al pivot como hijo izquierdo de su hijo y al hijo izquierdo que
        //tenía su hijo lo pone como hijo derecho del él
        NodoAVLDicc hijo = pivot.getDerecho(), temp = hijo.getIzquierdo();
        hijo.setIzquierdo(pivot);
        pivot.setDerecho(temp);
        //Pone al hijo correspondiente del pivot como hijo correspondiente del padre del pivot
        if (pivotPadre!=null){
            if (pivotPadre.getIzquierdo()!=null && pivotPadre.getIzquierdo().getClave().compareTo(pivot.getClave()) == 0)
                pivotPadre.setIzquierdo(hijo);
            else
                pivotPadre.setDerecho(hijo);
        }
        //Si pivot era la raíz, lo pone al hijo como raíz
        if (this.raiz.getClave().compareTo(pivot.getClave())==0)
            this.raiz = hijo;
        return hijo;
    }
    private NodoAVLDicc rotarDer(NodoAVLDicc pivotPadre, NodoAVLDicc pivot){
        //Coloca al pivot como hijo derecho de su hijo y al hijo derecho que
        //tenía su hijo lo pone como hijo izquierdo de del él
        NodoAVLDicc hijo = pivot.getIzquierdo(), temp = hijo.getDerecho();
        hijo.setDerecho(pivot);
        pivot.setIzquierdo(temp);
        //Pone al hijo correspondiente del pivot como hijo correspondiente del padre del pivot
        if (pivotPadre!=null){
            if (pivotPadre.getIzquierdo()!=null && pivotPadre.getIzquierdo().getClave().compareTo(pivot.getClave()) == 0)
                pivotPadre.setIzquierdo(hijo);
            else
                pivotPadre.setDerecho(hijo);
        }
        //Si pivot era la raíz, lo pone al hijo como raíz
        if (this.raiz.getClave().compareTo(pivot.getClave())==0)
            this.raiz = hijo;
        return hijo;
    }
    private int balance(NodoAVLDicc n){
        //Calcula el balance de un nodo
        //Si el nodo no existe, retorna -1
        //Si el nodo existe, calcula su balance con la altura de sus hijos
        int salida, hD, hI;
        if (n != null){
            if (n.getIzquierdo() != null)
                hI = n.getIzquierdo().getAltura();
            else
                hI = -1;
            if (n.getDerecho() != null)
                hD = n.getDerecho().getAltura();
            else
                hD = -1;
            salida = (hI - hD);
        }
        else {
            salida = -1;
        }
        return salida;
    }
    private void reorganizar(NodoAVLDicc pivotPadre, NodoAVLDicc pivot, NodoAVLDicc hijoI, NodoAVLDicc hijoD, int balancePivot){
        //Hace las rotaciones correspondientes para que el nodo quede balanceado
        int balHijoI = balance(hijoI);
        int balHijoD = balance(hijoD);
        if (balancePivot == 2 && balHijoI >= 0)
            rotarDer(pivotPadre, pivot);
        if (balancePivot == 2 && balHijoI == -1){
            rotarIzq(pivot, hijoI);
            rotarDer(pivotPadre, pivot);
        }
        if (balancePivot == -2 && balHijoD <= 0)
            rotarIzq(pivotPadre, pivot);
        if (balancePivot == -2 && balHijoD == 1){
            rotarDer(pivot, hijoD);
            rotarIzq(pivotPadre, pivot);
        }
    }
    public boolean insertar(Comparable clave, Comparable dato){
        //Si el árbol no está vacío, inserta el nodo donde corresponda
        //Si el árbol está vacío, inserta al nodo como raíz
        boolean salida = true;
        if (this.raiz != null)
            salida = insertarAux(this.raiz, null, clave, dato);
        else
            this.raiz = new NodoAVLDicc(clave, dato);
        return salida;
    }
    private boolean insertarAux(NodoAVLDicc n, NodoAVLDicc nPadre, Comparable clave, Comparable dato){
        boolean salida = true;
        if (clave.compareTo(n.getClave()) == 0) {
            //Si ya existe el nodo, la operación termina sin éxito
            salida = false;
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                //Si el nodo actual tiene clave mayor a la buscada, se avanza por izquierda
                if (n.getIzquierdo() != null){
                    //Si tiene hijo izquierdo, se avanza por izquierda
                    salida = insertarAux(n.getIzquierdo(), n, clave, dato);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        //Si el nodo actual está desbalanceado, se balancea
                        reorganizar(nPadre, n, n.getIzquierdo(), n.getDerecho(), balance);
                    }
                }
                else {
                    //Si no tiene hijo izquierdo, se coloca al nodo como tal
                    n.setIzquierdo(new NodoAVLDicc(clave, dato));
                }
            }
            else {
                //Si el nodo actual tiene clave menor a la buscada, se avanza por derecha
                if (n.getDerecho() != null){
                    //Si tiene hijo derecho, se avanza por derecho
                    salida = insertarAux(n.getDerecho(), n, clave, dato);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        //Si el nodo actual está desbalanceado, se balancea
                        reorganizar(nPadre, n, n.getIzquierdo(), n.getDerecho(), balance);
                    }
                }
                else {
                    //Si no tiene hijo derecho, se coloca al nodo como tal
                    n.setDerecho(new NodoAVLDicc(clave, dato));
                }
            }
        }
        return salida;
    }
    public boolean eliminar(Comparable clave){
        //Si el árbol está vacío, no se puede eliminar y retorna falso
        //Si el árbol no está vacío, se procede al módulo eliminarAux
        boolean salida;
        if (this.raiz != null)
            salida = eliminarAux(this.raiz, null, clave);
        else
            salida = false;
        return salida;
    }
    private boolean eliminarAux(NodoAVLDicc n, NodoAVLDicc padre, Comparable clave){
        boolean salida;
        if (clave.compareTo(n.getClave()) == 0) {
            //Si lo encuentra, se procede al método eliminarElegirCaso
            salida = eliminarElegirCaso(n, padre);
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                //Si la clave buscada es menor a la del nodo actual, se avanza por izquierda
                if (n.getIzquierdo() != null){
                    //Si el nodo actual tiene hijo izquierdo, se avanza por izquierda
                    salida = eliminarAux(n.getIzquierdo(), n, clave);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        //Si el nodo actual está desbalanceado, se balancea
                        reorganizar(padre, n, n.getIzquierdo(), n.getDerecho(), balance);
                    }
                }
                else
                    //Si el nodo actual no tiene hijo izquierdo, no se puede avanzar y retorna false
                    salida = false;
            }
            else {
                //Si la clave buscada es mayor a la del nodo actual, se avanza por derecha
                if (n.getDerecho() != null){
                    //Si el nodo actual tiene hijo derecho, se avanza por derecha
                    salida = eliminarAux(n.getDerecho(), n, clave);
                    int balance = balance(n);
                    if (balance > 1 || balance < -1){
                        //Si el nodo actual está desbalanceado, se balancea
                        reorganizar(padre, n, n.getIzquierdo(), n.getDerecho(), balance);
                    }
                }
                else
                    //Si el nodo actual no tiene hijo derecho, no se puede avanzar y retorna false
                    salida = false;
            }
        }
        return salida;
    }
    private boolean eliminarElegirCaso(NodoAVLDicc n, NodoAVLDicc padre){
        boolean salida = true;
        if (n.getIzquierdo() == null && n.getDerecho() == null)
            //Si el nodo n tiene ambos hijos, se procede con el caso 1
            eliminarCaso1(n, padre);
        else {
            if (n.getIzquierdo() != null && n.getDerecho() != null)
                //Si el nodo n no tiene hijos, se procede con el caso 3
                eliminarCaso3(n);
            else
                //Si el nodo n tiene 1 solo hijo, se procede con el caso 2
                eliminarCaso2(n, padre);
        }
        return salida;
    }
    private void eliminarCaso1(NodoAVLDicc n, NodoAVLDicc padre){
        //Se elimina el hijo correspondiente
        if (padre.getIzquierdo() != null && n.getClave().compareTo(padre.getIzquierdo().getClave()) == 0)
            padre.setIzquierdo(null);
        else
            padre.setDerecho(null);
    }
    private void eliminarCaso2(NodoAVLDicc n, NodoAVLDicc padre){
        //Se elimina el hijo correspondiente
        if (n.getClave().compareTo(padre.getIzquierdo().getClave()) == 0){
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
    private void eliminarCaso3(NodoAVLDicc n){
        //Se busca un candidato entre los hijos y se lo reemplaza al nodo por este
        NodoAVLDicc candidato = n.getDerecho(), padre = n;
        while (candidato.getIzquierdo() != null){
            //Se busca el candidato
            padre = candidato;
            candidato = candidato.getIzquierdo();
        }
        //Se reemplaza al nodo
        n.setElem(candidato.getClave());
        //Se elimina al candidato
        if (candidato.getIzquierdo() == null && candidato.getDerecho() == null)
            eliminarCaso1(candidato, padre);
        else {
            eliminarCaso2(candidato, padre);
        }
    }
    public Comparable obtenerDato(Comparable clave){
        //Retorna el dato de clave
        //Si el árbol está vacío, retorna null
        Comparable salida = null;
        if (this.raiz != null){
            //Si el árbol no está vacío, prosigue por obtenerDatoAux
            salida = obtenerDatoAux(this.raiz, clave);
        }
        return salida;
    }
    private Comparable obtenerDatoAux(NodoAVLDicc n, Comparable clave){
        Comparable salida = null;
        if (n != null){
            if (clave.compareTo(n.getClave()) == 0){
                //Si encuentra la cave, retorna el dato
                salida = n.getDato();
            } else {
                //Si clave es menor a la clave del nodo actual, busca por izquierda
                //Si es mayor, busca por derecha
                if (clave.compareTo(n.getClave()) < 0)
                    salida = obtenerDatoAux(n.getIzquierdo(), clave);
                else
                    salida = obtenerDatoAux(n.getDerecho(), clave);
            }
        }
        return salida;
    }
    public boolean existeClave(Comparable clave){
        //Verifica la existencia de clave
        //Si el árbol está vacío, retorna false
        //Si no está vacío, avanza por existeClaveAux
        boolean salida = false;
        if (this.raiz != null){
            salida = existeClaveAux(this.raiz, clave);
        }
        return salida;
    }
    private boolean existeClaveAux(NodoAVLDicc n, Comparable clave){
        boolean salida = false;
        if (n != null){
            if (clave.compareTo(n.getClave()) == 0){
                //Si encuentra la clave, retorna true
                salida = true;
            } else {
                //Si clave es menor a la clave del nodo actual, busca por izquierda
                //Si es mayor, busca por derecha
                if (clave.compareTo(n.getClave()) < 0)
                    salida = existeClaveAux(n.getIzquierdo(), clave);
                else
                    salida = existeClaveAux(n.getDerecho(), clave);
            }
        }
        return salida;
    }
    public Lista listarClaves(){
        //Lista todas las claves del árbol
        Lista salida = new Lista();
        if (this.raiz != null)
            //Verifica que el árbol no este vacío y avanza a listarClavesAux
            listarClavesAux(this.raiz, salida);
        return salida;
    }
    private Lista listarClavesAux(NodoAVLDicc n, Lista salida){
        //Lista las claves de forma ascendente
        if (n != null){
            listarClavesAux(n.getIzquierdo(), salida);
            salida.insertar(n.getClave(), salida.longitud()+1);
            listarClavesAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public Lista listarDatos(){
        //Lista todos los datos del árbol
        Lista salida = new Lista();
        if (this.raiz != null)
            //Si el árbol no está vacío, avanza a listarDatosAux
            listarDatosAux(this.raiz, salida);
        return salida;
    }
    private Lista listarDatosAux(NodoAVLDicc n, Lista salida){
        //Lista los datos
        if (n != null){
            listarDatosAux(n.getIzquierdo(), salida);
            salida.insertar(n.getDato(), salida.longitud()+1);
            listarDatosAux(n.getDerecho(), salida);
        }
        return salida;
    }
    public boolean esVacio(){
        //Retorna true si el árbol está vacío, sino false
        boolean salida = true;
        if (this.raiz != null)
            salida = false;
        return salida;
    }
    @Override
    public String toString(){
        //Si el árbol no está vacío
        //Retorna un String con cada nodo diciendo quienes son sus hijos
        String salida = "";
        if (this.raiz!=null){
            salida = toStringAux(this.raiz, salida);
        }
        return salida;
    }
    private String toStringAux(NodoAVLDicc n, String salida){
        if (n!=null){
            salida+= "Nodo: "+n.getClave()+" Hijo izquierdo: ";
            if (n.getIzquierdo()!=null){
                //Si tiene hijo izquierdo, lo coloca en el string
                salida+= n.getIzquierdo().getClave().toString();
            }
            salida+= " Hijo derecho: ";
            if (n.getDerecho()!=null){
                //Si tiene hijo derecho, lo coloca en el string
                salida+= n.getDerecho().getClave().toString();
            }
            salida+= "\n";
            salida = toStringAux(n.getIzquierdo(), salida);
            salida = toStringAux(n.getDerecho(), salida);
        }
        return salida;
    }
}
