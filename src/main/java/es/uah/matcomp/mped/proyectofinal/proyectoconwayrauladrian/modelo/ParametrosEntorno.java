package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

public class ParametrosEntorno {
    //Esta clase únicamente va a almacenar las propiedades de partida elegidas para cada recurso;
    private int tiempoHastaDesaparicion; //todo //porque se llaman distinto tiempohastadesaparicion y tiempoAparicion
    private double probabilidadAgua;
    private double probabilidadComida;
    private double probabilidadMontaña;
    private double probabilidadTesoro;
    private double probabilidadBiblioteca;
    private double probabilidadPozo;

    public ParametrosEntorno(int tiempoHastaDesaparicion, double probabilidadAgua, double probabilidadComida, double probabilidadMontaña, double probabilidadTesoro, double probabilidadBiblioteca, double probabilidadPozo) {
        this.tiempoHastaDesaparicion = tiempoHastaDesaparicion;
        this.probabilidadAgua = probabilidadAgua;
        this.probabilidadComida = probabilidadComida;
        this.probabilidadMontaña = probabilidadMontaña;
        this.probabilidadTesoro = probabilidadTesoro;
        this.probabilidadBiblioteca = probabilidadBiblioteca;
        this.probabilidadPozo = probabilidadPozo;
    }
    public int getTiempoHastaDesaparicion() {
        return tiempoHastaDesaparicion;
    }

    public void setTiempoHastaDesaparicion(int tiempoHastaDesaparicion) {
        this.tiempoHastaDesaparicion = tiempoHastaDesaparicion;
    }

    public double getProbabilidadAgua() {
        return probabilidadAgua;
    }

    public void setProbabilidadAgua(double probabilidadAgua) {
        this.probabilidadAgua = probabilidadAgua;
    }

    public double getProbabilidadComida() {
        return probabilidadComida;
    }

    public void setProbabilidadComida(double probabilidadComida) {
        this.probabilidadComida = probabilidadComida;
    }

    public double getProbabilidadMontaña() {
        return probabilidadMontaña;
    }

    public void setProbabilidadMontaña(double probabilidadMontaña) {
        this.probabilidadMontaña = probabilidadMontaña;
    }

    public double getProbabilidadTesoro() {
        return probabilidadTesoro;
    }

    public void setProbabilidadTesoro(double probabilidadTesoro) {
        this.probabilidadTesoro = probabilidadTesoro;
    }

    public double getProbabilidadBiblioteca() {
        return probabilidadBiblioteca;
    }

    public void setProbabilidadBiblioteca(double probabilidadBiblioteca) {
        this.probabilidadBiblioteca = probabilidadBiblioteca;
    }

    public double getProbabilidadPozo() {
        return probabilidadPozo;
    }

    public void setProbabilidadPozo(double probabilidadPozo) {
        this.probabilidadPozo = probabilidadPozo;
    }


}
