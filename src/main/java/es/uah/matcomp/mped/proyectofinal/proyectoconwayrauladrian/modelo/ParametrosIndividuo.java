package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

public class ParametrosIndividuo {
    private int turnosVidaRestantes;
    private int probabilidadMuerte;
    private int probabilidadClonacion;
    private int probabilidadReproduccion;


    public ParametrosIndividuo(int turnosVidaRestantes, int probabilidadMuerte, int probabilidadClonacion, int probabilidadReproduccion) {
        this.turnosVidaRestantes = turnosVidaRestantes;
        this.probabilidadMuerte = probabilidadMuerte;
        this.probabilidadClonacion = probabilidadClonacion;
        this.probabilidadReproduccion = probabilidadReproduccion;
    }

    public int getTurnosVidaRestantes() {
        return turnosVidaRestantes;
    }

    public void setTurnosVidaRestantes(int turnosVidaRestantes) {
        this.turnosVidaRestantes = turnosVidaRestantes;
    }

    public int getProbabilidadMuerte() {
        return probabilidadMuerte;
    }

    public void setProbabilidadMuerte(int probabilidadMuerte) {
        this.probabilidadMuerte = probabilidadMuerte;
    }

    public int getProbabilidadClonacion() {
        return probabilidadClonacion;
    }

    public void setProbabilidadClonacion(int probabilidadClonacion) {
        this.probabilidadClonacion = probabilidadClonacion;
    }

    public int getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public void setProbabilidadReproduccion(int probabilidadReproduccion) {
        this.probabilidadReproduccion = probabilidadReproduccion;
    }
}
