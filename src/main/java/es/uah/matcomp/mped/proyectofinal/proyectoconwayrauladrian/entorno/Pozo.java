package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Pozo extends Entorno{
    public Pozo(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Pozo() {

    }

    public void accionPozo(Individuo individuo){
        individuo.setTurnosVidaRestantes(-1);
    }
    @Override
    public String toString() {
        return "POZO";
    }


}
