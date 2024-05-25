package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import com.google.gson.annotations.Expose;

public class ParametrosIndividuo {
    @Expose
    public int turnosVidaRestantes;
    @Expose
    public int probabilidadMuerte;
    @Expose
    public int probabilidadClonacion;
    @Expose
    public int probabilidadReproduccion;



    public ParametrosIndividuo(int turnosVidaRestantes, int probabilidadMuerte, int probabilidadClonacion, int probabilidadReproduccion) {
        this.turnosVidaRestantes = turnosVidaRestantes;
        this.probabilidadMuerte = probabilidadMuerte;
        this.probabilidadClonacion = probabilidadClonacion;
        this.probabilidadReproduccion = probabilidadReproduccion;
    }
    //Constructor vacio, que temporalmente permite crear un individuo vacío,ya que implementa esta clase
    public ParametrosIndividuo() {

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
        this.probabilidadClonacion = probabilidadClonacion; }

    public int getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public void setProbabilidadReproduccion(int probabilidadReproduccion) {
        this.probabilidadReproduccion = probabilidadReproduccion;
    }
}
