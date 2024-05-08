package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Tesoro extends Entorno{
    public void accionTesoro(Individuo individuo) {
        int probabilidadAnterior=individuo.getProbabilidadReproduccion();
        if (probabilidadAnterior+10 <=100) {
            individuo.setProbabilidadReproduccion(probabilidadAnterior + 10);

        } else{
            individuo.setProbabilidadReproduccion(100);
        }
    }
}