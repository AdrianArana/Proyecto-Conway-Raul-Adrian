package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;


import com.google.gson.annotations.Expose;

public class Entorno {
    @Expose
    public int coordenadaX;
    @Expose
    public int coordenadaY;
    @Expose
    public int tiempoAparicion;
    @Expose
    public int probabilidadDeEsteRecurso;

    public Entorno(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.tiempoAparicion = tiempoAparicion;
    }

    public Entorno() {
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

    public int getTiempoAparicion() {
        return tiempoAparicion;
    }

    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }

    public int getProbabilidadDeEsteRecurso() {
        return probabilidadDeEsteRecurso;
    }

    public void setProbabilidadDeEsteRecurso(int probabilidadDeEsteRecurso) {
        this.probabilidadDeEsteRecurso = probabilidadDeEsteRecurso;
    }
}
