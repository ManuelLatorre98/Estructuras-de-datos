/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author Martín
 */
public class Lista{
    private Nodo cabecera;
    private int longitud;
    
    public Lista(){
        //crea una lista vacía, con la cabecera en null y longitud 0
        this.cabecera = null;
        this.longitud = 0;
    }
    public boolean insertar(Object nuevoElem, int pos){
        //insertar nuevoElem en la lista y retorna true, si la posición es válida
        boolean salida = true;
        //verifica posicion válida
        if (pos<1 || pos>this.longitud+1){
            salida = false;
        }
        //si la posición es válida ejecuta lo siguiente
        else {
            //si la posición es 1, coloca a nuevoElem como cabecera
            if (pos==1){
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            } else {
                //si la posición es mayor a 1, utiliza a aux para recorrer la
                //lista hasta la posición anterior a la que se quiere colocar
                //al nuevoElem
                Nodo aux = this.cabecera;
                int i = 1;
                while (i<pos-1){
                    aux = aux.getEnlace();
                    i++;
                } Nodo nuevoNodo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
                this.longitud++;
            }
        }
        return salida;
    }
    public boolean eliminar(int pos){
        //elmina el nodo que esté en la posición pos y retorna true, si es 
        //válida la posición
        boolean salida = false;
        //verifica que la lista no esté vacía y que pos sea válido
        if (this.cabecera != null && pos>0 && pos<this.longitud+1){
            //si la posición es 1, ejecuta lo siguiente
            if (pos==1){
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            }
            //si la posición es mayor a 1, ejecuta lo siguiente
            else {
                //aux va a recorrer la lista hasta la posición anterior a la que
                //se quiere eliminar
                Nodo aux = this.cabecera;
                int i = 1;
                while (i<pos-1){
                    aux = aux.getEnlace();
                    i++;
                } aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            } salida = true;
        }
        return salida;
    }
    public Object recuperar(int pos){
        //retorna el elemento de la posición pos, si es una posición válida
        Object salida = null;
        //verifica que pos sea válida
        if (pos>0 && pos<this.longitud+1){
            //aux va a recorrer la lista hasta la posición indicada
            Nodo aux = this.cabecera;
            int i = 1;
            while (i<pos){
                aux = aux.getEnlace();
                i++;
            }
            salida = aux.getElem();
        }
        return salida;
    }
    public int localizar(Object elem){
        //retorna la posición de elem, si no lo encuentra retorna -1
        int salida = -1;
        if (this.cabecera != null){
            //aux va a recorrer la lista hasta encontrar el elemento
            int i=1;
            Nodo aux = this.cabecera;
            while (i<this.longitud+1 && aux.getElem()!=elem){
                aux = aux.getEnlace();
                i++;
            }
            if (aux != null)
                salida = i;
        }
        return salida;
    }
    public void vaciar(){
        //vacía la lista
        this.cabecera = null;
        this.longitud = 0;
    }
    public boolean esVacia(){
        //true si la lista está vacía
        boolean salida = true;
        if (this.cabecera!=null)
            salida = false;
        return salida;
    }
    public Lista clone(){
        //retorna copia exacta de la lista
        Lista clon = new Lista();
        if (this.cabecera != null){
            //aux1 va a recorrer la lista original mientras que aux2, la copia
            clon.longitud = this.longitud;
            clon.cabecera = new Nodo(this.cabecera.getElem(), null);
            Nodo aux1 = this.cabecera;
            Nodo aux2 = clon.cabecera;
            while (aux1.getEnlace() != null){
                aux1 = aux1.getEnlace();
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
            }
        }
        return clon;
    }
    public int longitud(){
        //retorna la cantidad de elementos que hay en la lista
        return this.longitud;
    }
    public String toString(){
        //retorna una cadena con los elementos de la lista
        String salida = "";
        if (this.cabecera != null){
            //aux va a recorrer la lista
            Nodo aux = this.cabecera;
            while (aux != null){
                salida += aux.getElem()+" ";
                aux = aux.getEnlace();
            }
        } else {
            salida = "Lista vacía";
        }
        return salida;
    }
}
