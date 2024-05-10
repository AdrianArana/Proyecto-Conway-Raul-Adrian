package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Entorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Casilla {
    private int coordenadaX;
    private int coordenadaY;
    ListaEnlazada<Individuo> individuos = new ListaEnlazada();
    ListaEnlazada<Entorno> recursos = new ListaEnlazada();

    public Casilla(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public ListaEnlazada<Individuo> getIndividuos() {
        return individuos;
    }

    public void setIndividuos(ListaEnlazada<Individuo> individuos) {
        this.individuos = individuos;
    }

    public ListaEnlazada<Entorno> getRecursos() {
        return recursos;
    }

    public void setRecursos(ListaEnlazada<Entorno> recursos) {
        this.recursos = recursos;
    }
}
