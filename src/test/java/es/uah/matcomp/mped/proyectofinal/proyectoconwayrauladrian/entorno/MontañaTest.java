package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontañaTest {

    @Test
    void accionMontaña() {
        Individuo individuo= new Individuo(3,1,1,1,1,1,1,1,1);
        Montaña montaña= new Montaña(1,1,1,1);

        montaña.accionMontaña(individuo);

        assertEquals(1, individuo.getTurnosVidaRestantes());
    }
}