package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VentanaCasillaController {
    public Label labelCasilla;
    //El id que se le pone a cada individuo cuando se crea uno nuevo
    int id=0;
    int turnoActual;
    ParametrosIndividuoModelProperties parametrosIndividuo;
    ParametrosEntornoModelProperties parametrosEntorno;
    int turnosDeVidaRestantes;
    int probabilidadMuerte;
    int probabilidadClonacion;
    int probabilidadReproduccion;
public void tomarValores(){
    this.turnosDeVidaRestantes=parametrosIndividuo.turnosVidaRestantesProperty().getValue().intValue();
    this.probabilidadMuerte=parametrosIndividuo.probabilidadMuerteProperty().getValue().intValue();
    this.probabilidadClonacion=parametrosIndividuo.probabilidadClonacionProperty().getValue().intValue();
    this.probabilidadReproduccion=parametrosIndividuo.probabilidadReproduccionProperty().getValue().intValue();
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
            if (individuos.getElemento(i).getData().getTipo()==1){
                cantidadIndividuo1++;
            } else if(individuos.getElemento(i).getData().getTipo()==2){
                cantidadIndividuo2++;
            } else if(individuos.getElemento(i).getData().getTipo()==3){
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

    }


    public void setCasilla(Casilla casillaActual) {
        this.casilla = casillaActual;
    }
    public void setParametros(ParametrosIndividuoModelProperties parametrosIndividuo, ParametrosEntornoModelProperties parametrosEntorno,int turnoActual){
        this.parametrosIndividuo=parametrosIndividuo;
        this.parametrosEntorno=parametrosEntorno;
        this.turnoActual=turnoActual;

    }

    //FUNCIONES PARA LOS BOTONES DE AÑADIR INDIVIDUOS O RECURSOS
    public void onbotonAñadirIndividuo1(ActionEvent actionEvent) {

        id++;
        ListaEnlazada<Individuo> individuos = casilla.getIndividuos();
        if (individuos.getNumeroElementos()<3){
            individuos.add(new Individuo(casilla.getCoordenadaX(),casilla.getCoordenadaY(), id,1,turnosDeVidaRestantes,turnoActual,probabilidadMuerte,probabilidadClonacion,probabilidadReproduccion));
        }
        casilla.setIndividuos(individuos);
        mostrarInfo();
    }

    public void onbotonAñadirIndividuo2(ActionEvent actionEvent) {
    }

    public void onbotonAñadirIndividuo3(ActionEvent actionEvent) {
    }

    public void onBotonAñadirAgua(ActionEvent actionEvent) {
    }

    public void onBotonAñadirComida(ActionEvent actionEvent) {
    }

    public void onBotonAñadirBiblioteca(ActionEvent actionEvent) {
    }

    public void onBotonAñadirMontaña(ActionEvent actionEvent) {
    }

    public void onBotonAñadirPozo(ActionEvent actionEvent) {
    }

    public void onBotonAñadirTesoro(ActionEvent actionEvent) {
    }

    public void onBotonEliminarIndividuo1(ActionEvent actionEvent) {
    }

    public void onBotonEliminarIndividuo2(ActionEvent actionEvent) {
    }

    public void onBotonEliminarIndividuo3(ActionEvent actionEvent) {
    }

    public void onBotonEliminarAgua(ActionEvent actionEvent) {
    }

    public void onBotonEliminarComida(ActionEvent actionEvent) {
    }

    public void onBotonEliminarBiblioteca(ActionEvent actionEvent) {
    }

    public void onBotonEliminarMontaña(ActionEvent actionEvent) {
    }

    public void onBotonEliminarPozo(ActionEvent actionEvent) {
    }

    public void onBotonEliminarTesoro(ActionEvent actionEvent) {
    }
}
