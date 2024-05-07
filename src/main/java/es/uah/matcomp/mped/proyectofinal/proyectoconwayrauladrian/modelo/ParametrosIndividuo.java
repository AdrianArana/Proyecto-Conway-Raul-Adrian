package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

public class ParametrosIndividuo {
    private int turnosVidaRestantes;
    private double probabilidadMuerte;
    private double probabilidadClonacion;
    private double probabilidadReproduccion;

    public ParametrosIndividuo(int turnosVidaRestantes, double probabilidadMuerte, double probabilidadClonacion, double probabilidadReproduccion) {
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

    public double getProbabilidadMuerte() {
        return probabilidadMuerte;
    }

    public void setProbabilidadMuerte(double probabilidadMuerte) {
        this.probabilidadMuerte = probabilidadMuerte;
    }

    public double getProbabilidadClonacion() {
        return probabilidadClonacion;
    }

    public void setProbabilidadClonacion(double probabilidadClonacion) {
        this.probabilidadClonacion = probabilidadClonacion;
    }

    public double getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public void setProbabilidadReproduccion(double probabilidadReproduccion) {
        this.probabilidadReproduccion = probabilidadReproduccion;
    }
}
