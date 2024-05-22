package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Agua extends Entorno {

    public Agua(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Agua() {
        super();
    }

    public static void accionAgua(Individuo individuo) {
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes() + 2);
    }

    @Override
    public String toString() {
        return "AGUA";
    }

    public String getTipo() {
        return "Agua";
    }
}
