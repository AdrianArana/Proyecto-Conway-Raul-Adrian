package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Biblioteca extends Entorno{
    public void accionBiblioteca(Individuo individuo) {
        double probabilidadAnterior = individuo.getProbabilidadClonacion();
        if (probabilidadAnterior + 20 <= 100) {
            individuo.setProbabilidadReproduccion(probabilidadAnterior + 20);
        } else {
            individuo.setProbabilidadReproduccion(100);
        }
        subirDeTipo(individuo);
    }

    private void subirDeTipo(Individuo individuo) {
        individuo.setTipo(individuo.getTipo() + 1);
    }
}
