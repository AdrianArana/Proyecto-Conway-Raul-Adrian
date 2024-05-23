package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo;

import com.google.gson.annotations.Expose;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;

public class modeloDatosFinal {
    @Expose
    public ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    @Expose
    public ParametrosIndividuoModelProperties parametrosGuardadosIndividuos;
    @Expose
    public ParametrosEntornoModelProperties parametrosGuardadosEntorno;
    @Expose
    public ParametrosCasillasModelProperties parametrosGuardadosCasillas;

    public modeloDatosFinal(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, ParametrosIndividuoModelProperties parametrosGuardadosIndividuos, ParametrosEntornoModelProperties parametrosGuardadosEntorno, ParametrosCasillasModelProperties parametrosGuardadosCasillas) {
        this.tablero = tablero;
        this.parametrosGuardadosIndividuos = parametrosGuardadosIndividuos;
        this.parametrosGuardadosEntorno = parametrosGuardadosEntorno;
        this.parametrosGuardadosCasillas = parametrosGuardadosCasillas;
    }

    public ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> getTablero() {
        return tablero;
    }

    public ParametrosIndividuoModelProperties getParametrosGuardadosIndividuos() {
        return parametrosGuardadosIndividuos;
    }

    public ParametrosEntornoModelProperties getParametrosGuardadosEntorno() {
        return parametrosGuardadosEntorno;
    }

    public ParametrosCasillasModelProperties getParametrosGuardadosCasillas() {
        return parametrosGuardadosCasillas;
    }
}
