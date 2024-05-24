package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.turnosDevidaMenosMontaña;

public class Montaña extends Entorno{

    public Montaña(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Montaña() {

    }

    public static void accionMontaña(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()-turnosDevidaMenosMontaña);
    }
    @Override
    public String toString() {
        return "MONTAÑA";
    }
}
