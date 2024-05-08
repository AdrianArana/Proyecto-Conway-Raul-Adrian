package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Comida extends Entorno{
    public void accionComida(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()+10);
    }
}