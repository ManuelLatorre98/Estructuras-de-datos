/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.grafos;
import grafos.GrafoEtiquetado;
/**
 *
 * @author Martín
 */
public class TestGrafoEtiq {
    public static void main(String [] args){
        
        GrafoEtiquetado g = new GrafoEtiquetado();
        
        System.out.println("estructura: "+g.toString());
        System.out.println("es vacío? "+g.esVacio());
        System.out.println("insertar vertices: ");
        System.out.println("C: "+g.insertarVertice('C'));
        System.out.println("D: "+g.insertarVertice('D'));
        System.out.println("B: "+g.insertarVertice('B'));
        System.out.println("A: "+g.insertarVertice('A'));
        System.out.println("A: "+g.insertarVertice('A'));
        System.out.println("insertar arcos: ");
        System.out.println("A-C: "+g.insertarArco('A', 'C', 2));
        System.out.println("A-B: "+g.insertarArco('A', 'B', 1));
        System.out.println("B-C: "+g.insertarArco('B', 'C', 3));
        System.out.println("C-D: "+g.insertarArco('C', 'D', 4));
        System.out.println("D-B: "+g.insertarArco('D', 'B', 5));
        System.out.println("D-Z: "+g.insertarArco('D', 'Z', 6));
        System.out.println("estructura: \n"+g.toString());
        System.out.println("es vacío? "+g.esVacio());
        System.out.println("existe camino desde A hasta D? "+g.existeCamino('A', 'D'));
        System.out.println("existe camino desde D hasta A? "+g.existeCamino('D', 'A'));
        System.out.println("camino más corto desde A hasta D: "+g.caminoMasCorto('A', 'D'));
        System.out.println("camino más largo desde A hasta D: "+g.caminoMasLargo('A', 'D'));
        System.out.println("listar en profundidad: "+g.listarEnProfundidad());
        System.out.println("listar en anchura: "+g.listarEnAnchura());
        System.out.println("clonar: \n"+g.clone().toString());
        System.out.println("existe arco B-C? "+g.existeArco('B', 'C'));
        System.out.println("eliminar arco B-C: "+g.eliminarArco('B', 'C', 3));
        System.out.println("existe arco B-C? "+g.existeArco('B', 'C'));
        System.out.println("estructura: "+g.toString());
        System.out.println("existe vertice B? "+g.existeVertice('B'));
        System.out.println("eliminar vertice B: "+g.eliminarVertice('B'));
        System.out.println("existe vertice B? "+g.existeVertice('B'));
        System.out.println("estructura: \n"+g.toString());
    }
}
