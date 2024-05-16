package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {

    @Test
    void accionPozo() {
        Individuo individuo= new Individuo(2,2,2,2,2,2,2,2,2);
        Pozo pozo= new Pozo(1,1,1);

        pozo.accionPozo(individuo);

        assertEquals(-1, individuo.getTurnosVidaRestantes());
    }
}