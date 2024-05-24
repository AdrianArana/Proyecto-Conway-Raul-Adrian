package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.probabilidadReproduccionExtraTesoro;

public class Tesoro extends Entorno{

    public Tesoro(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Tesoro() {

    }

    public static void accionTesoro(Individuo individuo) {
        int probabilidadAnterior=individuo.getProbabilidadReproduccion();
        if (probabilidadAnterior+probabilidadReproduccionExtraTesoro <=100) {
            individuo.setProbabilidadReproduccion(probabilidadAnterior + probabilidadReproduccionExtraTesoro);

        } else{
            individuo.setProbabilidadReproduccion(100);
        }
    }


    @Override
    public String toString() {
        return "TESORO";
    }
}
