package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.grafo;


import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;

public class Camino<TipoDato> {
    ListaEnlazada<Vertice<TipoDato>> caminoDeVertices;
    double coste;

    public Camino(ListaEnlazada<Vertice<TipoDato>> caminoDeVertices, double coste){
        this.caminoDeVertices = caminoDeVertices;
        this.coste = coste;
    }
    public double getCoste(){
        return coste;
    }

    public ListaEnlazada<Vertice<TipoDato>> getCaminoDeVertices() {
        return caminoDeVertices;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        salida.append("======= Volcado del camino desde [" + getCaminoDeVertices().getPrimero().getData().getId() + "] hasta [" + getCaminoDeVertices().getUltimo().getData().getId() + "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCaminoDeVertices() + "\n");
        salida.append("Lista de datos en vértices: [");

        int posicion=0;

        while (posicion <= this.caminoDeVertices.getNumeroElementos() - 1 ) {

            salida.append(this.caminoDeVertices.getElemento(posicion).getData().getId());
            posicion++;
        }


        salida.append("] - Coste: " + this.getCoste() + "\n");

        return salida.toString();
    }
}
