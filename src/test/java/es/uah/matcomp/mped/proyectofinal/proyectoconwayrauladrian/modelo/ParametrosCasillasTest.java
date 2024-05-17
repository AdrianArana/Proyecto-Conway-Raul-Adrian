package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametrosCasillasTest {
    Casilla casilla= new Casilla(2,3);

    @Test
    void getX() {
        assertEquals(2, casilla.getCoordenadaX());
    }

    @Test
    void setX() {
        casilla.setCoordenadaX(21);
        assertEquals(21, casilla.getCoordenadaX());
    }

    @Test
    void getY() {
        assertEquals(3, casilla.getCoordenadaY());
    }

    @Test
    void setY() {
        casilla.setCoordenadaY(32);
        assertEquals(32, casilla.getCoordenadaY());
    }
}