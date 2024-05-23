package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import com.google.gson.annotations.Expose;

public class ParametrosEntorno {
    //Esta clase únicamente va a almacenar las propiedades de partida elegidas para cada recurso;
    @Expose
    public int tiempoAparicion;
    @Expose
    public int probabilidadAgua;
    @Expose
    public int probabilidadComida;
    @Expose
    public int probabilidadMontaña;
    @Expose
    public int probabilidadTesoro;
    @Expose
    public int probabilidadBiblioteca;
    @Expose
    public int probabilidadPozo;
    @Expose
    public int probabilidadGeneral;

    public ParametrosEntorno(int tiempoAparicion, int probabilidadGeneral, int probabilidadAgua, int probabilidadComida, int probabilidadMontaña, int probabilidadTesoro, int probabilidadBiblioteca, int probabilidadPozo) {
        this.tiempoAparicion = tiempoAparicion;

        this.probabilidadAgua = probabilidadAgua;
        this.probabilidadComida = probabilidadComida;
        this.probabilidadMontaña = probabilidadMontaña;
        this.probabilidadTesoro = probabilidadTesoro;
        this.probabilidadBiblioteca = probabilidadBiblioteca;
        this.probabilidadPozo = probabilidadPozo;
        this.probabilidadGeneral = probabilidadGeneral;
    }

    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }

    public int getProbabilidadAgua() {
        return probabilidadAgua;
    }

    public void setProbabilidadAgua(int probabilidadAgua) {
        this.probabilidadAgua = probabilidadAgua;
    }

    public int getProbabilidadComida() {
        return probabilidadComida;
    }

    public void setProbabilidadComida(int probabilidadComida) {
        this.probabilidadComida = probabilidadComida;
    }

    public int getProbabilidadMontaña() {
        return probabilidadMontaña;
    }

    public void setProbabilidadMontaña(int probabilidadMontaña) {
        this.probabilidadMontaña = probabilidadMontaña;
    }

    public int getProbabilidadTesoro() {
        return probabilidadTesoro;
    }

    public void setProbabilidadTesoro(int probabilidadTesoro) {
        this.probabilidadTesoro = probabilidadTesoro;
    }

    public int getProbabilidadBiblioteca() {
        return probabilidadBiblioteca;
    }

    public void setProbabilidadBiblioteca(int probabilidadBiblioteca) {
        this.probabilidadBiblioteca = probabilidadBiblioteca;
    }

    public int getProbabilidadPozo() {
        return probabilidadPozo;
    }

    public void setProbabilidadPozo(int probabilidadPozo) {
        this.probabilidadPozo = probabilidadPozo;
    }

    public void setProbabilidadGeneral(int probabilidadGeneral) {
        this.probabilidadGeneral = probabilidadGeneral;
    }

    public int getProbabilidadGeneral() {
        return probabilidadGeneral;
    }

    public int getTiempoAparicion() {
        return tiempoAparicion;
    }
}