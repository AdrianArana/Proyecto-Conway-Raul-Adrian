package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle.colaIndividuos;

public class VentanaColaActividades implements Initializable {

    public ScrollPane scrollPaneColaActividades;
    public TextArea textAreaCola;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListaEnlazada<Individuo> colaDatos = colaIndividuos.getDatos();
        StringBuilder colaIndividuoLongevo = new StringBuilder();
        for (int i = 0; i < colaDatos.getNumeroElementos(); i++) {
            colaIndividuoLongevo.append(colaDatos.getElemento(i).getData().toString());
        }
        textAreaCola.setText(colaIndividuoLongevo.toString());
        textAreaCola.setWrapText(true);
        textAreaCola.setEditable(false);
        scrollPaneColaActividades.setContent(textAreaCola);
        scrollPaneColaActividades.setFitToWidth(true);
        scrollPaneColaActividades.setFitToHeight(true);
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
