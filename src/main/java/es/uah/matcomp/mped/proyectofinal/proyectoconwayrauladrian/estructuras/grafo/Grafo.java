package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.grafo;



import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Grafo<TipoDato> {
    private ListaEnlazada<Vertice<TipoDato>> vertices;
    private ListaEnlazada<Arco<TipoDato>> arcos;

    public Grafo(ListaEnlazada<Vertice<TipoDato>> vertices, ListaEnlazada<Arco<TipoDato>> arcos) {
        this.vertices = vertices;
        this.arcos = arcos;
    }


    public Grafo() {
        this.vertices = new ListaEnlazada<>();
        this.arcos = new ListaEnlazada<>();
    }

    public void addVertice(Vertice<TipoDato> vertice) {
        if (vertices.isVacia()) {
            vertices.add(vertice);
        } else {
            boolean bool = false;//Suponemos que el vértice que vamos a añadir no está presente en nuestro grafo
            for (int x = 0; x < vertices.getNumeroElementos(); x++) {
                if (vertice.getId() == vertices.getElemento(x).getData().getId()) {
                    bool = true;//Ahora lo cambiamos en el momento en el que detectemos que este. Solo se mete aquí si está en la lista de vértices
                }
            }
            if (!bool) {
                vertices.add(vertice);
            }
        }
    }


    public void deleteVertice(Vertice vertice) {
        if (vertices.isVacia()) {
            System.out.println("ERROR ESTA VACIA");
        } else {
            for (int x = 0; x < vertices.getNumeroElementos(); x++) {
                if (vertice.getId() == vertices.getElemento(x).getData().getId()) {
                    //ListaEnlazada<Arco<TipoDato>> listaFrontera=vertices.getElemento(x).getData().getFrontera();
                    //for (int y = 0; y <= listaFrontera.getNumeroElementos(); y++) {
                    //    listaFrontera.delete(y);
                    //}
                    vertices.delete(x);
                }
            }
        }
    }

    public boolean buscarVertice(Vertice vertice) {
        if (vertices.isVacia()) {
            return false;
        } else {
            for (int x = 0; x < vertices.getNumeroElementos(); x++) {
                if (vertice.getId() == vertices.getElemento(x).getData().getId()) {
                    return true;
                }
            }
            return false;
        }
    }

    public void addArco(Vertice v1, Vertice v2, double peso, String nombre) {
        ElementoLE<Vertice<TipoDato>> v1Elem = new ElementoLE<Vertice<TipoDato>>(v1);
        ElementoLE<Vertice<TipoDato>> v2Elem = new ElementoLE<Vertice<TipoDato>>(v2);
        Arco arcoNuevo = new Arco<>(v1, v2, peso, nombre);
        if (vertices.getPosicion(v1Elem) != -1 && vertices.getPosicion(v2Elem) != -1) {
            ElementoLE<Arco<TipoDato>> arcoNuevoElem = new ElementoLE<Arco<TipoDato>>(arcoNuevo);
            if (arcos.getPosicion(arcoNuevoElem) == -1) {
                arcos.add(arcoNuevo);
                v1.getFrontera().add(arcoNuevoElem);
                v2.getFrontera().add(arcoNuevoElem);
                v1.setConectado(true);
                v2.setConectado(true);
            }
        }
    }


    public ListaEnlazada<Vertice<TipoDato>> getVertices() {
        return vertices;
    }

    public void setVertices(ListaEnlazada<Vertice<TipoDato>> vertices) {
        this.vertices = vertices;
    }

    public ListaEnlazada<Arco<TipoDato>> getArcos() {
        return arcos;
    }

    public void setArcos(ListaEnlazada<Arco<TipoDato>> arcos) {
        this.arcos = arcos;
    }

    public void borrarArco(Arco arco) {
        ElementoLE arcoElem = new ElementoLE(arco);
        if (arcos.isVacia()) {
            System.out.println("No hay ningún arco en el grafo");
        } else if (arcos.getPosicion(arcoElem) == -1) {
            System.out.println("Ese arco no está, -colega-");
        } else {
            int posicion = arcos.getPosicion(arcoElem);
            int posicion1 = arco.getV1().getFrontera().getPosicion(arcoElem);
            int posicion2 = arco.getV2().getFrontera().getPosicion(arcoElem);
            arcos.delete(posicion);
            arco.getV1().getFrontera().delete(posicion1);
            arco.getV2().getFrontera().delete(posicion2);
        }
    }

    public Map<Vertice<TipoDato>, Camino<TipoDato>> dijkstra(Vertice<TipoDato> origen) {

        Map<Vertice<TipoDato>, Double> distancias = new HashMap<>();
        Queue<Vertice<TipoDato>> colaPendientes = new LinkedList<>();
        Map<Vertice<TipoDato>, Vertice<TipoDato>> verticesanteriores = new HashMap<>();

        this.dijkstra_init(origen, distancias, colaPendientes, verticesanteriores);  //Inicialización
        this.dijkstra_calcula(distancias, colaPendientes, verticesanteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias, verticesanteriores);    //Procesamiento de resultados


    }


    protected void dijkstra_init(Vertice<TipoDato> origen, Map<Vertice<TipoDato>, Double> distancias, Queue<Vertice<TipoDato>> colaPendientes, Map<Vertice<TipoDato>, Vertice<TipoDato>> verticesAnteriores) {

        int posicion = 0;

        while (posicion <= this.vertices.getNumeroElementos() - 1) {

            distancias.put(vertices.getElemento(posicion).getData(), Double.MAX_VALUE);
            posicion++;
        }

        distancias.put(origen, 0.0);
        colaPendientes.add(origen);
    }

    protected void dijkstra_calcula(Map<Vertice<TipoDato>, Double> distancias, Queue<Vertice<TipoDato>> colaPendientes, Map<Vertice<TipoDato>, Vertice<TipoDato>> verticesAnteriores) {
        while (!colaPendientes.isEmpty()) {
            Vertice<TipoDato> verticeActual = colaPendientes.poll();  // Sacamos un vértice de la cola, el primero

            ListaEnlazada<Arco<TipoDato>> listaadyacentes = verticeActual.getFrontera();

            int posicion = 0;

            while (posicion < listaadyacentes.getNumeroElementos() - 1) {

                Arco<TipoDato> arco = listaadyacentes.getElemento(posicion).getData();
                Vertice<TipoDato> verticeadyacente;

                if (verticeActual.getId() == arco.getV1().getId()) {
                    verticeadyacente = arco.getV2();
                } else {
                    verticeadyacente = arco.getV1();
                }


                double nuevoCalculoDeDistancia = distancias.get(verticeActual) + arco.getPeso();

                if (nuevoCalculoDeDistancia < distancias.get(verticeadyacente)) {
                    distancias.put(verticeadyacente, nuevoCalculoDeDistancia);
                    verticesAnteriores.put(verticeadyacente, verticeActual);
                    colaPendientes.add(verticeadyacente);
                }

                posicion++;
            }

        }

    }


    protected Map<Vertice<TipoDato>, Camino<TipoDato>> dijkstra_procesaResultados(Map<Vertice<TipoDato>, Double> distancias, Map<Vertice<TipoDato>, Vertice<TipoDato>> verticesAnteriores) {
        Map<Vertice<TipoDato>, Camino<TipoDato>> caminos = new HashMap<>();

        for (Vertice<TipoDato> verticeDestino : verticesAnteriores.keySet()) {
            ListaEnlazada<Vertice<TipoDato>> caminoCalculado = new ListaEnlazada<>();
            Vertice<TipoDato> v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v = verticesAnteriores.get(v);
            }

            caminos.put(verticeDestino, new Camino<>(caminoCalculado, distancias.get(verticeDestino)));
        }
        return caminos;
    }
}


