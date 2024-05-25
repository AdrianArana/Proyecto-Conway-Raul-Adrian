package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametrosEntornoTest {

    @Test
    void getTiempoHastaDesaparicion() {
        Agua agua= new Agua(1,1,3);

        assertEquals(3,agua.getTiempoAparicion());
    }

    @Test
    void setTiempoHastaDesaparicion() {
        Montaña montaña= new Montaña(1,1,1);
        montaña.setTiempoAparicion(1212);
        assertEquals(1212, montaña.getTiempoAparicion());
    }

    @Test
    void getProbabilidadAgua() {
        Agua agua= new Agua(1,1,1);
        assertEquals(1, agua.getProbabilidadAgua());
    }

    @Test
    void setProbabilidadAgua() {
        Agua agua= new Agua(1,1,1);
        agua.setProbabilidadDeEsteRecurso(101);
        assertEquals(101, agua.getProbabilidadDeEsteRecurso());
    }

    @Test
    void getProbabilidadComida() {
        Comida comida= new Comida(1,1,1);

        assertEquals(1, comida.getProbabilidadDeEsteRecurso());
    }

    @Test
    void setProbabilidadComida() {
        Comida comida= new Comida(1,1,1);
        comida.setProbabilidadDeEsteRecurso(12);
        assertEquals(12, comida.getProbabilidadDeEsteRecurso());

    }

    @Test
    void getProbabilidadMontaña() {
        Montaña montaña= new Montaña(1,1,1);

        assertEquals(1, montaña.getProbabilidadDeEsteRecurso());
    }

    @Test
    void setProbabilidadMontaña() {
        Montaña montaña= new Montaña(2,2,2);
        montaña.setProbabilidadDeEsteRecurso(13);
        assertEquals(13, montaña.getProbabilidadDeEsteRecurso());
    }

    @Test
    void getProbabilidadTesoro() {
        Tesoro tesoro= new Tesoro(3,3,3);
        assertEquals(3, tesoro.getProbabilidadDeEsteRecurso() );
    }

    @Test
    void setProbabilidadTesoro() {
        Tesoro tesoro= new Tesoro(3,3,3);
        tesoro.setProbabilidadDeEsteRecurso(45);
        assertEquals(45, tesoro.getProbabilidadDeEsteRecurso());
    }

    @Test
    void getProbabilidadBiblioteca() {
        Biblioteca biblioteca= new Biblioteca(10,10,10);
        assertEquals(10, biblioteca.getProbabilidadDeEsteRecurso());
    }

    @Test
    void setProbabilidadBiblioteca() {
        Biblioteca biblioteca= new Biblioteca(10,10,1);
        biblioteca.setProbabilidadDeEsteRecurso(100);
        assertEquals(100, biblioteca.getProbabilidadDeEsteRecurso());

    }

    @Test
    void getProbabilidadPozo() {
        Pozo pozo= new Pozo(11,1,1);
        assertEquals(1, pozo.getProbabilidadDeEsteRecurso());
    }

    @Test
    void setProbabilidadPozo() {
        Pozo pozo= new Pozo(11,1,1);
        pozo.setProbabilidadDeEsteRecurso(89);
        assertEquals(89, pozo.getProbabilidadDeEsteRecurso());

    }
}