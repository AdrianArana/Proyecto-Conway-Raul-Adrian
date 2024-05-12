package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;

public class Individuo extends ParametrosIndividuo {
    private int coordenadaX;
    private int coordenadaY;
    private int id;
    private int tipo;

    //Constructor vacío
    public Individuo() {
        super();
    }


    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurnoGeneracion() {
        return turnoGeneracion;
    }

    public void setTurnoGeneracion(int turnoGeneracion) {
        this.turnoGeneracion = turnoGeneracion;
    }

    private int turnoGeneracion;

    public Individuo(int turnosVidaRestantes, int probabilidadMuerte, int probabilidadClonacion, int probabilidadReproduccion, int coordenadaX, int coordenadaY, int id, int tipo, int turnoGeneracion) {
        super(turnosVidaRestantes, probabilidadMuerte, probabilidadClonacion, probabilidadReproduccion);
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.id = id;
        this.tipo = tipo;
        this.turnoGeneracion = turnoGeneracion;
    }
}
