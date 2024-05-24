package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.turnosVidaExtraComida;

public class Comida extends Entorno{

    public Comida(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Comida() {

    }

    public static void accionComida(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()+turnosVidaExtraComida);
    }
    @Override
    public String toString() {
        return "COMIDA";
    }
}
