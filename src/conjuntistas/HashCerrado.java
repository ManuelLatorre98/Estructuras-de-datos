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
public class HashCerrado {
    private static final int TAMANIO=10;
    private CeldaHash[] tabla;
    private int cant;
    private static final int VACIO=0;
    private static final int OCUPADO=1;
    private static final int BORRADO=-1;
    
    public HashCerrado(){
        //Crea una tabla de hash cerrado y se le asigna a cada celda un nodo sin
        //elemento pero con un indicador de si hay, hubo o no hubo un elemento
        this.tabla = new CeldaHash[TAMANIO];
        this.cant = 0;
        int i;
        for (i=0; i<this.TAMANIO; i++){
            this.tabla[i] = new CeldaHash();
        }
    }
    private int rehash(Object elem){
        //Calcula el incremento para las colisiones, obteniendo el primer dígito
        int funcionDos = (int) elem;
        if (funcionDos>9) {
            do{
                funcionDos = funcionDos /10;
            } while (funcionDos>=10); }
        return funcionDos;
    }
    public boolean pertenece(Object buscado){
        //Devuelve true si el elemento está en la tabla, sino false
        boolean salida = false;
        //Verifica que haya elementos en la tabla
        if (this.cant>0){
            int intento = 1;
            int incremento = rehash(buscado)%this.TAMANIO;
            int pos = buscado.hashCode()%this.TAMANIO;
            //Verifica que el elemento no haya sido encontrado, que el nodo de 
            //pos esté vacío y que la cantidad de intentos no sea mayor a la
            //longitud de la tabla
            while (!salida && this.tabla[pos].getEstado()!=VACIO && intento<this.TAMANIO){
                //Si había un elemento en esta posición, avanza
                if (this.tabla[pos].getEstado()==BORRADO){
                    pos = (pos+incremento*intento)%this.TAMANIO;
                    intento++;
                }
                //Si hay un elemento en esta posición
                else{
                    //Verifica si el elemento buscado es igual al de la celda pos
                    if (this.tabla[pos].getElem().equals(buscado)){
                        salida = true;
                    } else {
                        pos = (pos+incremento*intento)%this.TAMANIO;
                        intento++;
                    }
                }
            }
        }
        return salida;
    }
    public boolean insertar(Object elem){
        //Retorna true si se puede insertar
        boolean encontrado = this.pertenece(elem);
        boolean salida = false;
        int porcentajeOcupado = (this.cant+1)*100/this.TAMANIO;
        //Verifica que no exista el elemento en la tabla y que el porcentaje de
        //ocupación de la tabla no sea mayor al 75% si se agrega este elemento
        if (!encontrado && porcentajeOcupado < 75){
            int pos = elem.hashCode()%this.TAMANIO;
            int incremento = rehash(elem)%this.TAMANIO;
            int intento = 1;
            //Repite el incremento mientras el estado de la celda visitada indique
            //que ya hay un elemento allí
            while (this.tabla[pos].getEstado() == OCUPADO){
                pos = (pos+incremento*intento)%this.TAMANIO;
                intento++;
            }
            this.tabla[pos].setElem(elem);
            this.tabla[pos].setEstado(OCUPADO);
            this.cant++;
            salida = true;
        }
        return salida;
    }
    public boolean eliminar(Object buscado){
        //Retorna true si puede eliminar el elemento llamado "buscado"
        int pos = buscado.hashCode()%this.TAMANIO;
        int incremento = rehash(buscado)%this.TAMANIO;
        boolean encontrado = false;
        int intento = 1;
        //Repite hasta encontrar el elemento y borrarlo, hasta que haya más intentos
        //que Celdas o hasta que lleguemos a una celda que nunca tuvo un elemento
        //indicando que este elemento no está en la tabla
        while (!encontrado && intento<this.TAMANIO && this.tabla[pos].getEstado() != VACIO){
            //Verifica si existe un elemento en la celda pos
            if (this.tabla[pos].getEstado() == OCUPADO){
                encontrado = this.tabla[pos].getElem().equals(buscado);
                //Si encuentra el elemento, lo borra
                if (encontrado){
                    this.tabla[pos].setEstado(BORRADO);
                    this.tabla[pos].setElem(null);
                    this.cant--;
                }
            }
            pos = (pos+intento*incremento)%this.TAMANIO;
            intento++;
        }
        return encontrado;
    }
    public boolean esVacia(){
        //Devuelve true si la tabla no tiene elementos
        return (this.cant == 0);
    }
    public Lista listar(){
        //Devuelve lista con los elementos de la tabla
        Lista l = new Lista();
        //entra si la tabla tiene elementos
        if(this.cant>0){
            CeldaHash aux;
            int cantAux= this.cant, i=0;
            //mientras haya elementos que listar, va a seguir iterando
            while(cantAux > 0){
                //mientras en la misma posicion hayan elementos, va a seguir iterando
                aux = this.tabla[i];
                if (aux.getElem() != null){
                    l.insertar(aux.getElem(), l.longitud()+1);
                    cantAux--;
                }
                i++;
            }
        }
        return l;
    }
    public String toString(){
        //Retorna una cadena que indique el elemento según cada posición
        String salida ="";
        int i;
        //Controla que la cantidad de iteraciones no sea mayor a la tabla
        for (i=0; i<this.TAMANIO; i++){
            //Si hay elemento en la posición i, lo coloca, sino deja la posición vacía
            if (this.tabla[i].getElem() != null){
                salida += "posición "+i+": "+this.tabla[i].getElem()+"\n";
            } else{
                salida += "posición "+i+": \n";
            }
        }
        return salida;
    }
}
