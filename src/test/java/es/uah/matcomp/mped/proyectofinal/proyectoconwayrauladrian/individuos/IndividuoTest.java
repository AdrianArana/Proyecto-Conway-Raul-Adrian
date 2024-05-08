package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndividuoTest {
    Individuo individuo= new Individuo(2,2,2,2,2,2,2,2,2);

    @Test
    void getTipo() {
        assertEquals(2, individuo.getTipo());
    }

    @Test
    void setTipo() {
        individuo.setTipo(3);
        assertEquals(3, individuo.getTipo());
    }

    @Test
    void getCoordenadaX() {
        assertEquals(2, individuo.getCoordenadaX());
    }

    @Test
    void setCoordenadaX() {
        individuo.setCoordenadaX(32);
        assertEquals(32, individuo.getCoordenadaX());
    }

    @Test
    void getCoordenadaY() {
        assertEquals(2, individuo.getCoordenadaY());
    }

    @Test
    void setCoordenadaY() {
        individuo.setCoordenadaY(21);
        assertEquals(21, individuo.getCoordenadaY());
    }

    @Test
    void getId() {
        assertEquals(2, individuo.getId());
    }

    @Test
    void setId() {
        individuo.setId(98);
        assertEquals(98, individuo.getId());
    }

    @Test
    void getTurnoGeneracion() {
        assertEquals(2, individuo.getTurnoGeneracion());
    }

    @Test
    void setTurnoGeneracion() {
        individuo.setTurnoGeneracion(100);
        assertEquals(100, individuo.getTurnoGeneracion());
    }
}