package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.cargar;
import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.cargarPartidasGuardadas;

public class VentanaSeleccionCargarArchivoController implements Initializable {

    public Pagination paginacionArchivo;
    public Button botonVolver;
    public Button botonContinuar;
    public String partidaElegida;
    public Label labelNingunaPartidaGuardada;
    public Label labelElegidoCorrectamente;
    private Stage stage;
    ListaEnlazada<String> partidasGuardadas;
    IntegerProperty indiceElegido= new SimpleIntegerProperty(0);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }


    public void hacerPagination() {
        partidasGuardadas = cargarPartidasGuardadas();
        assert partidasGuardadas != null;
        if (cargarPartidasGuardadas()!=null){
            paginacionArchivo.setPageCount(Objects.requireNonNull(cargarPartidasGuardadas()).getNumeroElementos());
            paginacionArchivo.setPageFactory(pageIndex -> {
                assert partidasGuardadas != null;
                if (pageIndex >= partidasGuardadas.getNumeroElementos()) {
                    return null;
                }
                ElementoLE<String> partida = partidasGuardadas.getElemento(pageIndex);
                Button botonPagina = new Button();
                botonPagina.setMaxWidth(partida.getData().length()*8);
                botonPagina.setMinWidth(partida.getData().length()*6);
                botonPagina.setText(partida.getData());
                botonPagina.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        partidaElegida=partidasGuardadas.getElemento(pageIndex).getData();
                        labelElegidoCorrectamente.setText("Partida seleccionada: "+partidaElegida);
                    }
                });
                VBox vbox = new VBox(botonPagina);
                labelNingunaPartidaGuardada.setText("");
                return vbox;
            });
        }else{
            paginacionArchivo.setOpacity(0);
            labelNingunaPartidaGuardada.setText("No hay partidas que cargar");
        }


    }

    public void onBotonContinuar() {
        if (partidaElegida!=null){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaJuego.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
                modeloDatosFinal datosCargados = cargar(partidaElegida);
                stage.setTitle("Juego - La vida de Conway: PARTIDA DE " + datosCargados.getNombreGuardadoString().toUpperCase() + " CARGADA");
                stage.setScene(scene);
                VentanaJuegoController juegoController = fxmlLoader.getController();
                juegoController.setTablero(datosCargados.getTablero());
                juegoController.setParametros(datosCargados.getNombreGuardadoString(), new ParametrosIndividuoModelProperties(datosCargados.getParametrosGuardadosIndividuos()), new ParametrosEntornoModelProperties(datosCargados.getParametrosGuardadosEntorno()), new ParametrosCasillasModelProperties(datosCargados.getParametrosGuardadosCasillas()));
                juegoController.setBotones();
                juegoController.setStage(stage);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            labelElegidoCorrectamente.setText("Elija una partida que cargar");
        }

    }


    public void setStage(Stage stage) {
        this.stage=stage;
    }

    public void onBotonVolver(ActionEvent actionEvent) {
    }

}
