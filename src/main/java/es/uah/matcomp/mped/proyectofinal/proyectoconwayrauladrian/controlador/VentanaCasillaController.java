package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VentanaCasillaController {
    @FXML
    public Label labelCasilla;
    public Label labelIndividuoCreado;
    //El id que se le pone a cada individuo cuando se crea uno nuevo
    //TODO-> HACER QUE FUNCIONE EL ID int id;
    int turnoActual;
    ParametrosIndividuoModelProperties parametrosIndividuo;
    ParametrosEntornoModelProperties parametrosEntorno;
    int turnosDeVidaRestantes;
    int probabilidadMuerte;
    int probabilidadClonacion;
    int probabilidadReproduccion;

    public void tomarValores() {
        this.turnosDeVidaRestantes = parametrosIndividuo.turnosVidaRestantesProperty().getValue().intValue();
        this.probabilidadMuerte = parametrosIndividuo.probabilidadMuerteProperty().getValue().intValue();
        this.probabilidadClonacion = parametrosIndividuo.probabilidadClonacionProperty().getValue().intValue();
        this.probabilidadReproduccion = parametrosIndividuo.probabilidadReproduccionProperty().getValue().intValue();
    }

    public Label labelIndividuosTipo1;
    public Label labelIndividuosTipo2;
    public Label labelIndividuosTipo3;
    public Button botonAñadirIndividuo3;
    public Button botonAñadirIndividuo2;
    public Button botonAñadirIndividuo1;
    public Button botonEliminarIndividuo1;
    public Button botonEliminarIndividuo2;
    public Button botonEliminarIndividuo3;

    public Button botonAñadirTesoro;
    public Button botonAñadirAgua;
    public Button botonAñadirComida;
    public Button botonAñadirBiblioteca;
    public Button botonAñadirMontaña;
    public Button botonAñadirPozo;
    public Label labelCantidadAgua;
    public Label labelCantidadComida;
    public Label labelCantidadBiblioteca;
    public Label labelCantidadMontaña;
    public Label labelCantidadTesoro;
    public Label labelCantidadPozo;
    public Button botonEliminarAgua;
    public Button botonEliminarComida;
    public Button botonEliminarBiblioteca;
    public Button botonEliminarMontaña;
    public Button botonEliminarPozo;
    public Button botonEliminarTesoro;

    private Casilla casilla;

    private Stage escenaVentana;

    //Creamos el tablero vacío, que se generará despues
    ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = new ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>();


    public void setStage(Stage escenaDada) {
        this.escenaVentana = escenaDada;
    }

    //Función que actualiza las labels donde se muestra la cantida de elenentos e individuos
    public void mostrarInfo() {
        ListaEnlazada<Entorno> recursos = casilla.getRecursos();
        ListaEnlazada<Individuo> individuos = casilla.getIndividuos();
        int cantidadAgua = 0;
        int cantidadComida = 0;
        int cantidadBiblioteca = 0;
        int cantidadMontaña = 0;
        int cantidadPozo = 0;
        int cantidadTesoro = 0;
        int cantidadIndividuo1 = 0;
        int cantidadIndividuo2 = 0;
        int cantidadIndividuo3 = 0;
        for (int i = 0; i < recursos.getNumeroElementos(); i++) {
            if (recursos.getElemento(i).getData().getClass() == Agua.class) {
                cantidadAgua++;
            } else if (recursos.getElemento(i).getData().getClass() == Comida.class) {
                cantidadComida++;
            } else if (recursos.getElemento(i).getData().getClass() == Biblioteca.class) {
                cantidadBiblioteca++;
            } else if (recursos.getElemento(i).getData().getClass() == Montaña.class) {
                cantidadMontaña++;
            } else if (recursos.getElemento(i).getData().getClass() == Tesoro.class) {
                cantidadTesoro++;
            }
        }
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            if (individuos.getElemento(i).getData().getTipo() == 1) {
                cantidadIndividuo1++;
            } else if (individuos.getElemento(i).getData().getTipo() == 2) {
                cantidadIndividuo2++;
            } else if (individuos.getElemento(i).getData().getTipo() == 3) {
                cantidadIndividuo3++;
            }
        }
        labelCantidadAgua.setText(String.valueOf(cantidadAgua));
        labelCantidadComida.setText(String.valueOf(cantidadComida));
        labelCantidadBiblioteca.setText(String.valueOf(cantidadBiblioteca));
        labelCantidadTesoro.setText(String.valueOf(cantidadTesoro));
        labelCantidadPozo.setText(String.valueOf(cantidadPozo));
        labelCantidadMontaña.setText(String.valueOf(cantidadMontaña));
        labelIndividuosTipo1.setText(String.valueOf(cantidadIndividuo1));
        labelIndividuosTipo2.setText(String.valueOf(cantidadIndividuo2));
        labelIndividuosTipo3.setText(String.valueOf(cantidadIndividuo3));
        labelCasilla.setText("CASILLA ACTUAL: (" + casilla.getCoordenadaX() + "," + casilla.getCoordenadaY() + ")");
    }


    public void setCasilla(Casilla casillaActual) {
        this.casilla = casillaActual;
    }

    public void setParametros(ParametrosIndividuoModelProperties parametrosIndividuo, ParametrosEntornoModelProperties parametrosEntorno, int turnoActual) {
        this.parametrosIndividuo = parametrosIndividuo;
        this.parametrosEntorno = parametrosEntorno;
        this.turnoActual = turnoActual;

    }

    //FUNCIONES PARA LOS BOTONES DE AÑADIR INDIVIDUOS O RECURSOS
    //Ejecutable por botón
    private void nuevoIndividuo(int tipo) {
        id++;
        ListaEnlazada<Individuo> individuos = casilla.getIndividuos();
        if (individuos.getNumeroElementos() < 3) {
            Individuo nuevoIndividuo = new Individuo(casilla.getCoordenadaX(), casilla.getCoordenadaY(),
                    id, tipo, turnosDeVidaRestantes, turnoActual, probabilidadMuerte,
                    probabilidadClonacion, probabilidadReproduccion);
            individuos.add(nuevoIndividuo);
            labelIndividuoCreado.setText("Individuo añadido: " + nuevoIndividuo.toString());
        } else if (individuos.getNumeroElementos() == 3) {
            ponerBotonEnRojo(tipo);
            casilla.setIndividuos(individuos);
        }
        mostrarInfo();
    }

    private void quitarIndividuo(int tipo) {
        ListaEnlazada<Individuo> individuos = casilla.getIndividuos();
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            if (individuos.getElemento(i).getData().getTipo() == tipo) {
                individuos.delete(i);
                ponerBotonesSinColor();
                casilla.setIndividuos(individuos);
                mostrarInfo();
                return;
            }
        }
    }


    public void nuevoRecurso(String clase) {
        ListaEnlazada<Entorno> recursos = casilla.getRecursos();
        int x = casilla.getCoordenadaX();
        int y = casilla.getCoordenadaY();
        if (recursos.getNumeroElementos() == 3) {
            if (clase == "Agua") {
                botonAñadirAgua.setStyle("-fx-background-color: #ff0000;");
            } else if (clase == "Biblioteca") {
                botonAñadirBiblioteca.setStyle("-fx-background-color: #ff0000;");
            } else if (clase == "Comida") {
                botonAñadirComida.setStyle("-fx-background-color: #ff0000;");
            } else if (clase == "Montaña") {
                botonAñadirMontaña.setStyle("-fx-background-color: #ff0000;");
            } else if (clase == "Pozo") {
                botonAñadirPozo.setStyle("-fx-background-color: #ff0000;");
            } else if (clase == "Tesoro") {
                botonAñadirTesoro.setStyle("-fx-background-color: #ff0000;");
            }
        }
        if (recursos.getNumeroElementos() < 3) {
            if (clase == "Agua") {
                recursos.add(new Agua(x, y, 10));
            } else if (clase == "Biblioteca") {
                recursos.add(new Biblioteca(x, y, 10));
            } else if (clase == "Comida") {
                recursos.add(new Comida(x, y, 10));
            } else if (clase == "Montaña") {
                recursos.add(new Montaña(x, y, 10));
            } else if (clase == "Pozo") {
                recursos.add(new Pozo(x, y, 10));
            } else if (clase == "Tesoro") {
                recursos.add(new Tesoro(x, y, 10));
            }
            mostrarInfo();
        }
        casilla.setRecursos(recursos);
    }

    public void eliminarRecurso(Class clase) {
        ListaEnlazada<Entorno> recursos = casilla.getRecursos();
        for (int i = 0; i < recursos.getNumeroElementos(); i++) {
            if (clase == recursos.getElemento(i).getData().getClass()) {
                recursos.delete(i);
                setBotonesNulos();
                mostrarInfo();
                return;
            }
        }
        casilla.setRecursos(recursos);
    }

    //Funciones para la visualización de los botones
    public void ponerBotonEnRojo(int numeroBoton) {
        if (numeroBoton == 1) {
            botonAñadirIndividuo1.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000;");
        } else if (numeroBoton == 2) {
            botonAñadirIndividuo2.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000;");
        } else if (numeroBoton == 3) {
            botonAñadirIndividuo3.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000;");
        }
    }

    public void ponerBotonesSinColor() {
        botonAñadirIndividuo1.setStyle(null);
        botonAñadirIndividuo2.setStyle(null);
        botonAñadirIndividuo3.setStyle(null);
    }

    private void setBotonesNulos() {
        botonAñadirMontaña.setStyle(null);
        botonAñadirComida.setStyle(null);
        botonAñadirAgua.setStyle(null);
        botonAñadirBiblioteca.setStyle(null);
        botonAñadirTesoro.setStyle(null);
        botonAñadirPozo.setStyle(null);
    }

    //Ejecución de las funciones creadas al tocar cada botón
    public void onbotonAñadirIndividuo1(ActionEvent actionEvent) {
        nuevoIndividuo(1);
    }

    public void onbotonAñadirIndividuo2(ActionEvent actionEvent) {
        nuevoIndividuo(2);
    }

    public void onbotonAñadirIndividuo3(ActionEvent actionEvent) {
        nuevoIndividuo(3);
    }

    public void onBotonEliminarIndividuo1(ActionEvent actionEvent) {
        quitarIndividuo(1);
    }

    public void onBotonEliminarIndividuo2(ActionEvent actionEvent) {
        quitarIndividuo(2);
    }

    public void onBotonEliminarIndividuo3(ActionEvent actionEvent) {
        quitarIndividuo(3);
    }

    public void onBotonAñadirAgua(ActionEvent actionEvent) {
        nuevoRecurso("Agua");
    }

    public void onBotonAñadirComida(ActionEvent actionEvent) {
        nuevoRecurso("Comida");
    }

    public void onBotonAñadirBiblioteca(ActionEvent actionEvent) {
        nuevoRecurso("Biblioteca");
    }

    public void onBotonAñadirMontaña(ActionEvent actionEvent) {
        nuevoRecurso("Montaña");
    }

    public void onBotonAñadirPozo(ActionEvent actionEvent) {
        nuevoRecurso("Pozo");
    }

    public void onBotonAñadirTesoro(ActionEvent actionEvent) {
        nuevoRecurso("Tesoro");
    }


    public void onBotonEliminarAgua(ActionEvent actionEvent) {
        eliminarRecurso(Agua.class);
    }

    public void onBotonEliminarComida(ActionEvent actionEvent) {
        eliminarRecurso(Comida.class);
    }

    public void onBotonEliminarBiblioteca(ActionEvent actionEvent) {
        eliminarRecurso(Biblioteca.class);
    }

    public void onBotonEliminarMontaña(ActionEvent actionEvent) {
        eliminarRecurso(Montaña.class);
    }

    public void onBotonEliminarPozo(ActionEvent actionEvent) {
        eliminarRecurso(Pozo.class);
    }

    public void onBotonEliminarTesoro(ActionEvent actionEvent) {
        eliminarRecurso(Agua.class);
    }
}
