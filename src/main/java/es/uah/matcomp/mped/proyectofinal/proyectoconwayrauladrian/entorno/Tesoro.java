package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Tesoro extends Entorno{
    public void accionTesoro(Individuo individuo) {
        double probabilidadAnterior=individuo.getProbabilidadReproduccion();
        if (probabilidadAnterior+0.10 <=1) {
            individuo.setProbabilidadReproduccion(probabilidadAnterior + 0.10);

        } else{
            individuo.setProbabilidadReproduccion(1);
        }
    }
}
