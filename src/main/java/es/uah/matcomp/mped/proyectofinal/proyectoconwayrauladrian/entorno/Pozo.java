package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Pozo extends Entorno{
    public void accionPozo(Individuo individuo){
        individuo.setTurnosVidaRestantes(-1);
    }
}
