package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

public class ParametrosEntorno {
    //Esta clase únicamente va a almacenar las propiedades de partida elegidas para cada recurso;

    private int probabilidadAgua;
    private int probabilidadComida;
    private int probabilidadMontaña;
    private int probabilidadTesoro;
    private int probabilidadBiblioteca;
    private int probabilidadPozo;

    public ParametrosEntorno( int probabilidadAgua, int probabilidadComida, int probabilidadMontaña, int probabilidadTesoro, int probabilidadBiblioteca, int probabilidadPozo) {

        this.probabilidadAgua = probabilidadAgua;
        this.probabilidadComida = probabilidadComida;
        this.probabilidadMontaña = probabilidadMontaña;
        this.probabilidadTesoro = probabilidadTesoro;
        this.probabilidadBiblioteca = probabilidadBiblioteca;
        this.probabilidadPozo = probabilidadPozo;
    }

    public ParametrosEntorno() {
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
}
