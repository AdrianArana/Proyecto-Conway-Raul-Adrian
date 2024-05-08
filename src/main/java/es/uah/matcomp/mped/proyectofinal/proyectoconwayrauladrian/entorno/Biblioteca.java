package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

public class Biblioteca extends Entorno{
    public Biblioteca(int coordenadaX, int coordenadaY, int tiempoAparicion, int probabilidadDeEsteRecurso) {
        super(coordenadaX, coordenadaY, tiempoAparicion, probabilidadDeEsteRecurso);
    }

    public void accionBiblioteca(Individuo individuo) {
        int probabilidadAnterior = individuo.getProbabilidadClonacion();
        if (probabilidadAnterior + 20 <= 100) {
            individuo.setProbabilidadClonacion(probabilidadAnterior + 20);
        } else {
            individuo.setProbabilidadClonacion(100);
        }
        subirDeTipo(individuo);
    }


    private void subirDeTipo(Individuo individuo) {
        individuo.setTipo(individuo.getTipo() + 1);
    }
}
