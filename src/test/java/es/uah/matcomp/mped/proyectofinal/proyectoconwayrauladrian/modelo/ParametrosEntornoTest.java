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
        ParametrosEntorno entorno=new ParametrosEntorno(1,1,1,1,1,1,1,1
        );
        assertEquals(1,entorno.getProbabilidadAgua());
    }

    @Test
    void setProbabilidadAgua() {
        ParametrosEntorno entorno=new ParametrosEntorno(1,1,1,1,1,1,1,1
        );
        entorno.setProbabilidadAgua(21);
        assertEquals(21,entorno.getProbabilidadAgua());
    }

    @Test
    void getProbabilidadComida() {

        ParametrosEntorno entorno=new ParametrosEntorno(2,2,2,2,2,2,2,2
        );
        assertEquals(2,entorno.getProbabilidadComida());
    }

    @Test
    void setProbabilidadComida() {

        ParametrosEntorno entorno=new ParametrosEntorno(2,2,2,2,2,2,2,2
        );
        entorno.setProbabilidadComida(21);
        assertEquals(21,entorno.getProbabilidadComida());

    }

    @Test
    void getProbabilidadMontaña() {

        ParametrosEntorno entorno=new ParametrosEntorno(3,3,3,3,3,3,3,3
        );
        assertEquals(3,entorno.getProbabilidadMontaña());

    }

    @Test
    void setProbabilidadMontaña() {
        ParametrosEntorno entorno=new ParametrosEntorno(3,3,3,3,3,3,3,3
        );
        entorno.setProbabilidadMontaña(44);
        assertEquals(44,entorno.getProbabilidadMontaña());

    }

    @Test
    void getProbabilidadTesoro() {
        ParametrosEntorno entorno=new ParametrosEntorno(5,5,5,5,5,5,5,5
        );

        assertEquals(5,entorno.getProbabilidadMontaña());
    }

    @Test
    void setProbabilidadTesoro() {
        ParametrosEntorno entorno=new ParametrosEntorno(3,3,3,3,3,3,3,3
        );
        entorno.setProbabilidadTesoro(44);
        assertEquals(44,entorno.getProbabilidadTesoro());

    }

    @Test
    void getProbabilidadBiblioteca() {
        ParametrosEntorno entorno=new ParametrosEntorno(6,6,6,6,6,6,6,6
        );

        assertEquals(6,entorno.getProbabilidadBiblioteca());

    }

    @Test
    void setProbabilidadBiblioteca() {
        ParametrosEntorno entorno=new ParametrosEntorno(3,3,3,3,3,3,3,3
        );
        entorno.setProbabilidadTesoro(44);
        assertEquals(44,entorno.getProbabilidadTesoro());


    }

    @Test
    void getProbabilidadPozo() {
        ParametrosEntorno entorno=new ParametrosEntorno(8,8,8,8,8,8,8,8
        );
        assertEquals(8,entorno.getProbabilidadPozo());

    }

    @Test
    void setProbabilidadPozo() {
        ParametrosEntorno entorno=new ParametrosEntorno(3,3,3,3,3,3,3,3
        );
        entorno.setProbabilidadPozo(44);
        assertEquals(44,entorno.getProbabilidadPozo());

    }
}