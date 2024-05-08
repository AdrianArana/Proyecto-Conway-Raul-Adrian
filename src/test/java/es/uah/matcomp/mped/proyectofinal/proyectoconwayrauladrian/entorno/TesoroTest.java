package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoroTest {

    @Test
    void accionTesoro() {
        Individuo individuo= new Individuo(2,2,2,2,2,2,2,2,2);
        Tesoro tesoro= new Tesoro(1,1,1,1);
        tesoro.accionTesoro(individuo);
        assertEquals(12, individuo.getProbabilidadReproduccion());
    }
}