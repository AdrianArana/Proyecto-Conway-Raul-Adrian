package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Comida extends Entorno{

    public Comida(int coordenadaX, int coordenadaY, int tiempoAparicion, int probabilidadDeEsteRecurso) {
        super(coordenadaX, coordenadaY, tiempoAparicion, probabilidadDeEsteRecurso);
    }

    public void accionComida(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()+10);
    }

}
