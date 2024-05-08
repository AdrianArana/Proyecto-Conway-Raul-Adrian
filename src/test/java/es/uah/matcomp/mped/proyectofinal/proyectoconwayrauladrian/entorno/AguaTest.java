package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AguaTest {

    @Test
    void accionAgua() {
        Individuo individuo=new Individuo(2,2,2,2,2,2,2,2,2);
        Agua agua=new Agua(1,1,3,23);

        agua.accionAgua(individuo);

        assertEquals(4,individuo.getTurnosVidaRestantes());
    }
}