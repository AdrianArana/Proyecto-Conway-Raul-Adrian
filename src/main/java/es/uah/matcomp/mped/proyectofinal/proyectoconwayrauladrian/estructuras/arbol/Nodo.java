package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol;

public class Nodo<Individuo> {
    Nodo<Individuo> derecha;
    Nodo<Individuo> izquierda;
    Individuo dato;


    public Nodo(Nodo<Individuo> derecha, Nodo<Individuo> izquierda, Individuo dato) {
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.dato = dato;
    }


    public Nodo(Individuo dato) {
        this.dato = dato;
    }

    public Nodo<Individuo> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<Individuo> derecha) {
        this.derecha = derecha;
    }

    public Nodo<Individuo> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<Individuo> izquierda) {
        this.izquierda = izquierda;
    }

    public Individuo getDato() {
        return this.dato;
    }

    public void setDato(Individuo dato) {
        this.dato = dato;
    }

    public int gradoNodo() {
        Nodo<Individuo> n = this;
        int contador = 0;
        if (n.getIzquierda() != null) {
            contador++;
        }
        if (n.getDerecha() != null) {
            contador++;
        }
        return contador;
    }

    public boolean esHoja() {
        Nodo<Individuo> n = this;
        return n.getDerecha() == null && n.getIzquierda() == null;
    }
}