package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.pozoMataIndividuos;

public class Pozo extends Entorno{
    public Pozo(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Pozo() {

    }

    public static void accionPozo(Individuo individuo){
        if (pozoMataIndividuos){
            individuo.setTurnosVidaRestantes(-1);
        }
    }
    @Override
    public String toString() {
        return "POZO";
    }


}
