package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;

import com.google.gson.annotations.Expose;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Entorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import javafx.scene.control.Button;
public class Casilla {
    @Expose
    private int coordenadaX;
    @Expose
    private int coordenadaY;
    @Expose
    ListaEnlazada<Individuo> individuos = new ListaEnlazada<Individuo>();
    @Expose
    ListaEnlazada<Entorno> recursos = new ListaEnlazada<Entorno>();
    @Expose
    private Button boton;

    public Casilla(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    public void setBoton(Button botonDado){
        this.boton=botonDado;
    }

    public Button getBoton() {
        return boton;
    }

    public void setColorBotonRojo(){
        this.boton.setStyle("-fx-background-color: #ff0000; -fx-text-alignment: center;");
        this.boton.setText(coordenadaX+","+coordenadaY);
    }

    public Casilla() {
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

    @Override
    public String toString() {
        return "Casilla(" +coordenadaX+","+coordenadaY+")";

    }
}
