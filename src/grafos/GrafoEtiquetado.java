/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;
import lineales.dinamicas.*;
/**
 *
 * @author Martín
 */
public class GrafoEtiquetado {
    private NodoVertice inicio;
    
    public GrafoEtiquetado(){
        inicio = null;
    }
    public boolean insertarVertice(Object nuevoVertice){
        boolean exito = false;
        NodoVertice aux = ubicarVertice(nuevoVertice);
        if (aux == null){
            //Si el vértice buscado no existe en la estructura, lo inserta en el inicio de la misma
            this.inicio = new NodoVertice(nuevoVertice, this.inicio, null);
            exito = true;
        }
        return exito;
    }
    private NodoVertice ubicarVertice(Object buscado){
        NodoVertice aux = this.inicio;
        while(aux!=null && !aux.getElem().equals(buscado)){
            aux = aux.getSigVertice();
        }
        return aux;
    }
    public boolean eliminarVertice(Object vertice){
        boolean exito = false;
        NodoVertice aux = ubicarVertice(vertice);
        if (aux!=null){
            //Si encuentra el vertice, elimina los arcos que los tienen como extremo, y al mismo
            eliminarArcosConDestino(aux);
            eliminarArcosConOrigen(aux, aux.getPrimerAdy());
            eliminarVerticeAux(aux);
            exito = true;
        }
        return exito;
    }
    private void eliminarArcosConDestino(NodoVertice dest){
        NodoVertice aux = this.inicio;
        while (aux!=null){
            NodoAdy auxAdy = aux.getPrimerAdy();
            if (auxAdy!=null){
                if (auxAdy.getVertice().getElem().equals(dest.getElem())){
                    //Si dest es el primer adyacente del vertice..
                    if (auxAdy.getSigAdy() != null){
                        aux.setPrimerAdy(auxAdy.getSigAdy());
                    } else {
                        aux.setPrimerAdy(null);
                    }
                } else {
                    //Si no es el primer adyacente, lo busca
                    while (auxAdy.getSigAdy()!=null && !auxAdy.getSigAdy().getVertice().getElem().equals(dest.getElem())){
                        auxAdy = auxAdy.getSigAdy();
                    }
                    //si no es nulo significa que encontró una igualdad
                    if (auxAdy.getSigAdy()!=null) {
                        auxAdy.setSigAdy(auxAdy.getSigAdy().getSigAdy());
                    }
                }
            }
            //avanza en la estructura
            aux = aux.getSigVertice();
        }
    }
    private void eliminarArcosConOrigen(NodoVertice origen, NodoAdy aux){
        eliminarArcosConOrigenAux(aux);
        origen.setPrimerAdy(null);
    }
    private void eliminarArcosConOrigenAux(NodoAdy aux){
        //recorrido recursivo
        //recorre los adyacentes hasta el final en la ida
        //elimina SigAdy de cada uno en la vuelta
        if (aux != null){
            eliminarArcosConOrigenAux(aux.getSigAdy());
            aux.setSigAdy(null);
        }
    }
    private void eliminarVerticeAux(NodoVertice vert){
        //Recorre la estructura hasta encontrar como sigVertice al que se quiere eliminar
        NodoVertice aux = this.inicio;
        if (aux!=null){
            //En caso de que el buscado sea el inicio, hace lo siguiente
            if (aux.getElem().equals(vert.getElem())){
                this.inicio = aux.getSigVertice();
            }
            //Si no es el inicio, lo busca
            else {
                while (aux.getSigVertice()!=null && !aux.getSigVertice().getElem().equals(vert.getElem())){
                    aux = aux.getSigVertice();
                }
                //Si no es nulo, significa que encontró una igualdad
                if (aux.getSigVertice()!=null){
                    aux.setSigVertice(aux.getSigVertice().getSigVertice());
                }
            }
        }
    }
    public boolean insertarArco(Object origen, Object destino, int etiqueta){
        boolean exito = false;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si existen el origen y el destino, se inserta el arco
            auxO.setPrimerAdy(new NodoAdy(auxD, auxO.getPrimerAdy(), etiqueta));
            exito = true;
        }
        return exito;
    }
    public boolean eliminarArco(Object origen, Object destino, int etiqueta){
        boolean exito = false;
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si existen el origen y el destino, se busca y elimina el arco si existe
            NodoAdy auxOAdy = auxO.getPrimerAdy();
            if (auxOAdy != null){
                if (auxOAdy.getVertice().getElem().equals(destino)){
                    //En caso de que el primer adyacente sea el destino
                    auxO.setPrimerAdy(auxOAdy.getSigAdy());
                    exito = true;
                }
                else {
                    //Si no, se busca y elimina el arco, si existe
                    while (auxOAdy.getSigAdy()!=null && !auxOAdy.getSigAdy().getVertice().getElem().equals(destino)){
                        auxOAdy = auxOAdy.getSigAdy();
                    }
                    if (auxOAdy.getSigAdy()!=null){
                        //Si no es nulo, significa que encontró una igualdad
                        auxOAdy.setSigAdy(auxOAdy.getSigAdy().getSigAdy());
                        exito = true;
                    }
                }
            }
        }
        return exito;
    }
    public boolean existeVertice(Object vertice){
        boolean exito = false;
        NodoVertice aux = ubicarVertice(vertice);
        if (aux!=null)
            exito = true;
        return exito;
    }
    public boolean existeArco(Object origen, Object destino){
        boolean exito = false;
        //Verifican si ambos vértices existen
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si ambos vértices existen, busca si existe arco entre ellos
            NodoAdy ady = auxO.getPrimerAdy();
            while (ady!=null && !ady.getVertice().getElem().equals(destino)){
                ady = ady.getSigAdy();
            }
            if (ady!=null){
                exito = true;
            }
        }
        return exito;
    }
    public boolean existeCamino(Object origen,Object destino){
        boolean exito = false;
        //Verifican si ambos vértices existen
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si ambos vértices existen, busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }
    private boolean existeCaminoAux(NodoVertice n, Object dest, Lista vis){
        boolean exito = false;
        if (n != null){
            //Si vértice n es el destino, hay camino
            if (n.getElem().equals(dest)){
                exito = true;
            }
            else {
                //Si no es el destino, verifica si hay camino entre n y dest
                vis.insertar(n.getElem(), vis.longitud()+1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady!=null){
                    if (vis.localizar(ady.getVertice().getElem())<0){
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return exito;
    }
    public Lista caminoMasCorto(Object origen, Object destino){
        Lista salida = new Lista();
        //Verifican si ambos vértices existen
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si ambos vértices existen, busca el camino más corto entre ellos
            Lista aux = new Lista();
            salida = caminoMasCortoAux(auxO, destino, salida, aux);
        }
        return salida;
    }
    private Lista caminoMasCortoAux(NodoVertice n, Object dest, Lista vis, Lista aux){
        if (n != null){
            //Si vértice n es el destino, hay camino
            if (n.getElem().equals(dest)){
                aux.insertar(n.getElem(), aux.longitud()+1);
                if (vis.esVacia() || vis.longitud() > aux.longitud())
                    vis = aux.clone();
            }
            else {
                //Si no es el destino, verifica si hay camino entre n y dest
                aux.insertar(n.getElem(), aux.longitud()+1);
                NodoAdy ady = n.getPrimerAdy();
                while (ady!=null){
                    if (aux.localizar(ady.getVertice().getElem())<0){
                        vis = caminoMasCortoAux(ady.getVertice(), dest, vis, aux);
                        aux.eliminar(aux.longitud());
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return vis;
    }
    public Lista caminoMasLargo(Object origen, Object destino){
        Lista salida = new Lista();
        //Verifican si ambos vértices existen
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si ambos vértices existen, busca el camino más corto entre ellos
            Lista aux = new Lista();
            salida = caminoMasLargoAux(auxO, destino, salida, aux);
        }
        return salida;
    }
    private Lista caminoMasLargoAux(NodoVertice n, Object dest, Lista vis, Lista aux){
        if (n != null){
            //Si vértice n es el destino, hay camino
            if (n.getElem().equals(dest)){
                aux.insertar(n.getElem(), aux.longitud()+1);
                if (vis.longitud() < aux.longitud())
                    vis = aux.clone();
            }
            else {
                //Si no es el destino, verifica si hay camino entre n y dest
                aux.insertar(n.getElem(), aux.longitud()+1);
                NodoAdy ady = n.getPrimerAdy();
                while (ady!=null){
                    if (aux.localizar(ady.getVertice().getElem())<0){
                        vis = caminoMasLargoAux(ady.getVertice(), dest, vis, aux);
                        aux.eliminar(aux.longitud());
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return vis;
    }
    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        //Define un vertice donde comenzar a recorrer
        NodoVertice aux = this.inicio;
        while (aux != null){
            if (visitados.localizar(aux.getElem())<0){
                //Si el vertice no fue visitado aún, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }
    private void listarEnProfundidadAux(NodoVertice n, Lista vis){
        if (n != null){
            //Marca al vertice n como visitado
            vis.insertar(n.getElem(), vis.longitud()+1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null){
                //Visita en profundidad los adyacentes de n aún no visitados
                if (vis.localizar(ady.getVertice().getElem())<0){
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdy();
            }
        }
    }
    public Lista listarEnAnchura(){
        Lista visitados = new Lista();
        //Define un vertice donde comenzar a recorrer
        NodoVertice aux = this.inicio;
        while (aux != null){
            if (visitados.localizar(aux.getElem())<0){
                //Si el vertice no fue visitado aún, avanza en anchura
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }
    private void listarEnAnchuraAux(NodoVertice n, Lista vis){
        Cola q = new Cola();
        vis.insertar(n.getElem(), vis.longitud()+1);
        q.poner(n);
        while (!q.esVacia()){
            //Marca el vertice como visitado
            NodoVertice u = ubicarVertice(q.obtenerFrente());
            q.sacar();
            if (u!=null){
                NodoAdy ady = u.getPrimerAdy();
                while (ady != null){
                    //Visita por anchura los adyacentes de n aún no visitados
                    if (vis.localizar(ady.getVertice().getElem())<0){
                        vis.insertar(ady.getVertice().getElem(), vis.longitud()+1);
                        q.poner(ady);
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
    }
    public boolean esVacio(){
        boolean salida = true;
        if (this.inicio!=null){
            salida = false;
        }
        return salida;
    }
    public GrafoEtiquetado clone(){
        GrafoEtiquetado clon = new GrafoEtiquetado();
        if (this.inicio!=null){
            NodoVertice auxOrig = this.inicio;
            clon.inicio = new NodoVertice(auxOrig.getElem(), null, null);
            NodoVertice auxClon = clon.inicio;
            cloneAuxVertices(clon, auxOrig, auxClon);
            cloneAuxAdyacentes(clon, auxOrig, auxClon);
        }
        return clon;
    }
    private void cloneAuxVertices(GrafoEtiquetado clon, NodoVertice auxOrig, NodoVertice auxClon){
        while (auxOrig.getSigVertice()!=null){
            auxOrig = auxOrig.getSigVertice();
            auxClon.setSigVertice(new NodoVertice(auxOrig.getElem(), null, null));
            auxClon = auxClon.getSigVertice();
        }
    }
    private void cloneAuxAdyacentes(GrafoEtiquetado clon, NodoVertice auxOrig, NodoVertice auxClon){
        while (auxOrig!=null){
            if (auxOrig.getPrimerAdy()!=null){
                NodoAdy adyOrig = auxOrig.getPrimerAdy();
                auxClon.setPrimerAdy(new NodoAdy(clon.ubicarVertice(adyOrig.getVertice().getElem()), null, adyOrig.getEtiqueta()));
                NodoAdy adyClon = auxClon.getPrimerAdy();
                while (adyOrig.getSigAdy()!=null){
                    adyOrig = adyOrig.getSigAdy();
                    adyClon.setSigAdy(new NodoAdy(clon.ubicarVertice(adyOrig.getVertice().getElem()), null, adyOrig.getEtiqueta()));
                    adyClon = adyClon.getSigAdy();
                }
            }
            auxOrig = auxOrig.getSigVertice();
            auxClon = auxClon.getSigVertice();
        }
    }
    @Override
    public String toString(){
        String salida = "";
        if (this.inicio != null){
            NodoVertice aux = this.inicio;
            while (aux!=null){
                NodoAdy ady = aux.getPrimerAdy();
                salida += "Vertice: "+aux.getElem().toString()+" Adyacentes: ";
                while (ady!=null){
                    salida += ady.getVertice().getElem().toString()+" ";
                    ady = ady.getSigAdy();
                }
                aux = aux.getSigVertice();
                if (aux!=null)
                    salida += "\n";
            }
        }
        return salida+"\n";
    }
    
    public Lista caminoMasCortoPorEtiquetas(Object origen, Object destino){
        Lista salida = new Lista();
        //Verifican si ambos vértices existen
        NodoVertice auxO = ubicarVertice(origen);
        NodoVertice auxD = ubicarVertice(destino);
        if (auxO!=null && auxD!=null){
            //Si ambos vértices existen, busca el camino más corto entre ellos
            Lista aux = new Lista();
            int etiqAux = 0;
            salida.insertar(aux, 1);
            salida.insertar(0, 2);
            salida = caminoMasCortoPorEtiquetasAux(auxO, destino, salida, aux, etiqAux);
        }
        return (Lista) salida.recuperar(1);
    }
    private Lista caminoMasCortoPorEtiquetasAux(NodoVertice n, Object dest, Lista vis, Lista aux, int etiqAux){
        if (n != null){
            //Si vértice n es el destino, hay camino
            if (n.getElem().equals(dest)){
                aux.insertar(n.getElem(), aux.longitud()+1);
                int etiq = (int) vis.recuperar(2);
                if (etiq==0 || (int) vis.recuperar(2)>etiqAux) {
                    vis.insertar(aux.clone(), 1);
                    vis.insertar(etiqAux, 2);
                }
            }
            else {
                //Si no es el destino, verifica si hay camino entre n y dest
                aux.insertar(n.getElem(), aux.longitud()+1);
                NodoAdy ady = n.getPrimerAdy();
                while (ady!=null){
                    if (aux.localizar(ady.getVertice().getElem())<0){
                        vis = caminoMasCortoPorEtiquetasAux(ady.getVertice(), dest, vis, aux, etiqAux+ady.getEtiqueta());
                        aux.eliminar(aux.longitud());
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return vis;
    }
}
