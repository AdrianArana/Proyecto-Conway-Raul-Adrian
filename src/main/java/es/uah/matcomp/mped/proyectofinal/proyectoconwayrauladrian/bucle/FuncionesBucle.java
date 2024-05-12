package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;

public class FuncionesBucle {

    public void recorrerIndividuos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero){
        int filas = tablero.getNumeroFilas();
        for (int fila=0;fila<filas;fila++){
            ListaEnlazadaColumnas<Casilla> filaActual=tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas= filaActual.getNumeroColumnas();
            for (int columna=0;columna<columnas;columna++){//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,
                // pero REMARCAMOS QUE LOS NUMEROS DE LAS POSICIONES DE LAS CASILLAS SON DESDE EL 1 HASTA EL 10,
                // NO DESDE EL 0 AL 9 COMO AQUI, PUEDE QUE DE PROBLEMAS
                Casilla casillaActual=filaActual.getElemento(columna).getData();//Ya podemos trabajar con cada casilla
                System.out.println("CasillaActual: "+casillaActual.toString());
            }
        }
    }
}