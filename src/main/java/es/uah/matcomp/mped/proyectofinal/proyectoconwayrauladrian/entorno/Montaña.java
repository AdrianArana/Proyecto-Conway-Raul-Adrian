package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Montaña extends Entorno{

    public Montaña(int coordenadaX, int coordenadaY, int tiempoAparicion, int probabilidadDeEsteRecurso) {
        super(coordenadaX, coordenadaY, tiempoAparicion, probabilidadDeEsteRecurso);
    }

    public void accionMontaña(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()-2);
    }

}
