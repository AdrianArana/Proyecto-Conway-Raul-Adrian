package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Montaña {
    public void accionMontaña(Individuo individuo){
        individuo.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes()-2);
    }
}
