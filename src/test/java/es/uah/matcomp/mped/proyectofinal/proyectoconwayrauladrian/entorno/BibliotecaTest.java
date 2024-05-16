package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    @Test
    void accionBiblioteca() {
        Individuo individuo=new Individuo(2,2,2,2,2,2,2,2,2);
        Biblioteca biblioteca= new Biblioteca(1,1,1);
        biblioteca.accionBiblioteca(individuo);

        assertEquals(22, individuo.getProbabilidadClonacion());
        assertEquals(3, individuo.getTipo());
    }

}