package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.probabilidadClonacionExtraBiblioteca;
import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.subirDeTipoBiblioteca;

public class Biblioteca extends Entorno{
    public Biblioteca(int coordenadaX, int coordenadaY, int tiempoAparicion) {
        super(coordenadaX, coordenadaY, tiempoAparicion);
    }

    public Biblioteca() {
    }

    public static void accionBiblioteca(Individuo individuo) {
        int probabilidadAnterior = individuo.getProbabilidadClonacion();
        if (probabilidadAnterior + probabilidadClonacionExtraBiblioteca <= 100) {
            individuo.setProbabilidadClonacion(probabilidadAnterior + probabilidadClonacionExtraBiblioteca);
        } else {
            individuo.setProbabilidadClonacion(100);
        }
        subirDeTipo(individuo);
    }


    private static void subirDeTipo(Individuo individuo) {
        if (individuo.getTipo()+1<=3 && subirDeTipoBiblioteca){
            individuo.setTipo(individuo.getTipo() + 1);
        }
    }

    @Override
    public String toString() {
        return "BIBLIOTECA";
    }
}

