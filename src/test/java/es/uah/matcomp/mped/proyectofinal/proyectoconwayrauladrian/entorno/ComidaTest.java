package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComidaTest {

    @Test
    void accionComida() {
        Individuo individuo=new Individuo(2,2,2,2,2,2,2,2,2);
        Comida comida=new Comida(1,1,1);

        comida.accionComida(individuo);

        assertEquals(12, individuo.getTurnosVidaRestantes());
    }
}