package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;

public class Individuo extends ParametrosIndividuo{
    private int coordenadaX;
    private int coordenadaY;
    private int id;
    private int tipo;
    private int turnoGeneracion;

    //Constructor vacío
    public Individuo(int x, int y, int id, int tipo, int turnosVidaRestantes,int turnoGeneracion, int probabilidadMuerte, int probabilidadClonacion, int probabilidadReproduccion) {
        super(turnosVidaRestantes,probabilidadMuerte,probabilidadClonacion,probabilidadReproduccion);
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.id = id;
        this.tipo = tipo;
        this.turnoGeneracion = turnoGeneracion;
    }

    public Individuo() {
    }


    @Override
    public String toString() {
        String tipoString = "";
        if(tipo==1){
            tipoString="Básico";
        }else if (tipo==2){
            tipoString="Normal";
        }else if (tipo==3){
            tipoString="Avanzado";
        }
        return
                "\n Coordenadas: (" + coordenadaX +
                "," + coordenadaY +
                ")\n Id: '" + id +
                "'\n Tipo: " + tipoString +
                "'\n Turno de Generacion=" + turnoGeneracion;
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


    public void setTurnosVidaRestantes(int i) {
    }
}
