package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.IndividuoAvanzado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.IndividuoBasico;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.IndividuoNormal;

public class Biblioteca {
    public void accionBiblioteca(Individuo individuo) {
        double probabilidadAnterior = individuo.getProbabilidadClonacion();
        if (probabilidadAnterior + 0.2 <= 1) {
            individuo.setProbabilidadReproduccion(probabilidadAnterior + 0.2);
        } else {
            individuo.setProbabilidadReproduccion(1);
        }

    }

    private void subirDeTipo(Individuo individuo) {
        if (individuo.getClass() == IndividuoBasico.class){
            IndividuoNormal individuoNormal = (IndividuoNormal) individuo;//todo cambiar de tipo a un individuo
        }
        individuo=null;


    }
}
