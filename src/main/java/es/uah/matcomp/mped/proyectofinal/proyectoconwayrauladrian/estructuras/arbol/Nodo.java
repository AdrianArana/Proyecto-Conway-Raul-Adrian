package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol;

public class Nodo<TipoDato> {
    Nodo<TipoDato> derecha;
    Nodo<TipoDato> izquierda;
    TipoDato dato;


    public Nodo(Nodo<TipoDato> derecha, Nodo<TipoDato> izquierda, TipoDato dato) {
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.dato = dato;
    }


    public Nodo(TipoDato dato) {
        this.dato = dato;
    }

    public Nodo<TipoDato> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<TipoDato> derecha) {
        this.derecha = derecha;
    }

    public Nodo<TipoDato> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<TipoDato> izquierda) {
        this.izquierda = izquierda;
    }

    public TipoDato getDato() {
        return this.dato;
    }

    public void setDato(TipoDato dato) {
        this.dato = dato;
    }

    public int gradoNodo() {
        Nodo<TipoDato> n = this;
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
        Nodo<TipoDato> n = this;
        return n.getDerecha() == null && n.getIzquierda() == null;
    }
}