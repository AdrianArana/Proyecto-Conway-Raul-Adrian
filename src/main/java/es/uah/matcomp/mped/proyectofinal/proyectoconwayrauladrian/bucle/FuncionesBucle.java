package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol.Arbol;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol.Nodo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;
import javafx.scene.paint.Color;

import java.util.Random;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;
import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.valoresAjustables.*;

public class FuncionesBucle {
    public static Cola<Individuo> colaIndividuos = new Cola<>();
    public static int numeroMutaciones = 0;
    public static int numeroReproducciones = 0;
    public static Individuo individuoMayorEsperanzaDeVida;
    private int mayorEsperanzaDeVida;
//TODO FALTA ARREGLAR QUE SE SUME 1 PARA CANTIDAD REPRODUCCIONES, CANTIDAD AGUA CONSUMIDA Y CANTIDAD CLONACIONES
    //TODO que el individuo ganador funcione

    public void recorrerCasillas(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, int turnoActual, ParametrosEntorno parametrosEntorno, ParametrosIndividuo parametrosIndividuo, String colorBordes) {
        log.info("Se realiza el recorrido de casillas.");
        int filas = tablero.getNumeroFilas();
        ListaEnlazada<ElementoLE<Individuo>> individuosMover = new ListaEnlazada<>();
        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {
                momentoMayorEsperanzaDeVida(tablero.getElemento(fila).getData().getElemento(columna).getData());
                quitarObjetivos(tablero.getElemento(fila).getData().getElemento(columna).getData());

                mejoras((tablero.getElemento(fila).getData().getElemento(columna).getData()));
                tiempoDeVida(tablero.getElemento(fila).getData().getElemento(columna).getData());
                reproduccion(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData(), turnoActual, parametrosIndividuo);
                clonacion(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData(), turnoActual, parametrosIndividuo);
                muerteIndividuos(tablero.getElemento(fila).getData().getElemento(columna).getData());
                recursoActivo(tablero.getElemento(fila).getData().getElemento(columna).getData());
                aparicionRecursos(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData(), parametrosEntorno);
                //Añadimos a la lista de movimientos el individuo que corresponde, solo si hay algun individuo que mover en esa casilla
                if (hayMovimiento(tablero.getElemento(fila).getData().getElemento(columna).getData())) {
                    //Añadimos un individuo a la lista con sus nuevas coordenadas,para despues colocarlo donde le correponda
                    individuosMover.add(moverIndividuos(tablero, tablero.getElemento(fila).getData().getElemento(columna).getData()));
                }
                actualizarBotones(tablero.getElemento(fila).getData().getElemento(columna).getData(), colorBordes);
            }
        }

        if (!individuosMover.isVacia()) {
            for (int k = 0; k < individuosMover.getNumeroElementos(); k++) {
                if (individuosMover.getElemento(k).getData() != null) {
                    if (individuosMover.getElemento(k).getData().getData() != null) {
                        Individuo indivAñadir = individuosMover.getElemento(k).getData().getData();
                        tablero.getElemento(indivAñadir.getCoordenadaX() - 1).getData().getElemento(indivAñadir.getCoordenadaY() - 1).getData().getIndividuos().add(indivAñadir);
                    }
                }
            }
        }
        for (int a = 0; a < filas; a++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(a).getData();//Obtenemos la lista de elementos que se encuentra en la fila actual
            int columnas = filaActual.getNumeroColumnas();
            for (int b = 0; b < columnas; b++) {
                //Matamos el exceso de individuos en caso de que haya
                while (tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getNumeroElementos() > cantidadMaximaIndividuosPorCelda) {
                    int posicionDelMásViejo = 0;
                    int mayorEdad = 0;
                    for (int i = 0; i < tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getNumeroElementos(); i++) {
                        if (tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getElemento(i).getData().getTurnosVidaRestantes() > mayorEdad) {
                            posicionDelMásViejo = i;
                        }
                    }
                    colaIndividuos.push(tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().getElemento(posicionDelMásViejo).getData());
                    tablero.getElemento(a).getData().getElemento(b).getData().getIndividuos().delete(posicionDelMásViejo);

                }
                actualizarBotones(tablero.getElemento(a).getData().getElemento(b).getData(), colorBordes);
            }
        }
    }

    private void momentoMayorEsperanzaDeVida(Casilla casillaActual) {
        for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
            if (casillaActual.getIndividuos().getElemento(i).getData().getTurnosVidaRestantes() > mayorEsperanzaDeVida) {
                mayorEsperanzaDeVida = casillaActual.getIndividuos().getElemento(i).getData().getTurnosVidaRestantes();
                individuoMayorEsperanzaDeVida = casillaActual.getIndividuos().getElemento(i).getData();
            }
        }
    }


    private void quitarObjetivos(Casilla casilla) {
        if (casilla.getIndividuos().getNumeroElementos() != 0) {
            for (int i = 0; i < casilla.getIndividuos().getNumeroElementos(); i++) {
                if (casilla.getIndividuos().getElemento(i).getData().getObjetivo() != null) {
                    int xIndividuo = casilla.getIndividuos().getElemento(i).getData().getCoordenadaX();
                    int yIndividuo = casilla.getIndividuos().getElemento(i).getData().getCoordenadaY();
                    int xObjetivo = casilla.getIndividuos().getElemento(i).getData().getObjetivo().getData().getCoordenadaX();
                    int yObjetivo = casilla.getIndividuos().getElemento(i).getData().getObjetivo().getData().getCoordenadaY();
                    if (xObjetivo == xIndividuo && yObjetivo == yIndividuo) {
                        casilla.getIndividuos().getElemento(i).getData().setObjetivo(null);
                        casilla.getIndividuos().getElemento(i).getData().getCola().push("El individuo ha alcanzado su objetivo\n");
                    }
                }
            }
        }
    }

    private void actualizarBotones(Casilla casilla, String color) {
        int numeroIndividuos = casilla.getIndividuos().getNumeroElementos();
        int numeroRecursos = casilla.getRecursos().getNumeroElementos();
        if (numeroIndividuos > 0) {
            if (numeroIndividuos == 1) {
                casilla.getBoton().setStyle("-fx-background-color: #ffae00;-fx-border-color: " + color + ";");
                casilla.getBoton().setText("1");
                casilla.getBoton().setTextFill(Color.BLACK);
            } else if (numeroIndividuos == 2) {
                casilla.getBoton().setStyle("-fx-background-color: #ff4d00;-fx-border-color: " + color + ";");
                casilla.getBoton().setText("2");
                casilla.getBoton().setTextFill(Color.BLACK);
            } else if (numeroIndividuos == 3) {
                casilla.getBoton().setStyle("-fx-background-color: #ff0000;-fx-border-color: " + color + ";");
                casilla.getBoton().setTextFill(Color.BLACK);
                casilla.getBoton().setText("3");
            } else if (numeroIndividuos > 3) {
                casilla.getBoton().setStyle("-fx-background-color: #5a0000;-fx-border-color: " + color + ";");
                casilla.getBoton().setTextFill(Color.WHITE);
                casilla.getBoton().setText("" + numeroIndividuos);
            }
        } else if (numeroRecursos > 0) {
            for (int i = 0; i < numeroRecursos; i++) {
                Class<? extends Entorno> clase = casilla.getRecursos().getElemento(i).getData().getClass();
                if (clase == Pozo.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#0c0c0c;-fx-border-color: " + color + ";");
                }
                if (clase == Agua.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a8cce;-fx-border-color: " + color + ";");
                }
                if (clase == Biblioteca.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#cb82de;-fx-border-color: " + color + ";");
                }
                if (clase == Comida.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#f5a76e;-fx-border-color: " + color + ";");
                }
                if (clase == Montaña.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a4716;-fx-border-color: " + color + ";");
                }
                if (clase == Tesoro.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#ffcf3d;-fx-border-color: " + color + ";");
                }
            }
        }
        if (numeroIndividuos == 0 && numeroRecursos == 0) {
            casilla.getBoton().setStyle("-fx-background-color:null;-fx-border-color: " + color + ";");
            casilla.getBoton().setText(null);
        }
    }

    private boolean hayMovimiento(Casilla casillaActual) {
        if (casillaActual.getIndividuos().getNumeroElementos() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void tiempoDeVida(Casilla casillaActual) {
        log.info("Entrando al método de revisión de los turnos de vida restantes de los individuos");
        for (int i = 0; i < casillaActual.getIndividuos().getNumeroElementos(); i++) {
            casillaActual.getIndividuos().getElemento(i).getData().restarUnoDeVida();
            int turnosAnteriores = (casillaActual.getIndividuos().getElemento(i).getData().getTurnosVividos());
            int nuevoValorVida = turnosAnteriores + (1);
            casillaActual.getIndividuos().getElemento(i).getData().setTurnosVividos(nuevoValorVida);
            casillaActual.getIndividuos().getElemento(i).getData().getCola().push("Al individuo se le ha sumado 1 turno vivido" + '\n');

        }

        for (int j = 0; j < casillaActual.getIndividuos().getNumeroElementos(); j++) {
            if (casillaActual.getIndividuos().getElemento(j).getData().getTurnosVidaRestantes() <= 0) {
                casillaActual.getIndividuos().getElemento(j).getData().getCola().push("El individuo ha muerto." + '\n');
                colaIndividuos.push(casillaActual.getIndividuos().getElemento(j).getData());
                casillaActual.getIndividuos().delete(j);
                casillaActual.setIndividuos(casillaActual.getIndividuos());

            }
        }

        for (int k = 0; k < casillaActual.getIndividuos().getNumeroElementos(); k++) {
            if (casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion() >= probabilidadReproduccionRestadaPorTurno) {
                int nuevaProbReproduccion = (casillaActual.getIndividuos().getElemento(k).getData().getProbabilidadReproduccion()) - (probabilidadReproduccionRestadaPorTurno);
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion(nuevaProbReproduccion);
                casillaActual.getIndividuos().getElemento(k).getData().getCola().push("Al individuo se le ha restado " + probabilidadReproduccionRestadaPorTurno + " de probabilidad de reproducción" + '\n');

            } else {
                casillaActual.getIndividuos().getElemento(k).getData().getCola().push("Al individuo se le ha restado " + probabilidadReproduccionRestadaPorTurno + " de probabilidad de reproducción, y ha acabado en 0" + '\n');
                casillaActual.getIndividuos().getElemento(k).getData().setProbabilidadReproduccion(0);
            }

        }

        for (int l = 0; l < casillaActual.getIndividuos().getNumeroElementos(); l++) {
            if (casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion() >= probabilidadClonacionRestadaPorTurno) {
                int nuevaProbClonacion = (casillaActual.getIndividuos().getElemento(l).getData().getProbabilidadClonacion()) - (probabilidadClonacionRestadaPorTurno);
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadClonacion(nuevaProbClonacion);
                casillaActual.getIndividuos().getElemento(l).getData().getCola().push("Al individuo se le ha restado " + probabilidadClonacionRestadaPorTurno + " de probabilidad de clonación" + '\n');
            } else {
                casillaActual.getIndividuos().getElemento(l).getData().getCola().push("Al individuo se le ha restado " + probabilidadClonacionRestadaPorTurno + " de probabilidad de clonación, y ha acabado en 0" + '\n');
                casillaActual.getIndividuos().getElemento(l).getData().setProbabilidadClonacion(0);
            }
        }
    }

    public void recursoActivo(Casilla casillaActual) {
        log.info("Entrando al método de revisión de recursos activos");
        try {
            for (int i = 0; i < casillaActual.getRecursos().getNumeroElementos(); i++) {
                casillaActual.getRecursos().getElemento(i).getData().setTiempoAparicion(casillaActual.getRecursos().getElemento(i).getData().getTiempoAparicion() - 1);
            }

            for (int j = 0; j < casillaActual.getRecursos().getNumeroElementos(); j++) {
                if (casillaActual.getRecursos().getElemento(j).getData().getTiempoAparicion() <= 0) {
                    casillaActual.getRecursos().delete(j);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el método recursoActivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //FUNCIONES DE MOVIMIENTO DE LOS INDIVIDUOS

    public ElementoLE<Individuo> moverIndividuos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual) {
        log.info("Entrando al método de revisión del movimiento de los individuos");
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
                    individuo = moverAvanzado(tablero, casillaActual, casillaActual.getIndividuos().getElemento(i).getData(), i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el método moverIndividuos: " + e.getMessage());
            e.printStackTrace();
        }
        return individuo;
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
        individuo.getCola().push("El individuo se ha movido de la casilla: (" + casillaActual.getCoordenadaX() + "," + casillaActual.getCoordenadaY() + ")" + " a la casillla: (" + individuo.getCoordenadaX() + "," + individuo.getCoordenadaY() + ")" + '\n');
        return new ElementoLE<Individuo>(individuo);
    }

    protected ElementoLE<Individuo> moverNormal(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int posicion) {
        ListaEnlazada<ElementoLE<Entorno>> recursos = obtenerRecursos(tablero);
        int cantidadDeRecursos = recursos.getNumeroElementos();
        if (cantidadDeRecursos > 0) {
            Random random = new Random();
            int recursoEscogido = random.nextInt(cantidadDeRecursos);
            int xIndividuo = individuo.getCoordenadaX();
            int yIndividuo = individuo.getCoordenadaY();
            int xRecursoEscogido = recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaX();
            int yRecursoEscogido = recursos.getElemento(recursoEscogido).getData().getData().getCoordenadaY();

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
            individuo.getCola().push("El individuo se ha movido de la casilla: (" + casillaActual.getCoordenadaX() + "," + casillaActual.getCoordenadaY() + ")" + " a la casillla: (" + individuo.getCoordenadaX() + "," + individuo.getCoordenadaY() + ")" + '\n');

        }
        casillaActual.getIndividuos().delete(posicion);
        return new ElementoLE<Individuo>(individuo);
    }

    protected ElementoLE<Individuo> moverAvanzado(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, Individuo individuo, int pos) {
        ListaEnlazada<ElementoLE<Entorno>> recursos = obtenerRecursos(tablero);

        double menorDistancia = 100000000.0;
        //Obtenemos la menor distancia de todos los elementos del tablero respecto a nuestro individuo
        if (individuo.getObjetivo() == null) {
            for (int i = 0; i < recursos.getNumeroElementos(); i++) {
                if (Math.sqrt(Math.pow((recursos.getElemento(i).getData().getData().getCoordenadaX() - individuo.getCoordenadaX()), 2) +
                        Math.pow((recursos.getElemento(i).getData().getData().getCoordenadaY() - individuo.getCoordenadaY()), 2)) < menorDistancia) {
                    individuo.setObjetivo(recursos.getElemento(i).getData());
                    menorDistancia = Math.sqrt(Math.pow((recursos.getElemento(i).getData().getData().getCoordenadaX() - individuo.getCoordenadaX()), 2) +
                            Math.pow((recursos.getElemento(i).getData().getData().getCoordenadaY() - individuo.getCoordenadaY()), 2));
                }
            }
        }
        if (individuo.getObjetivo() != null) {
            //Coordenadas del individuo
            int xIndividuo = individuo.getCoordenadaX();
            int yIndividuo = individuo.getCoordenadaY();
            //Coordenadas del recurso objetivo
            int xRecursoEscogido = individuo.getObjetivo().getData().getCoordenadaX();
            int yRecursoEscogido = individuo.getObjetivo().getData().getCoordenadaY();
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
        individuo.getCola().push("El individuo se ha movido de la casilla: (" + casillaActual.getCoordenadaX() + "," + casillaActual.getCoordenadaY() + ")" + " a la casillla: (" + individuo.getCoordenadaX() + "," + individuo.getCoordenadaY() + ")" + '\n');
        casillaActual.getIndividuos().delete(pos);
        return new ElementoLE<Individuo>(individuo);
    }

    //Función usada en el método de mover individuos avanzados, para poder "escanear" los recursos del tablero
    private ListaEnlazada<ElementoLE<Entorno>> obtenerRecursos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        log.info("Entrando al método de revisión de obtencion de recursos");
        int filas = tablero.getNumeroFilas();
        ListaEnlazada<ElementoLE<Entorno>> recursos = new ListaEnlazada<>();

        for (int fila = 0; fila < filas; fila++) {
            ListaEnlazadaColumnas<Casilla> filaActual = tablero.getElemento(fila).getData();
            int columnas = filaActual.getNumeroColumnas();
            for (int columna = 0; columna < columnas; columna++) {
                if (tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getNumeroElementos() != 0) {
                    for (int r = 0; r < tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getNumeroElementos(); r++) {
                        recursos.add(tablero.getElemento(fila).getData().getElemento(columna).getData().getRecursos().getElemento(r));
                    }
                }
            }
        }
        return recursos;
    }

    //FUNCIÓN PARA EL ID DE LOS INDIVIDUOS GENERADOS
    public int generarID(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        log.info("Entrando al método de generarID");
        int id = 0;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getPrimero().getData().getNumeroColumnas(); j++) {
                if (tablero.getElemento(i).getData().getElemento(j) != null) {
                    //Accedemos a la casilla si no está vacía
                    for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                        if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getId() > id) {
                            id = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getId();
                        }
                    }
                }
            }
        }
        for (int j = 0; j < colaIndividuos.getDatos().getNumeroElementos(); j++) {
            if (colaIndividuos.getDatos().getElemento(j).getData().getId() > id) {
                id = colaIndividuos.getDatos().getElemento(j).getData().getId();
            }
        }
        id++;
        return id;
    }

    //----OTRAS FUNCIONES PARA EL BUCLE----

    //INDIVIDUOS

    public void reproduccion(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, int turnoActual, ParametrosIndividuo parametrosIndividuo) {
        log.info("Entrando al método de reproduccion de individuos");
        try {
            if (casillaActual.getIndividuos().getNumeroElementos() < cantidadMaximaIndividuosPorCelda && casillaActual.getIndividuos().getNumeroElementos() > 1) {
                Random random = new Random();
                int probabilidadAleatoria = random.nextInt(1, 101);
                Individuo individuo1 = casillaActual.getIndividuos().getElemento(0).getData();
                Individuo individuo2 = casillaActual.getIndividuos().getElemento(1).getData();

                if (individuo1.getProbabilidadReproduccion() >= probabilidadAleatoria ||
                        individuo2.getProbabilidadReproduccion() >= probabilidadAleatoria) {
                    Individuo hijo = new Individuo(individuo1.getCoordenadaX(), individuo1.getCoordenadaY(), generarID(tablero), Math.max(individuo1.getTipo(), individuo2.getTipo()), parametrosIndividuo.getTurnosVidaRestantes(), turnoActual, Math.min(individuo1.getProbabilidadMuerte(), individuo2.getProbabilidadMuerte()), Math.max(individuo1.getProbabilidadClonacion(), individuo2.getProbabilidadClonacion()), Math.max(individuo1.getProbabilidadReproduccion(), individuo2.getProbabilidadReproduccion()));
                    casillaActual.getIndividuos().add(hijo);
                    hijo.setArbolDelIndividuo(new Arbol<Individuo>(new Nodo<Individuo>(hijo), new Nodo<Individuo>(individuo1), new Nodo<Individuo>(individuo2)));
                    individuo1.getCola().push("El individuo " + individuo1 + " junto con " + individuo2 + " han tenido un hijo/a" + '\n');
                    int numeroReproduccionesAnteriores = (casillaActual.getIndividuos().getElemento(0).getData().getNumeroDeReproducciones());
                    int nuevoNumeroReproducciones = numeroReproduccionesAnteriores + (1);
                    casillaActual.getIndividuos().getElemento(0).getData().setNumeroDeReproducciones(nuevoNumeroReproducciones);
                    int numeroReproduccionesAnteriores2 = (casillaActual.getIndividuos().getElemento(1).getData().getNumeroDeReproducciones());
                    int nuevoNumeroReproducciones2 = numeroReproduccionesAnteriores2 + (1);
                    casillaActual.getIndividuos().getElemento(0).getData().setNumeroDeReproducciones(nuevoNumeroReproducciones2);
                    numeroReproducciones++;
                } else {
                    //De no reproducirse, puede morir uno de ellos
                    if (casillaActual.getIndividuos().getElemento(0).getData().getProbabilidadMuerte() > probabilidadAleatoria) {
                        casillaActual.getIndividuos().delete(0);
                        individuo1.getCola().push("El individuo " + individuo1 + " ha muerto" + '\n');
                        return;
                    }
                    if (casillaActual.getIndividuos().getElemento(1).getData().getProbabilidadMuerte() > probabilidadAleatoria) {
                        casillaActual.getIndividuos().delete(1);
                        individuo2.getCola().push("El individuo " + individuo2 + " ha muerto" + '\n');
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error en el método reproducción: " + e.getMessage());
            e.printStackTrace();

        }

    }

    public void clonacion(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, int turnoActual, ParametrosIndividuo parametrosIndividuo) {

        log.info("Entrando al método de clonacion de individuos");
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        try {
            if (individuos.getNumeroElementos() < cantidadMaximaIndividuosPorCelda) {
                for (int i = 0; i < individuos.getNumeroElementos(); i++) {
                    Random random = new Random();
                    int probabilidad = random.nextInt(1, 101);
                    if (probabilidad <= individuos.getElemento(i).getData().getProbabilidadClonacion()) {
                        Individuo clon = new Individuo(individuos.getElemento(i).getData().getCoordenadaX(), individuos.getElemento(i).getData().getCoordenadaY(), generarID(tablero), individuos.getElemento(i).getData().getTipo(), parametrosIndividuo.getTurnosVidaRestantes(), turnoActual, individuos.getElemento(i).getData().getProbabilidadMuerte(), individuos.getElemento(i).getData().getProbabilidadClonacion(), individuos.getElemento(i).getData().getProbabilidadReproduccion());

                        clon.setArbolDelIndividuo(new Arbol<Individuo>(new Nodo<Individuo>(clon), new Nodo<Individuo>(individuos.getElemento(i).getData()), null));
                        clon.setArbolDelIndividuo(individuos.getElemento(i).getData().getArbolDelIndividuo());
                        casillaActual.getIndividuos().add(clon);
                        individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " se ha clonado" + '\n');
                        clon.getCola().push("El individuo " + individuos.getElemento(i).getData() + " se ha clonado y ha dado al clon: " + clon + "numero de mutaciones" + '\n');
                        numeroMutaciones++;
                        int numeroClonacionesAnterior = (casillaActual.getIndividuos().getElemento(i).getData().getNumeroDeClonaciones());
                        int nuevoNumeroClonaciones = numeroClonacionesAnterior + (1);
                        casillaActual.getIndividuos().getElemento(0).getData().setNumeroDeClonaciones(nuevoNumeroClonaciones);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error en el método clonacion: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void muerteIndividuos(Casilla casillaActual) {
        log.info("Entrando al método de muerte de individuos");
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
                individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " ha muerto, cayéndose a un pozo\n");
                individuos.delete(i);

            }
        }
        casillaActual.setIndividuos(individuos);
    }

    //RECURSOS

    public void aparicionRecursos(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero, Casilla casillaActual, ParametrosEntorno parametrosEntorno) {
        log.info("Entrando al método de aparicion de recursos");
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();
        int tiempoDeAparicionRecursos = parametrosEntorno.getTiempoAparicion();
        try {
            int probGeneral = parametrosEntorno.getProbabilidadGeneral();
            Random random = new Random();
            int n = random.nextInt(1, 101);
            if (probGeneral >= n) {
                if (entorno.getNumeroElementos() < cantidadMaximaRecursosPorCelda) {
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

    public void mejoras(Casilla casillaActual) {
        log.info("Entrando al método de mejoras de los individuos");
        ListaEnlazada<Individuo> individuos = casillaActual.getIndividuos();
        ListaEnlazada<Entorno> entorno = casillaActual.getRecursos();

        for (int i = 0; i < individuos.getNumeroElementos(); i++) {

            for (int j = 0; j < entorno.getNumeroElementos(); j++) {

                if (entorno.getElemento(j).getData().getClass() == Agua.class) {
                    Agua.accionAgua(individuos.getElemento(i).getData());
                    entorno.delete(j);
                    individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " ha sumado " + turnosVidaExtraAgua + " puntos de vida" + '\n');
                    int cantidadAguaAnterior=individuos.getElemento(i).getData().getCantidadDeAguaBebida();
                    int cantidadNuevaAguaBebida = (cantidadAguaAnterior + 1);
                    individuos.getElemento(i).getData().setCantidadDeAguaBebida(cantidadNuevaAguaBebida);
                } else if (entorno.getElemento(j).getData().getClass() == Comida.class) {
                    Comida.accionComida(individuos.getElemento(i).getData());
                    entorno.delete(j);
                    individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " ha sumado " + turnosVidaExtraComida + " puntos de vida" + '\n');

                } else if (entorno.getElemento(j).getData().getClass() == Biblioteca.class) {
                    Biblioteca.accionBiblioteca(individuos.getElemento(i).getData());
                    entorno.delete(j);
                    individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " ha realizado la accion de la biblioteca: sube la probabilidad de clonacion " + probabilidadClonacionExtraBiblioteca + " y se ha subido de tipo" + '\n');

                } else if (entorno.getElemento(j).getData().getClass() == Montaña.class) {
                    Montaña.accionMontaña(individuos.getElemento(i).getData());
                    entorno.delete(j);
                    individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " ha perdido " + turnosDevidaMenosMontaña + " puntos de vida" + '\n');

                } else if (entorno.getElemento(j).getData().getClass() == Tesoro.class) {
                    Tesoro.accionTesoro(individuos.getElemento(i).getData());
                    entorno.delete(j);
                    individuos.getElemento(i).getData().getCola().push("El individuo " + individuos.getElemento(i).getData() + " se ha encontrado un tesoro: sabe la probabilidad de reproduccion" + probabilidadReproduccionExtraTesoro + '\n');
                }


                casillaActual.setRecursos(entorno);
            }

        }


    }
}