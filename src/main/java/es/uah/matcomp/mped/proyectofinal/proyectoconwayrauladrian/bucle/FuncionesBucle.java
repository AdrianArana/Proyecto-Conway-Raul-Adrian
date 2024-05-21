package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntorno;

import java.util.Random;

public class FuncionesBucle {
    //TODO- >
    /*
    No se clonan los individuos ni reproducen, creo, correctamente, ademas creo que no
    aparecen con los parametros adecuados, ya que cuando configuro numeros altos, mueren sin sentido.
    Arreglar lo del ID, no funciona nada,
    Añadir lo de los arboles,
    tambien lo de los logs
    poder mostrar los individuos que hay dentro de la casilla seleccionada
    Ademas no se mueven correctamente los individuos no se porque, ayer se movian bien
    poner los efectos de los elementos en los individuos,
    añadir el ultimo tipo de movimiento
    ajustar valores maximos y minimos de los sliders y valores iniciales
    comprobar que los individuos que se reproducen aparecen con las caracterisiticas adecuadas, creo que no
    por ultimo hacer lo de que se lea y se guarden los archivos
    si da tiempo, ajustar la velocidad del juego en la propia ventana de juego.
    */
    public FuncionesBucle() {
    }

    public void recorrerCasillas(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, int turnoActual, ParametrosEntorno parametrosEntorno) {
        int filas = tablero.getNumeroFilas();
        ListaEnlazada<ElementoLE<Individuo>> individuosMover = new ListaEnlazada<>();
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,

                mejoras((tablero.getElemento(fila).getData().getElemento(columna).getData()));
                tiempoDeVida(tablero.getElemento(fila).getData().getElemento(columna).getData());
                reproduccion(tablero,tablero.getElemento(fila).getData().getElemento(columna).getData(), turnoActual);
                clonacion(tablero,tablero.getElemento(fila).getData().getElemento(columna).getData(), turnoActual);
                muerteIndividuos(tablero.getElemento(fila).getData().getElemento(columna).getData());
                recursoActivo(tablero.getElemento(fila).getData().getElemento(columna).getData());
                aparicionRecursos(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData(), parametrosEntorno);

                //Añadimos a la lista de movimientos el individuo que corresponde, solo si hay algun individuo que mover en esa casilla


                if (hayMovimiento(tablero.getElemento(fila).getData().getElemento(columna).getData())) {
                    //Añadimos un individuo a la lista con sus nuevas coordenadas,para despues colocarlo donde le correponda
                    individuosMover.add(moverIndividuos(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData()));
                }

            }
        }

        if (!individuosMover.isVacia()) {
            for (int k = 0; k < individuosMover.getNumeroElementos(); k++) {
                if (individuosMover.getElemento(k).getData() != null) {
                    if (individuosMover.getElemento(k).getData().getData() != null) {
                        //System.out.println("" + individuosMover.getElemento(k).getData().getData().toString());
                        Individuo indivAñadir = individuosMover.getElemento(k).getData().getData();
                        //System.out.println("LO COLOCO");
                        //Las posiciones del tablero van del 0 al X-1, no del 1 al X
                        tablero.getElemento(indivAñadir.getCoordenadaX() - 1).getData().getElemento(indivAñadir.getCoordenadaY() - 1).getData().getIndividuos().add(indivAñadir);
                    }
                }
            }
        }
        for (int a = 0; a < filas; a++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(a).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int b = 0; b < columnas; b++) {//tiene que ser < o <= ???, creo que da igual, ya que solo la recorro,

                while (tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getNumeroElementos() > 3) {
                    int posicionDelMásViejo = 0;
                    int mayorEdad = 0;
                    for (int i = 0; i < tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getNumeroElementos(); i++) {
                        if (tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getElemento(i).getData().getTurnosVidaRestantes() > mayorEdad) {
                            posicionDelMásViejo = i;
                        }
                    }
                    tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().delete(posicionDelMásViejo);
                }
                actualizarBotones(tablero.getElemento(a).getData().getElemento(b).getData());
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
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#0c0c0c;");
                }
                if (clase == Agua.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a8cce;");
                }
                if (clase == Biblioteca.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#cb82de;");
                }
                if (clase == Comida.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#f5a76e;");
                }
                if (clase == Montaña.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a4716;");
                }
                if (clase == Tesoro.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#ffcf3d;");
                }
            }
        }
        if (numeroIndividuos == 0 && numeroRecursos == 0) {
            casilla.getBoton().setStyle(null);
            casilla.getBoton().setText(null);
        }
        if (numeroIndividuos == 1) {
            casilla.getBoton().setText("1");
        } else if (numeroIndividuos == 2) {
            casilla.getBoton().setText("2");
        } else if (numeroIndividuos == 3) {
            casilla.getBoton().setText("3");
        }
    }

    private boolean hayMovimiento(Casilla casillaActual) {
        if (casillaActual.getIndividuos().getNumeroElementos() != 0) {
            return true;
        } else {
            return false;
        }
    }

    //por cada turno, todos los individuos pierden 1 vida
    //si los turnos de vida del individuo son menores o ihuales a cero, se eliminan de la casilla y de la lisat de individuos
    //a los individuos por cada turno, se les actualiza su probabilidad de reproduccion y de clonacion
    public void tiempoDeVida(Casilla casillaActual) {
        //todo - > no hace absolutamente nada
        for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
            casillaActual.getIndividuos().getElemento(i).getData().restarUnoDeVida();
        }

        for (int j = 0; j < casillaActual.getIndividuos().getNumeroElementos(); j++) {
            if (casillaActual.getIndividuos().getElemento(j).getData().getTurnosVidaRestantes() <= 0) {
                casillaActual.getIndividuos().delete(j);
                casillaActual.setIndividuos(casillaActual.getIndividuos());
            }
        }

        for (int k = 0; k < casillaActual.getIndividuos().getNumeroElementos(); k++) {
            if (casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion() >= 10) {
                int nuevaProbReproduccion = (casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion()) - (10);
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion(nuevaProbReproduccion);
            } else {
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion(0);
            }

        }

        for (int l = 0; l < casillaActual.getIndividuos().getNumeroElementos(); l++) {
            if (casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion() >= 10) {
                int nuevaProbClonacion = (casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion()) - (10);
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadClonacion(nuevaProbClonacion);
            } else {
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadClonacion(0);
            }
        }
    }

    public void recursoActivo(Casilla casillaActual) {

        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i = 0; i < casillaActual.getRecursos().getNumeroElementos(); i++) {
            casillaActual.getRecursos().getElemento(i).getData().setTiempoAparicion(casillaActual.getRecursos().getElemento(i).getData().getTiempoAparicion() - 1);
        }
        try {
            for (int i = 0; i < entorno.getNumeroElementos(); i++) {
                entorno.getElemento(i).getData().setTiempoAparicion(entorno.getElemento(i).getData().getTiempoAparicion() - 1);
            }

            for (int j = 0; j < casillaActual.getRecursos().getNumeroElementos(); j++) {
                if (casillaActual.getRecursos().getElemento(j).getData().getTiempoAparicion() <= 0) {
                    casillaActual.getRecursos().delete(j);
                    casillaActual.setRecursos(casillaActual.getRecursos());
                }
            }
            for (int j = 0; j < entorno.getNumeroElementos(); j++) {
                if (entorno.getElemento(j).getData().getTiempoAparicion() <= 0) {
                    entorno.delete(j);
                    casillaActual.setRecursos(entorno);
                }
            }
            casillaActual.setRecursos(entorno);

        } catch (Exception e) {
            System.err.println("Error en el método recursoActivo: " + e.getMessage());
            e.printStackTrace();

        }


    }


    //HACER LA FUNCION DE LOS MOVIMIENTOS DE LOS INDIVIDUOS //todo

    //Funciones para el movimiento de los individuos


    public ElementoLE<Individuo> moverIndividuos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual) {
        ElementoLE<Individuo> individuo = new ElementoLE<>();
        try {
            for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
                Individuo individuoActual = casillaActual.getIndividuos().getElemento(i).getData();
                if (individuoActual.getTipo() == 1) {
                    individuo = moverSimple(tablero, casillaActual, casillaActual.getIndividuos().getElemento(i).getData(), i);
                    //Lo retornamos con su posicion ya cambiada
                } else if (individuoActual.getTipo() == 2) {
                    individuo = moverNormal(tablero, casillaActual, casillaActual.getIndividuos().getElemento(i).getData(), i);
                } else if (individuoActual.getTipo() == 3) {
                    individuo=moverAvanzado(tablero, casillaActual, casillaActual.getIndividuos().getElemento(i).getData(), i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el método moverIndividuos: " + e.getMessage());
            e.printStackTrace();
        }
        return individuo;
    }

    private ElementoLE<Individuo> moverAvanzado(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int pos) {
        ListaEnlazada<ElementoLE<Entorno>> recursos = obtenerRecursos(tablero);
        for (int a=0; a<tablero.getNumeroFilas(); a++) {
            for (int j = 0; j < tablero.getPrimero().getData().getNumeroColumnas(); j++) {

                for (int i = 0; i < recursos.getNumeroElementos(); i++) {
                    System.out.println(i);
                }
            }
        }
return  null;
    }

    private ListaEnlazada<ElementoLE<Entorno>> obtenerRecursos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        int filas = tablero.getNumeroFilas();
        ListaEnlazada<ElementoLE<Entorno>> recursos = new ListaEnlazada<>();
        //System.out.println("bucando elementps");
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {
                //System.out.println("Casilla entrada");
                //System.out.println(tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getNumeroElementos());
                if (tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getNumeroElementos() != 0) {
                    //System.out.println("Entramos a la data de los recursos de la casilla que esta vacia? " + tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().isVacia() + " y esta en las coords: " + fila + " , " + columna);
                    for (int r = 0; r < tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getNumeroElementos(); r++) {
                        //System.out.println("Casilla con recursos" + tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getElemento(r).getData());
                        recursos.add(tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getElemento(r));
                    }
                }
            }

        }
        return recursos;
    }

    protected ElementoLE<Individuo> moverNormal(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int posicion) {
        ListaEnlazada<ElementoLE<Entorno>> recursos = obtenerRecursos(tablero);
        int cantidadDeRecursos = recursos.getNumeroElementos();
        //System.out.println("cantidad de recursos: " + cantidadDeRecursos);
        if (cantidadDeRecursos > 0) {
            //System.out.println("Vamos a mover al individuo normal ");
            Random random = new Random();
            int recursoEscogido = random.nextInt(cantidadDeRecursos);
            int xIndividuo = individuo.getCoordenadaX();
            int yIndividuo = individuo.getCoordenadaY();
            for (int i = 0; i < cantidadDeRecursos; i++) {
                System.out.println(recursos.getElemento(i).getData().getData().toString() + " COORDENADAS: " + recursos.getElemento(i).getData().getData().getCoordenadaX() + "," + recursos.getElemento(i).getData().getData().getCoordenadaY());
            }
            //System.out.println("recursoEscogido: numero:" + recursoEscogido + " x:" + recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaX() + " y:" + recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaY());
            int xRecursoEscogido = recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaX();
            int yRecursoEscogido = recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaY();
            //System.out.println("Ubicacion del recurso: " + xRecursoEscogido + "," + yRecursoEscogido);
            if (xIndividuo < xRecursoEscogido) {
                xIndividuo++;
                individuo.setCoordenadaX(xIndividuo);
            }
            if (yIndividuo < yRecursoEscogido) {
                yIndividuo++;
                individuo.setCoordenadaY(yIndividuo);
            }
            if (xIndividuo > xRecursoEscogido) {
                xIndividuo--;
                individuo.setCoordenadaX(xIndividuo);
            }
            if (yIndividuo > yRecursoEscogido) {
                yIndividuo--;
                individuo.setCoordenadaY(yIndividuo);
            }
        }
        casillaActual.getIndividuos().delete(posicion);
        return new ElementoLE<Individuo>(individuo);
    }


    protected ElementoLE<Individuo> moverSimple(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int posicion) {
        Random random = new Random();
        int dir = random.nextInt(8);
        int x = casillaActual.getCoordenadaX();
        int y = casillaActual.getCoordenadaY();
        //Valores máximos, para que no se salgan nunca del tablero los individuos, al moverse
        int maxY = tablero.getNumeroFilas();
        int maxX = tablero.getPrimero().getData().getNumeroColumnas();
        if (dir == 0) {
            int newY = y + 1;
            int newX = x;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }
        if (dir == 1) {

            int newY = y + 1;
            int newX = x + 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }

        if (dir == 2) {
            int newY = y;
            int newX = x + 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }

        if (dir == 3) {
            int newY = y - 1;
            int newX = x;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }

        if (dir == 4) {
            int newY = y - 1;
            int newX = x - 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }

        if (dir == 5) {
            int newY = y;
            int newX = x - 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }

        if (dir == 6) {
            int newY = y + 1;
            int newX = x - 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);
        }
        if (dir == 7) {
            int newY = y - 1;
            int newX = x + 1;
            if (newY <= maxY && newX <= maxX) {
                individuo.setCoordenadaY(newY);
                individuo.setCoordenadaX(newX);
            }
            casillaActual.getIndividuos().delete(posicion);

        }
        return new ElementoLE<Individuo>(individuo);
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
//todo CUIDAO -- HECHO :)
    public int generarID(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        int id = 0;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getPrimero().getData().getNumeroColumnas(); j++) {
                if (tablero.getElemento(i).getData().getElemento(j) != null) {
                    //Accedemos a la casilla si no está vacía
                    for (int k=0; k<tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                        if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getId()>id){
                            id=tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getId();
                        }
                    }
                }
            }
        }
        id++;
        return id;


    }


// Si 2 individuos ocupan la misma posición de la matriz, o se
//reproducen generando otro individuo más, o mueren ambos
//La reproducción de dos individuos del mismo tipo da lugar a otro individuo de ese tipo.
//• La reproducción de individuos de distinto tipo da lugar a otro individuo del tipo más alto


    public void reproduccion(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, int turnoActual) {


        try {
            if (casillaActual.getIndividuos().getNumeroElementos() == 2) {
                Random random = new Random();
                int probabilidadAleatoria = random.nextInt(1, 101);

                Individuo individuo1 = casillaActual.getIndividuos().getElemento(0).getData();
                Individuo individuo2 = casillaActual.getIndividuos().getElemento(1).getData();
                //System.out.println("reproduccion de: " + individuo1.toString() + "y de " + individuo2.toString());
                //System.out.println("Individuo con: " + individuo1.getProbabilidadReproduccion() + " prob de reprod y el numero que ha tocado: " + probabilidadAleatoria);

                if (individuo1.getProbabilidadReproduccion() >= probabilidadAleatoria ||
                        individuo2.getProbabilidadReproduccion() >= probabilidadAleatoria) {

                    //genero su id
                    //TODO -> cambiar valores
                    Individuo hijo = new Individuo();
                    hijo.setId(generarID(tablero));
                    //su generacion

                    hijo.setTurnoGeneracion(turnoActual);

                    hijo.setTipo(Math.max(individuo1.getTipo(), individuo2.getTipo()));
                    //todo hacer con constructor
                    //su probabilidad de reproduccion y clonacion sera la misma q la del padre
                    hijo.setProbabilidadClonacion(Math.max(individuo1.getProbabilidadClonacion(), individuo2.getProbabilidadClonacion()));
                    hijo.setProbabilidadReproduccion(Math.max(individuo1.getProbabilidadReproduccion(), individuo2.getProbabilidadReproduccion()));
                    hijo.setProbabilidadMuerte(Math.min(individuo1.getProbabilidadMuerte(), individuo2.getProbabilidadMuerte()));
                    hijo.setTurnosVidaRestantes(Math.max(individuo1.getTurnosVidaRestantes(), individuo2.getTurnosVidaRestantes()));
                    hijo.setCoordenadaX(individuo1.getCoordenadaX());
                    hijo.setCoordenadaY(individuo1.getCoordenadaY());
                    //System.out.println("comprobar"+hijo);

                    casillaActual.getIndividuos().add(hijo);
                    //System.out.println("Ahora hay :" + casillaActual.getIndividuos().getNumeroElementos() + " individuos en la casilla");
                } else {
                    for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
                        if (casillaActual.getIndividuos().getElemento(i).getData().getProbabilidadMuerte() > probabilidadAleatoria) {
                            casillaActual.getIndividuos().delete(i);
                        }
                    }

                }
            } else if (casillaActual.getIndividuos().getNumeroElementos() == 3) {
                //System.out.println("YA HAY 3 INDIVIDUOS EN LA CELDA, IMPOSIBLE REPRODUCIRSE");//todo quitarlo
            }

        } catch (Exception e) {
            System.err.println("Error en el método moverIndividuos: " + e.getMessage());
            e.printStackTrace();

        }

    }


    //un individuo puede clonarse a sí mismo, y el clon
//aparecerá en la misma posición del mapa.
    public void clonacion(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, int turnoActual) {

        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        try {
            if (individuos.getNumeroElementos() < 3) {
                for (int i = 0; i < individuos.getNumeroElementos(); i++) {
                    Random random = new Random();
                    int probabilidad = random.nextInt(101);
                    if (probabilidad <= individuos.getElemento(i).getData().getProbabilidadClonacion()) {
                        if (casillaActual.getCoordenadaX() != individuos.getElemento(i).getData().getCoordenadaX()) {
                            System.out.println("LAS COORDENADAS NO COINCIDEN, ESTÁN MAL");
                        }
                        //System.out.println("individuo con: " + individuos.getElemento(i).getData().getProbabilidadClonacion() + " de prob de clonacion, y ha tocado el numero: " + probabilidad);


                        Individuo clon = new Individuo(individuos.getElemento(i).getData().getCoordenadaX(), individuos.getElemento(i).getData().getCoordenadaY(), generarID(tablero), individuos.getElemento(i).getData().getTipo(), individuos.getElemento(i).getData().getTurnosVidaRestantes(), turnoActual, individuos.getElemento(i).getData().getProbabilidadMuerte(), individuos.getElemento(i).getData().getProbabilidadClonacion(), individuos.getElemento(i).getData().getProbabilidadReproduccion()); //todo ver si coje bien la informacion el clon

                        //System.out.println("cantidad de individuos: " + casillaActual.getIndividuos().getNumeroElementos());
                        casillaActual.getIndividuos().add(clon);
                        //System.out.println("cantidad de individuos: " + casillaActual.getIndividuos().getNumeroElementos());

                        //System.out.println("hay clonacion");
                        return;
                    }
                }
            }


        } catch (Exception e) {
            System.err.println("Error en el método clonacion: " + e.getMessage());
            e.printStackTrace();

        }

    }


//individuos desaparecen cuando sus turnos de vida son menores o iguales que 1 y tambien desaparecen si caen al pozo y luego su proababilidadde muerte es 1-prob.de.reproduccion


    public void muerteIndividuos(Casilla casillaActual) {
        Random random = new Random();
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();
        boolean hayPozo = false;
        for (int j = 0; j < entorno.getNumeroElementos(); j++) {
            if (entorno.getElemento(j).getData().getClass() == Pozo.class) {
                hayPozo = true;
            }
        }
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            int probabilidadsobrevivir = random.nextInt(101);
            if (individuos.getElemento(i).getData().getTurnosVidaRestantes() < 1 || probabilidadsobrevivir < individuos.getElemento(i).getData().getProbabilidadMuerte() || hayPozo) {
                individuos.delete(i);
            }
        }
        casillaActual.setIndividuos(individuos);
    }


//ME FALTA LA FUNCION DE APARICION DE RECURSOS -- HECHO


//los recursos aparecen si una probabilidad de aparicion de recursos gana a la probabilidad generada aleatoriamente, si haay mas de 3 recursos en una posicion no pueden incluirse mas recursos en esta
//se va a ir comparando la probabilidad de creacion de un nuevo recurso y la probabilidad de aparecer el recurso x, si la probabiliad de el nuevo recurso a aparecer es mayor que la probabilidad de el recurso x, entonces se crea un nuevo recurso del tipo x.


//todo /con como lo he hecho, los reecursos saben la posicion en la que van a aparecer???????

    //todo?????????¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿por cada vez que le demos a avanzar va a haber una probabilidad distinta de si puede aparecer un nuevo recurso o no (simulando como que cada tiempo hay distintas vecees en las cuales hay mas recursos o menos) y si esta es mayor que la probabilidad de aparicion de un nuevo recurso entonces se llevaran acabo todas las probabilidades de lo demas
    public void aparicionRecursos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla
            casillaActual, ParametrosEntorno parametrosEntorno) {
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();
        int tiempoDeAparicionRecursos = parametrosEntorno.getTiempoAparicion();
        try {
            //int cantidadDeRecursos = obtenerRecursos(tablero).getNumeroElementos();
            //double numeroLimite = (tablero.getNumeroFilas() * tablero.getPrimero().getData().getNumeroColumnas()) * 0.1;
            //if (cantidadDeRecursos < numeroLimite) {
            int probGeneral = parametrosEntorno.getProbabilidadGeneral();
            //System.out.println("ProbabilidadGeneral: " + probGeneral);

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
                        Agua agua = new Agua(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(agua);
                        casillaActual.setRecursos(entorno);
                    } else if (probabilidadDeNuevorecurso <= cota2) {
                        Biblioteca biblioteca = new Biblioteca(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(biblioteca);
                        casillaActual.setRecursos(entorno);
                    } else if (probabilidadDeNuevorecurso <= cota3) {
                        Comida comida = new Comida(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(comida);
                        casillaActual.setRecursos(entorno);
                    } else if (probabilidadDeNuevorecurso <= cota4) {
                        Pozo pozo = new Pozo(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(pozo);
                        casillaActual.setRecursos(entorno);
                    } else if (probabilidadDeNuevorecurso <= cota5) {
                        Tesoro tesoro = new Tesoro(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(tesoro);
                        casillaActual.setRecursos(entorno);
                    } else {
                        Montaña montaña = new Montaña(casillaActual.getCoordenadaX(), casillaActual.getCoordenadaY(), tiempoDeAparicionRecursos);
                        entorno.add(montaña);
                        casillaActual.setRecursos(entorno);
                    }
                }
            }


        } catch (Exception e) {
            System.err.println("Error en el método Aaparicion Recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void mejoras(Casilla casillaActual){
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i=0; i<individuos.getNumeroElementos();i++){

            for (int j = 0; j < entorno.getNumeroElementos(); j++) {

                if (entorno.getElemento(j).getData().getClass() == Agua.class) {
                    Agua.accionAgua(individuos.getElemento(i).getData());
                    entorno.delete(j);
                }

                else if (entorno.getElemento(j).getData().getClass()==Comida.class){
                    Comida.accionComida(individuos.getElemento(i).getData());
                    entorno.delete(j);
                }

                else if(entorno.getElemento(j).getData().getClass()==Biblioteca.class){
                    Biblioteca.accionBiblioteca(individuos.getElemento(i).getData());
                    entorno.delete(j);
                }

                //entonces para que te sirve poder añadir mas de 1 agua en la configuracuion?

                else if(entorno.getElemento(j).getData().getClass()==Montaña.class){
                    Montaña.accionMontaña(individuos.getElemento(i).getData());
                    entorno.delete(j);
                }

                else if(entorno.getElemento(j).getData().getClass()==Tesoro.class){
                    Tesoro.accionTesoro(individuos.getElemento(i).getData());
                    entorno.delete(j);
                }

                casillaActual.setRecursos(entorno);
            }

        }

    }


}


