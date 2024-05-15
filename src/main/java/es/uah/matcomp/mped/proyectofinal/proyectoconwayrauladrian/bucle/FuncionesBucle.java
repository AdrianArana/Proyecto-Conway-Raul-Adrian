package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Entorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Pozo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import java.util.Random;

public class FuncionesBucle {

    public FuncionesBucle() {
    }

    public void recorrerCasillas(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        int filas = tablero.getNumeroFilas();
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,
                // pero REMARCAMOS QUE LOS NUMEROS DE LAS POSICIONES DE LAS CASILLAS SON DESDE EL 1 HASTA EL 10,
                // NO DESDE EL 0 AL 9 COMO AQUI, PUEDE QUE DE PROBLEMAS
                Casilla casillaActual = filaActual.getElemento(columna).getData();//Ya podemos trabajar con cada casilla
                System.out.println("CasillaActual: " + casillaActual.toString());
                tiempoDeVida(casillaActual);
                recursoActivo(casillaActual);
                reproduccion(casillaActual);
                clonacion(casillaActual);
                muerteIndividuos(casillaActual);
            }
        }
    }


    //todo
    public void tiempoDeVida(Casilla casillaActual) {

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            individuos.getElemento(i).getData().setTurnosVidaRestantes(individuos.getElemento(i).getData().getTurnosVidaRestantes() - 1);
        }

        for (int j = 0; j < individuos.getNumeroElementos(); j++) {
            if (individuos.getElemento(j).getData().getTurnosVidaRestantes() <= 0) {
                individuos.delete(j);
                casillaActual.setIndividuos(individuos);
            }
        }

        for (int k = 0; k < individuos.getNumeroElementos(); k++) {
            individuos.getElemento(k).getData().setProbabilidadReproduccion((individuos.getElemento(k).getData().getProbabilidadReproduccion()) - (10));
        }
        for (int l = 0; l < individuos.getNumeroElementos(); l++) {

            individuos.getElemento(l).getData().setProbabilidadClonacion(individuos.getElemento(l).getData().getProbabilidadClonacion() - (10));
        }
    }

    public void recursoActivo(Casilla casillaActual) {
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i = 0; i < entorno.getNumeroElementos(); i++) {
            entorno.getElemento(i).getData().setTiempoAparicion(entorno.getElemento(i).getData().getTiempoAparicion() - 1);
        }

        for (int j = 0; j < entorno.getNumeroElementos(); j++) {
            if (entorno.getElemento(j).getData().getTiempoAparicion() <= 0) {
                entorno.delete(j);
                casillaActual.setRecursos(entorno);
            }
        }

    }


    //HACER LA FUNCION DE LOS MOVIMIENTOS DE LOS INDIVIDUOS //todo




//hacer la funcion de mejoras del individuo
/*
    public void mejorasIndividuos(Casilla casillaActual){

        ListaEnlazada<Entorno> entorno= casillaActual.getRecursos();
        ListaEnlazada<Individuo> individuos= casillaActual.getIndividuos();


        for (int i = 0; i < entorno.getNumeroElementos(); i++) {
            for (int j = 0; j < individuos.getNumeroElementos(); j++) {
                if (entorno.getElemento(i).getData().getCelda() == individuos.getElemento(j).getData().getCelda()) {
                    recursos.getElemento(i).getData().Propiedad(individuos.getElemento(j).getData());
                    recursos.del(i);
                }
            }
        }

    }



 */

    public int generarID(Casilla casillaActual) {
        ListaEnlazada<Individuo> individuos= casillaActual.getIndividuos();


        int id = 0;
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            if(individuos.getElemento(i).getData().getId()>id){
                id=individuos.getElemento(i).getData().getId();
            }
        }
        id=id+1;
        return id;


    }

    // Si 2 individuos ocupan la misma posición de la matriz, o se
    //reproducen generando otro individuo más, o mueren ambos

    //La reproducción de dos individuos del mismo tipo da lugar a otro individuo de ese tipo.
    //• La reproducción de individuos de distinto tipo da lugar a otro individuo del tipo más alto


    public void reproduccion(Casilla casillaActual) {
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();

        if (individuos.getNumeroElementos() == 2) {
            Random random = new Random();
            int probabilidadAleatoria = random.nextInt(101);

            Individuo individuo1 = individuos.getElemento(0).getData();
            Individuo individuo2 = individuos.getElemento(1).getData();

            if (individuo1.getProbabilidadReproduccion() >= probabilidadAleatoria ||
                    individuo2.getProbabilidadReproduccion() >= probabilidadAleatoria) {

                //TODO -> cambiar valores
                Individuo hijo = new Individuo(1,1,1,1,1,1,1,1,1);
                hijo.setId(generarID(casillaActual));


                int generacion = Math.max(individuo1.getTurnoGeneracion(), individuo2.getTurnoGeneracion());
                hijo.setTurnoGeneracion(generacion);

                if (individuo1.getTipo() == individuo2.getTipo()) {
                    hijo.setTipo(individuo1.getTipo());
                }
                else {
                    hijo.setTipo(Math.max(individuo1.getTipo(), individuo2.getTipo()));
                }


                individuos.add(hijo);
                casillaActual.setIndividuos(individuos);
            }

            else {
                System.out.println("No hay reproduccion");
            }
        } else if (individuos.getNumeroElementos() == 3) {
            System.out.println("YA HAY 3 INDIVIDUOS EN LA CELDA, IMPOSIBLE REPRODUCIRSE");
        }
    }





    //un individuo puede clonarse a sí mismo, y el clon
    //aparecerá en la misma posición del mapa.
    public void clonacion(Casilla casillaActual){

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();

        for(int i=0; i<individuos.getNumeroElementos(); i++){
            Random random= new Random();
            int probabilidad= random.nextInt(101);

            if(probabilidad<=individuos.getElemento(i).getData().getProbabilidadClonacion()){


                Individuo clon= individuos.getElemento(i).getData();
                clon.setId(generarID(casillaActual));
                clon.setTurnoGeneracion(individuos.getElemento(i).getData().getTurnoGeneracion());
                individuos.add(clon);


                casillaActual.setIndividuos(individuos);

            }
        }

    }



    //individuos desaparecen cuando sus turnos de vida son menores o iguales que 1 y tambien desaparecen si caen al pozo y luego su proababilidadde muerte es 1-prob.de.reproduccion
    public void muerteIndividuos(Casilla casillaActual){
        Random random= new Random();
        int probabilidadsobrevivir= random.nextInt(101);

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i=0; i<individuos.getNumeroElementos(); i++){
            if (individuos.getElemento(i).getData().getTurnosVidaRestantes()<1){
                individuos.delete(i);
                casillaActual.setIndividuos(individuos);
            }

            else if (probabilidadsobrevivir<individuos.getElemento(i).getData().getProbabilidadMuerte()){
                individuos.delete(i);
                casillaActual.setIndividuos(individuos);

            }

            else{
                for (int j=0; j<entorno.getNumeroElementos();j++){
                    if (entorno.getElemento(j).getData().getClass()== Pozo.class){
                        individuos.delete(i);
                        casillaActual.setIndividuos(individuos);
                    }
                }

            }



        }

    }


    //ME FALTA LA FUNCION DE APARICION DE RECURSOS
}


