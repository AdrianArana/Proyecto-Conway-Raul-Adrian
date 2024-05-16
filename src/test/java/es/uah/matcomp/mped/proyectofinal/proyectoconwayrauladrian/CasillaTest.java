package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Agua;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Entorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasillaTest {

    Casilla casilla= new Casilla(2,3);

    @Test
    void getCoordenadaX() {
        assertEquals(2, casilla.getCoordenadaX());
    }

    @Test
    void setCoordenadaX() {
        casilla.setCoordenadaX(34);
        assertEquals(34, casilla.getCoordenadaX());
    }

    @Test
    void getCoordenadaY() {
        assertEquals(3, casilla.getCoordenadaY());
    }

    @Test
    void setCoordenadaY() {
        casilla.setCoordenadaY(21);
        assertEquals(21, casilla.getCoordenadaY());
    }

    @Test
    void getIndividuos() {
        Casilla casilla1= new Casilla();
        ListaEnlazada individuoslista=new ListaEnlazada();
        Individuo individuo= new Individuo(1,1,1,1,1,1,1,1,1);


        individuoslista.add(individuo);
        casilla.setIndividuos(individuoslista);

        assertEquals(1,casilla.getIndividuos().getNumeroElementos());



    }

    @Test
    void setIndividuos() {

        Casilla casilla1= new Casilla();
        ListaEnlazada individuoslista=new ListaEnlazada();
        Individuo individuo= new Individuo(1,1,1,1,1,1,1,1,1);


        individuoslista.add(individuo);
        casilla.setIndividuos(individuoslista);

        assertEquals(1,casilla.getIndividuos().getNumeroElementos());

    }

    @Test
    void getRecursos() {

        Casilla casilla1= new Casilla(1,1);
        ListaEnlazada entornoslista=new ListaEnlazada();
        Agua agua= new Agua(1,1,1);


        entornoslista.add(entornoslista);
        casilla.setRecursos(entornoslista);

        assertEquals(1,casilla.getRecursos().getNumeroElementos());
    }

    @Test
    void setRecursos() {

        Casilla casilla1= new Casilla(1,1);
        ListaEnlazada entornoslista=new ListaEnlazada();
        Agua agua= new Agua(1,1,1);


        entornoslista.add(entornoslista);
        casilla.setRecursos(entornoslista);

        assertEquals(1,casilla.getRecursos().getNumeroElementos());
    }


}