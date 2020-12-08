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
public class ArbolHeap {
    //Arbol ArbolHeap Mínimo
    private static final int TAM = 20;
    private Comparable[] heap;
    private int ultimo;
    
    public ArbolHeap(){
        this.heap = new Comparable[this.TAM];
        this.ultimo = 0;
    }
    public boolean insertar(Comparable elem){
        boolean salida = false;
        if (this.ultimo != TAM-1){
            this.ultimo++;
            this.heap[this.ultimo] = elem;
            hacerSubir(elem);
            salida = true; }
        return salida;
    }
    private void hacerSubir(Comparable elem){
        int posElem = this.ultimo;
        boolean fin = false;
        while (!fin) {
            //Comprueba que la posicion del padre no sea la posicion 0 del array
            if (posElem/2 > 0) {
                if (this.heap[posElem].compareTo(this.heap[posElem/2]) < 0){
                    this.heap[posElem] = this.heap[posElem/2];
                    this.heap[posElem/2] = elem;
                    posElem = posElem/2;
                } else
                    fin = true;
            } else
                fin = true;
            
        }
    }
    public boolean eliminarCima(){
        boolean salida = false;
        if (this.ultimo != 0){
            this.heap[1] = this.heap[this.ultimo];
            this.heap[this.ultimo] = null;
            this.ultimo--;
            hacerBajar(1);
            salida = true; }
        return salida;
    }
    private void hacerBajar(int posPadre){
        int posHijo;
        Comparable aux = this.heap[posPadre];
        boolean fin = false;
        while (!fin){
            posHijo = posPadre*2;
            if (posHijo <= this.ultimo){
                if (posHijo < this.ultimo){
                    if (this.heap[posHijo].compareTo(this.heap[posHijo+1]) > 0)
                        posHijo++;
                }
                if (aux.compareTo(this.heap[posHijo]) > 0){
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = aux;
                    posPadre = posHijo;
                } else
                    fin = true;
            } else
                fin = true;
        }
    }
    public Comparable recuperarCima(){
        Comparable salida = null;
        if (this.ultimo > 0)
            salida = this.heap[1];
        return salida;
    }
    public boolean esVacio(){
        boolean salida = false;
        if (this.ultimo == 0)
            salida = true;
        return salida;
    }
    public String toString(){
        String salida = "";
        if (this.ultimo > 0){
            int aux = 1;
            while (aux <= this.ultimo){
                salida = salida+this.heap[aux]+"--> HI: "+this.heap[aux*2]+" HD: "+this.heap[aux*2+1]+"\n";
                aux++;
            }
        }
        return salida;
    }
    public ArbolHeap clone(){
        ArbolHeap clon = new ArbolHeap();
        clon.ultimo = this.ultimo;
        clon.heap = this.heap.clone();
        return clon;
    }
}
