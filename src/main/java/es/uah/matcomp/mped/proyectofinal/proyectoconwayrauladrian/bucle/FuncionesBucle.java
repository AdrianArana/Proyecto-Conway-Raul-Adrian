package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntorno;

import java.util.Random;

public class FuncionesBucle {

    public FuncionesBucle() {
    }

    public void recorrerCasillas(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, int turnoActual, ParametrosEntorno parametrosEntorno) {
        int filas = tablero.getNumeroFilas();
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,
                // pero REMARCAMOS QUE LOS NUMEROS DE LAS POSICIONES DE LAS CASILLAS SON DESDE EL 1 HASTA EL 10,
                // NO DESDE EL 0 AL 9 COMO AQUI, PUEDE QUE DE PROBLEMAS
                Casilla casillaActual = filaActual.getElemento(columna).getData();//Ya podemos trabajar con cada casilla
                System.out.println("CasillaActual: " + casillaActual.toString());


                reproduccion(casillaActual, turnoActual);
                //clonacion(casillaActual, turnoActual);
                muerteIndividuos(casillaActual);
                recursoActivo(casillaActual);
                //aparicionRecursos(casillaActual, parametrosEntorno);
                moverIndividuos(tablero, casillaActual);
                tiempoDeVida(casillaActual);
                //TODO si hay mas de tres individuos en una casilla que salte un error

                actualizarBotones(casillaActual);

            }
        }
    }

    private void actualizarBotones(Casilla casilla) {
        int numeroIndividuos = casilla.getIndividuos().getNumeroElementos();
        int numeroRecursos = casilla.getRecursos().getNumeroElementos();
        if (numeroIndividuos > 0) {
            if (numeroIndividuos == 1) {
                casilla.getBoton().setStyle("-fx-background-color: #ffae00;");

            } else if (numeroIndividuos == 2) {
                casilla.getBoton().setStyle("-fx-background-color: #ff4d00;");
                casilla.getBoton().setText("2");

            } else if (numeroIndividuos == 3) {
                casilla.getBoton().setStyle("-fx-background-color: #ff0000;");
                casilla.getBoton().setText("3");
            }
        } else if (numeroRecursos > 0) {
            for (int i = 0; i < numeroRecursos; i++) {
                Class<? extends Entorno> clase = casilla.getRecursos().getElemento(i).getData().getClass();
                if (clase == Pozo.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#393434FF;");
                }
                if (clase == Agua.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#1c38a5;");
                }
                if (clase == Biblioteca.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#831ca5;");
                }
                if (clase == Comida.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#8e3f00;");
                }
                if (clase == Montaña.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#ffffff;");
                }
                if (clase == Tesoro.class) {
                    casilla.getBoton().setStyle("-fx-background-color:#ffff00;");
                }
            }
        }
        if (numeroIndividuos == 0 && numeroRecursos == 0) {
            casilla.getBoton().setStyle(null);
            casilla.getBoton().setText(null);
        }
        if(numeroIndividuos==1){
            casilla.getBoton().setText("1");
        }else if(numeroIndividuos==2){
            casilla.getBoton().setText("2");
        }else if(numeroIndividuos==3){
            casilla.getBoton().setText("3");
        }
    }


    //por cada turno, todos los individuos pierden 1 vida
    //si los turnos de vida del individuo son menores o ihuales a cero, se eliminan de la casilla y de la lisat de individuos
    //a los individuos por cada turno, se les actualiza su probabilidad de reproduccion y de clonacion
    public void tiempoDeVida(Casilla casillaActual) {
        for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
            casillaActual.getIndividuos().getElemento(i).getData().setTurnosVidaRestantes(casillaActual.getIndividuos().getElemento(i).getData().getTurnosVidaRestantes() - 1);
        }

        for (int j = 0; j < casillaActual.getIndividuos().getNumeroElementos(); j++) {
            if (casillaActual.getIndividuos().getElemento(j).getData().getTurnosVidaRestantes() <= 0) {
                casillaActual.getIndividuos().delete(j);
                casillaActual.setIndividuos(casillaActual.getIndividuos());
            }
        }

        for (int k = 0; k < casillaActual.getIndividuos().getNumeroElementos(); k++) {
            if (casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion() >= 10) {
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion((casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion()) - (10));
            } else {
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion(0);
            }

        }

        for (int l = 0; l < casillaActual.getIndividuos().getNumeroElementos(); l++) {
            if (casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion() >= 10) {
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadReproduccion((casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion()) - (10));
            } else {
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadClonacion(0);
            }
        }
    }

    public void recursoActivo(Casilla casillaActual) {


        for (int i = 0; i < casillaActual.getRecursos().getNumeroElementos(); i++) {
            casillaActual.getRecursos().getElemento(i).getData().setTiempoAparicion(casillaActual.getRecursos().getElemento(i).getData().getTiempoAparicion() - 1);
        }

        for (int j = 0; j < casillaActual.getRecursos().getNumeroElementos(); j++) {
            if (casillaActual.getRecursos().getElemento(j).getData().getTiempoAparicion() <= 0) {
                casillaActual.getRecursos().delete(j);
                casillaActual.setRecursos(casillaActual.getRecursos());
            }
        }
    }


    //HACER LA FUNCION DE LOS MOVIMIENTOS DE LOS INDIVIDUOS //todo

    //Funciones para el movimiento de los individuos


    public void moverIndividuos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual) {
        for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
            Individuo individuoActual = casillaActual.getIndividuos().getElemento(i).getData();
            if (individuoActual.getTipo() == 1) {
                moverSimple(tablero, casillaActual, casillaActual.getIndividuos().getElemento(i).getData(), i);
            }
        }

    }


    protected void moverSimple(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int posicion) {
        Random random = new Random();
        int dir = random.nextInt(8);

        int x = casillaActual.getCoordenadaX();
        int y = casillaActual.getCoordenadaY();


        if (dir == 0) {
            //arriba

            individuo.setCoordenadaY(y + 1);
            casillaActual.getIndividuos().delete(posicion);
            //Accedemos a la casilla
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);

        }
        if (dir==1){
            //arriba derecha

            individuo.setCoordenadaX(x+1);
            individuo.setCoordenadaY(y+1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);

        }

        if(dir==2){
            //derecha

            individuo.setCoordenadaX(x+1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);


        }

        if (dir==3){
            //abajo derecha
            individuo.setCoordenadaX(x+1);
            individuo.setCoordenadaY(y-1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);

        }

        if (dir==4){
            //abajo

            individuo.setCoordenadaY(y-1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);
        }

        if (dir==5){
            //abajo izquierda
            individuo.setCoordenadaY(y-1);
            individuo.setCoordenadaX(x-1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);

        }

        if (dir==6){
            //izquierda

            individuo.setCoordenadaX(x-1);
            casillaActual.getIndividuos().delete(posicion);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);
        }
        if (dir==7){
            //arriba izquierda
            individuo.setCoordenadaX(x-1);
            individuo.setCoordenadaY(y+1);
            tablero.getElemento(tablero.getNumeroFilas()-y).getData().getElemento(x).getData().getIndividuos().add(individuo);
        }

    }


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
    //todo CUIDAO
    public int generarID(Casilla casillaActual) {
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();


        int id = 0;
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            if (individuos.getElemento(i).getData().getId() > id) {
                id = individuos.getElemento(i).getData().getId();
            }
        }
        id = id + 1;
        return id;


    }


    // Si 2 individuos ocupan la misma posición de la matriz, o se
    //reproducen generando otro individuo más, o mueren ambos
    //La reproducción de dos individuos del mismo tipo da lugar a otro individuo de ese tipo.
    //• La reproducción de individuos de distinto tipo da lugar a otro individuo del tipo más alto


    public void reproduccion(Casilla casillaActual, int turnoActual) {
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();

        if (individuos.getNumeroElementos() == 2) {
            Random random = new Random();
            int probabilidadAleatoria = random.nextInt(1, 101);

            Individuo individuo1 = individuos.getElemento(0).getData();
            Individuo individuo2 = individuos.getElemento(1).getData();

            if (individuo1.getProbabilidadReproduccion() >= probabilidadAleatoria ||
                    individuo2.getProbabilidadReproduccion() >= probabilidadAleatoria) {


                //genero su id
                //TODO -> cambiar valores
                Individuo hijo = new Individuo();
                hijo.setId(generarID(casillaActual));
                //su generacion

                hijo.setTurnoGeneracion(turnoActual);

                hijo.setTipo(Math.max(individuo1.getTipo(), individuo2.getTipo()));

                //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                hijo.setProbabilidadClonacion(Math.max(individuo1.getProbabilidadReproduccion(), individuo2.getProbabilidadClonacion()));
                hijo.setProbabilidadReproduccion(Math.max(individuo1.getProbabilidadReproduccion(), individuo2.getProbabilidadReproduccion()));
                hijo.setProbabilidadMuerte(Math.min(individuo1.getProbabilidadMuerte(), individuo2.getProbabilidadMuerte()));
                hijo.setTurnosVidaRestantes(Math.max(individuo1.getTurnosVidaRestantes(), individuo2.getTurnosVidaRestantes()));
                hijo.setCoordenadaX(individuo1.getCoordenadaX());
                hijo.setCoordenadaY(individuo1.getCoordenadaY());


                individuos.add(hijo);
                casillaActual.setIndividuos(individuos);
            } else {
                for (int i = 0; i < individuos.getNumeroElementos(); i++) {
                    if (individuos.getElemento(i).getData().getProbabilidadMuerte() > probabilidadAleatoria) {
                        individuos.delete(i);
                        casillaActual.setIndividuos(individuos);
                    }
                }
                System.out.println("No hay reproduccion");
            }
        } else if (individuos.getNumeroElementos() == 3) {
            System.out.println("YA HAY 3 INDIVIDUOS EN LA CELDA, IMPOSIBLE REPRODUCIRSE");//todo quitarlo
        }
    }


    //un individuo puede clonarse a sí mismo, y el clon
    //aparecerá en la misma posición del mapa.
    public void clonacion(Casilla casillaActual, int turnoActual) {

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        if (individuos.getNumeroElementos() < 3) {
            for (int i = 0; i < individuos.getNumeroElementos(); i++) {
                Random random = new Random();
                int probabilidad = random.nextInt(1, 101);
                Individuo individuo = individuos.getElemento(i).getData();
                if (probabilidad <= individuos.getElemento(i).getData().getProbabilidadClonacion()) {


                    Individuo clon = new Individuo(); //todo ver si coje bien la informacion el clon

                    //id
                    clon.setId(generarID(casillaActual));
                    //generacion
                    clon.setTurnoGeneracion(turnoActual);

                    //el tipo
                    clon.setTipo(individuos.getElemento(i).getData().getTipo());
                    //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                    clon.setProbabilidadClonacion(individuo.getProbabilidadClonacion());
                    clon.setProbabilidadReproduccion(individuo.getProbabilidadReproduccion());
                    clon.setProbabilidadMuerte(individuos.getElemento(i).getData().getProbabilidadMuerte());
                    clon.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes());
                    clon.setCoordenadaX(individuo.getCoordenadaX());
                    clon.setCoordenadaY(individuo.getCoordenadaY());


                    individuos.add(clon);
                    casillaActual.setIndividuos(individuos);

                    //el tipo
                    clon.setTipo(individuos.getElemento(i).getData().getTipo());
                    //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                    clon.setProbabilidadClonacion(individuo.getProbabilidadClonacion());
                    clon.setProbabilidadReproduccion(individuo.getProbabilidadReproduccion());
                    clon.setProbabilidadMuerte(individuos.getElemento(i).getData().getProbabilidadMuerte());
                    clon.setTurnosVidaRestantes(individuo.getTurnosVidaRestantes());
                    clon.setCoordenadaX(individuo.getCoordenadaX());
                    clon.setCoordenadaY(individuo.getCoordenadaY());
                    individuos.add(clon);
                    casillaActual.setIndividuos(individuos);
                    if (individuos.getNumeroElementos() > 3) {
                        System.out.println("MAL; CJEQUEAR");
                    }
                }
            }
        }

    }


    //individuos desaparecen cuando sus turnos de vida son menores o iguales que 1 y tambien desaparecen si caen al pozo y luego su proababilidadde muerte es 1-prob.de.reproduccion


    public void muerteIndividuos(Casilla casillaActual) {
        Random random = new Random();
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        boolean hasPozo = false;
        for (int j = 0; j < entorno.getNumeroElementos(); j++) {
            if (entorno.getElemento(j).getData().getClass() == Pozo.class) {
                hasPozo = true;
                break;
            }
        }


        for (int i=0;i<individuos.getNumeroElementos(); i++) {
            Individuo individuo = individuos.getElemento(i).getData();
            int probabilidadsobrevivir = random.nextInt(101);

            if (individuo.getTurnosVidaRestantes() < 1 || probabilidadsobrevivir < individuo.getProbabilidadMuerte() || hasPozo) {
                individuos.delete(i);
            }
        }

        casillaActual.setIndividuos(individuos);
    }



    //ME FALTA LA FUNCION DE APARICION DE RECURSOS


    //los recursos aparecen si una probabilidad de aparicion de recursos gana a la probabilidad generada aleatoriamente, si haay mas de 3 recursos en una posicion no pueden incluirse mas recursos en esta
    //se va a ir comparando la probabilidad de creacion de un nuevo recurso y la probabilidad de aparecer el recurso x, si la probabiliad de el nuevo recurso a aparecer es mayor que la probabilidad de el recurso x, entonces se crea un nuevo recurso del tipo x.


    //todo /con como lo he hecho, los reecursos saben la posicion en la que van a aparecer???????

    //todo?????????¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿por cada vez que le demos a avanzar va a haber una probabilidad distinta de si puede aparecer un nuevo recurso o no (simulando como que cada tiempo hay distintas vecees en las cuales hay mas recursos o menos) y si esta es mayor que la probabilidad de aparicion de un nuevo recurso entonces se llevaran acabo todas las probabilidades de lo demas
    public void aparicionRecursos(Casilla casillaActual, ParametrosEntorno parametrosEntorno) {

        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        int probGeneral = parametrosEntorno.getProbabilidadGeneral();

        Random random = new Random();
        int n = random.nextInt(1, 101);
        if (probGeneral >= n) {
            if (entorno.getNumeroElementos() < 3) {
                //Sumamos todos los valores de las probabilidades, generamos un número aleatorio entre el 1 y el de la suma total
                int sumatotal = parametrosEntorno.getProbabilidadAgua() + parametrosEntorno.getProbabilidadBiblioteca() +
                        parametrosEntorno.getProbabilidadComida() + parametrosEntorno.getProbabilidadMontaña() +
                        parametrosEntorno.getProbabilidadPozo() + parametrosEntorno.getProbabilidadTesoro();

                int cota1 = parametrosEntorno.getProbabilidadAgua();
                int cota2 = cota1 + parametrosEntorno.getProbabilidadBiblioteca();
                int cota3 = cota2 + parametrosEntorno.getProbabilidadComida();
                int cota4 = cota3 + parametrosEntorno.getProbabilidadPozo();
                int cota5 = cota4 + parametrosEntorno.getProbabilidadTesoro();

                Random random1 = new Random();
                int probabilidadDeNuevorecurso = random1.nextInt(1, sumatotal);

                if (probabilidadDeNuevorecurso <= cota1) {
                    Agua agua = new Agua();
                    agua.setTiempoAparicion(3);
                    entorno.add(agua);
                    casillaActual.setRecursos(entorno);
                } else if (probabilidadDeNuevorecurso <= cota2) {
                    Biblioteca biblioteca = new Biblioteca();
                    biblioteca.setTiempoAparicion(3);
                    entorno.add(biblioteca);
                    casillaActual.setRecursos(entorno);
                } else if (probabilidadDeNuevorecurso <= cota3) {
                    Comida comida = new Comida();
                    comida.setTiempoAparicion(3);
                    entorno.add(comida);
                    casillaActual.setRecursos(entorno);
                } else if (probabilidadDeNuevorecurso <= cota4) {
                    Pozo pozo = new Pozo();
                    pozo.setTiempoAparicion(3);
                    entorno.add(pozo);
                    casillaActual.setRecursos(entorno);
                } else if (probabilidadDeNuevorecurso <= cota5) {
                    Tesoro tesoro = new Tesoro();
                    tesoro.setTiempoAparicion(3);
                    entorno.add(tesoro);
                    casillaActual.setRecursos(entorno);
                } else {
                    Montaña montaña = new Montaña();
                    montaña.setTiempoAparicion(3);
                    entorno.add(montaña);
                    casillaActual.setRecursos(entorno);
                }
            }
        }
    }
}


