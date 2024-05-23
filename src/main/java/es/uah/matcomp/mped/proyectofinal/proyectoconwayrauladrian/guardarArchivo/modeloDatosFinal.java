package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo;

import com.google.gson.annotations.Expose;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;

public class modeloDatosFinal {
    @Expose
    public String nombreGuardadoString;
    @Expose
    public ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    @Expose
    public ParametrosIndividuo parametrosGuardadosIndividuos;
    @Expose
    public ParametrosEntorno parametrosGuardadosEntorno;
    @Expose
    public ParametrosCasillas parametrosGuardadosCasillas;

    public modeloDatosFinal(String nombreGuardado,ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, ParametrosIndividuo parametrosGuardadosIndividuos, ParametrosEntorno parametrosGuardadosEntorno, ParametrosCasillas parametrosGuardadosCasillas) {
        this.nombreGuardadoString=nombreGuardado;
        this.tablero = tablero;
        this.parametrosGuardadosIndividuos = parametrosGuardadosIndividuos;
        this.parametrosGuardadosEntorno = parametrosGuardadosEntorno;
        this.parametrosGuardadosCasillas = parametrosGuardadosCasillas;
    }


    public String getNombreGuardadoString() {
        return nombreGuardadoString;
    }

    public ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> getTablero() {
        return tablero;
    }

    public ParametrosIndividuo getParametrosGuardadosIndividuos() {
        return parametrosGuardadosIndividuos;
    }

    public ParametrosEntorno getParametrosGuardadosEntorno() {
        return parametrosGuardadosEntorno;
    }

    public ParametrosCasillas getParametrosGuardadosCasillas() {
        return parametrosGuardadosCasillas;
    }
}
