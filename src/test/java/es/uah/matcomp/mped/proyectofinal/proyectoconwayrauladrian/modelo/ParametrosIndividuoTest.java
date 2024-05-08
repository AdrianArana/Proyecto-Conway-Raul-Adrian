package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametrosIndividuoTest {

    Individuo individuo= new Individuo(1,23,29,10,1,1,1,1,10);
    @Test
    void getTurnosVidaRestantes() {
        assertEquals(1, individuo.getTurnosVidaRestantes());
    }

    @Test
    void setTurnosVidaRestantes() {
        individuo.setTurnosVidaRestantes(80);
        assertEquals(80, individuo.getTurnosVidaRestantes());
    }


    @Test
    void getProbabilidadMuerte() {
        assertEquals(23, individuo.getProbabilidadMuerte());
    }

    @Test
    void setProbabilidadMuerte() {
        individuo.setProbabilidadMuerte(0);
        assertEquals(0, individuo.getProbabilidadMuerte());
    }

    @Test
    void getProbabilidadClonacion() {
        assertEquals(29, individuo.getProbabilidadClonacion());
    }

    @Test
    void setProbabilidadClonacion() {
        individuo.setProbabilidadClonacion(30);
        assertEquals(30, individuo.getProbabilidadClonacion());
    }

    @Test
    void getProbabilidadReproduccion() {
        assertEquals(10, individuo.getProbabilidadReproduccion());
    }

    @Test
    void setProbabilidadReproduccion() {
        individuo.setProbabilidadReproduccion(21);
        assertEquals(21, individuo.getProbabilidadReproduccion());
    }
}