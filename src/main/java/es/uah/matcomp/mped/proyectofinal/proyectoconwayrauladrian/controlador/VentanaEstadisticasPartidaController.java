package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle.*;

public class VentanaEstadisticasPartidaController implements Initializable {
    @FXML
    public Label labelIndividuoLongevo;
    public Label labelIndividuoConMutaciones;
    public Label labelCantidadReproduccionesTotal;
    public Label labelIndividuoConMasReproducciones;
    public Label labelIndividuoQueMasAguaHaBebido;
    public Label labelConMasTurnosRestantesPartida;
    public Button buttonColaIndividuoLongevo;
    public Label labelCantidadMutacionesTotal;
    ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    Individuo individuoMasLongevo;
    Individuo individuoConMasReproducciones;
    Individuo individuoQueMasAguaHaBebido;
    Individuo individuoConMasClonaciones;
    Stage primaryStage = new Stage();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelCantidadReproduccionesTotal.setText(String.valueOf(numeroReproducciones));
        labelCantidadMutacionesTotal.setText(String.valueOf(numeroMutaciones));
        labelConMasTurnosRestantesPartida.setText(individuoMayorEsperanzaDeVida.toString());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Individuo buscarGanadorLongevidad() {
        int mejorPuntuacion = 0;
        Individuo individuoGanador = null;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                    if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getTurnosVividos() > mejorPuntuacion) {
                        individuoGanador = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData();
                        mejorPuntuacion = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getTurnosVividos();
                    }
                }
            }
        }
        for (int i = 0; i < colaIndividuos.getDatos().getNumeroElementos(); i++) {
            if (colaIndividuos.getDatos().getElemento(i).getData().getTurnosVividos() > mejorPuntuacion) {
                mejorPuntuacion = colaIndividuos.getDatos().getElemento(i).getData().getTurnosVividos();
                individuoGanador = colaIndividuos.getDatos().getElemento(i).getData();
            }
        }
        if (individuoGanador != null) {
            individuoMasLongevo = individuoGanador;
            return individuoGanador;
        } else {
            return null;
        }
    }

    public Individuo buscarGanadorReproducciones() {
        int mejorPuntuacion = 0;
        Individuo individuoGanador = null;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                    System.out.println("numero reproducciones: "+tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getNumeroDeReproducciones());
                    if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getNumeroDeReproducciones() > mejorPuntuacion) {
                        individuoGanador = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData();
                        mejorPuntuacion = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getNumeroDeReproducciones();
                    }
                }
            }
        }
        for (int i = 0; i < colaIndividuos.getDatos().getNumeroElementos(); i++) {
            if (colaIndividuos.getDatos().getElemento(i).getData().getNumeroDeReproducciones() > mejorPuntuacion) {
                mejorPuntuacion = colaIndividuos.getDatos().getElemento(i).getData().getNumeroDeReproducciones();
                individuoGanador = colaIndividuos.getDatos().getElemento(i).getData();
            }
        }
        if (individuoGanador != null) {
            individuoConMasReproducciones = individuoGanador;
            return individuoGanador;
        } else {
            return null;
        }
    }

    public Individuo buscarGanadorClonaciones() {
        int mejorPuntuacion = 0;
        Individuo individuoGanador = null;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                    if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getNumeroDeClonaciones() > mejorPuntuacion) {
                        individuoGanador = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData();
                        mejorPuntuacion = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getNumeroDeClonaciones();
                    }
                }
            }
        }
        for (int i = 0; i < colaIndividuos.getDatos().getNumeroElementos(); i++) {
            if (colaIndividuos.getDatos().getElemento(i).getData().getNumeroDeClonaciones() > mejorPuntuacion) {
                mejorPuntuacion = colaIndividuos.getDatos().getElemento(i).getData().getNumeroDeClonaciones();
                individuoGanador = colaIndividuos.getDatos().getElemento(i).getData();
            }
        }
        if (individuoGanador != null) {
            individuoConMasClonaciones = individuoGanador;
            return individuoGanador;
        } else {
            return null;
        }
    }

    public Individuo buscarGanadorAgua() {
        int mejorPuntuacion = 0;
        Individuo individuoGanador = null;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                    if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getCantidadDeAguaBebida() > mejorPuntuacion) {
                        individuoGanador = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData();
                        mejorPuntuacion = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getCantidadDeAguaBebida();
                    }
                }
            }
        }
        for (int i = 0; i < colaIndividuos.getDatos().getNumeroElementos(); i++) {
            if (colaIndividuos.getDatos().getElemento(i).getData().getCantidadDeAguaBebida() > mejorPuntuacion) {
                mejorPuntuacion = colaIndividuos.getDatos().getElemento(i).getData().getCantidadDeAguaBebida();
                individuoGanador = colaIndividuos.getDatos().getElemento(i).getData();
            }
        }
        if (individuoGanador != null) {
            individuoQueMasAguaHaBebido = individuoGanador;
            return individuoGanador;
        } else {
            return null;
        }
    }


    public void setParametros(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tableroDado) {
        this.tablero = tableroDado;
    }

    public void actualizarLabels(){
        labelIndividuoLongevo.setText(individuoMasLongevo.toString());
        labelIndividuoQueMasAguaHaBebido.setText(individuoQueMasAguaHaBebido.toString());
        labelIndividuoConMutaciones.setText(individuoConMasClonaciones.toString());
        labelIndividuoConMasReproducciones.setText(individuoConMasReproducciones.toString());

    }
    private void mostrarColaIndividuo(int tipo) {
        Individuo individuoMostrar = null;
        if (tipo==1){//Longevidad
            individuoMostrar=individuoMasLongevo;
        }else if (tipo==2){//Reproducciones
            individuoMostrar=individuoConMasReproducciones;
        }else if (tipo==3){//Clonaciones
            individuoMostrar=individuoConMasClonaciones;
        }else if (tipo==4){//Agua
            individuoMostrar=individuoQueMasAguaHaBebido;
        }
        if (individuoMostrar != null) {
            ListaEnlazada<String> colaIndividuo = individuoMostrar.getCola().getDatos();
            StringBuilder colaIndividuoLongevo = new StringBuilder();
            for (int i = 0; i < colaIndividuo.getNumeroElementos(); i++) {
                colaIndividuoLongevo.append(colaIndividuo.getElemento(i).getData());
            }

            Stage ventanaEmergente = new Stage();
            ventanaEmergente.initModality(Modality.APPLICATION_MODAL);
            ventanaEmergente.initStyle(StageStyle.DECORATED);
            ventanaEmergente.setTitle("Texto Detallado");
            TextArea textArea = new TextArea(colaIndividuoLongevo.toString());
            textArea.setWrapText(true);
            textArea.setEditable(false);
            ScrollPane scrollPane = new ScrollPane(textArea);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setMinHeight(380);
            VBox layout = new VBox(scrollPane);
            Scene escenaEmergente = new Scene(layout, 600, 400);
            ventanaEmergente.setScene(escenaEmergente);
            ventanaEmergente.showAndWait();
        }
    }

    public void onBotonIndividuoMasLongevo() {
        mostrarColaIndividuo(1);
    }

    public void onBotonColaIndividuoMasReproducciones() {
        mostrarColaIndividuo(2);
    }

    public void onBotonIndividuoMasClonaciones() {
        mostrarColaIndividuo(3);
    }

    public void onBotonColaIndividuoAgua() {
        mostrarColaIndividuo(4);
    }

}
