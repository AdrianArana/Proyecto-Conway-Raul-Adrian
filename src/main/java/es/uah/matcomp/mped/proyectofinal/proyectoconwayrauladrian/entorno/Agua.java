package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Agua extends Entorno {

    public Agua(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public void accionAgua(Individuo individuo) {
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()+2);
    }

}
