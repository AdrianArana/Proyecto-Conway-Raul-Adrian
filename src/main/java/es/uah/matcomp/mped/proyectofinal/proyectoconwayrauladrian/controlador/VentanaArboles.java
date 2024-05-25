package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle.colaIndividuos;

public class VentanaArboles implements Initializable {
    @FXML
    public TreeView<Individuo> treeView;
    private ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void rellenarTreeView() {
        Individuo individuoMostrarArbol = buscarGanador();
        if (individuoMostrarArbol != null) {
            TreeItem<Individuo> individuoGanadorItem =añadirHijo(individuoMostrarArbol);
            treeView.setRoot(individuoGanadorItem);
        }else{
            treeView.setRoot(new TreeItem<>());
        }
    }

    private TreeItem<Individuo> añadirHijo(Individuo individuoMostrarArbol) {
        TreeItem<Individuo> indiv = new TreeItem<>(individuoMostrarArbol);
        if (individuoMostrarArbol.getArbolDelIndividuo().getRaiz().getIzquierda() != null) {
            indiv.getChildren().add(añadirHijo((Individuo) individuoMostrarArbol.getArbolDelIndividuo().getRaiz().getIzquierda().getDato()));
        }if (individuoMostrarArbol.getArbolDelIndividuo().getRaiz().getDerecha() != null) {
            indiv.getChildren().add(añadirHijo((Individuo) individuoMostrarArbol.getArbolDelIndividuo().getRaiz().getDerecha().getDato()));
        }
        return indiv;
    }


    private Individuo buscarGanador() {
        int mejorPuntuacion=0;
        Individuo individuoGanador = null;
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                    if (tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().getTurnosVividos() > mejorPuntuacion) {
                        individuoGanador = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData();
                    }
                }
            }
        }
        for (int i = 0; i < colaIndividuos.getDatos().getNumeroElementos(); i++) {
            if (colaIndividuos.getDatos().getElemento(i).getData().getTurnosVividos()>mejorPuntuacion){
                individuoGanador=colaIndividuos.getDatos().getElemento(i).getData();
            }
        }
        if (individuoGanador!=null){
            return individuoGanador;
        } else {
            return null;
        }

    }

    public void setParametros(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tableroDado) {
        this.tablero = tableroDado;
    }

    public void onAction() {
        TreeItem<Individuo> item = treeView.getSelectionModel().getSelectedItem();
        item.setExpanded(true);
        if (item != null) {
            System.out.println(item.getValue());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
