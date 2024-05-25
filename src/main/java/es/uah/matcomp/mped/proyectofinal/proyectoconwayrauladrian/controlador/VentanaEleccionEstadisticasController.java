package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class VentanaEleccionEstadisticasController {
    public Button botonEstadisticas;
    public Button botonArbolIndividuos;
    public Button botonFinalizar;
    public Button botonColaDeIndividuos;
    private ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    private Stage stage;

    public void onBotonColaIndividuos(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton colaActividades en VentanaEleccionEstadisticasController");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("colaActividades.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setTitle("Cola de Individuos");
            stage.setScene(scene);
            VentanaColaActividades controlador=fxmlLoader.getController();
            controlador.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBotonEstadisticas(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton de estadisticas en VentanaEleccionEstadisticasController");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("estadisticasIndividuos.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 850, 800);
            stage.setTitle("Estadisticas de la partida");
            stage.setScene(scene);
            VentanaEstadisticasPartidaController controlador=fxmlLoader.getController();
            controlador.setStage(stage);
            controlador.setParametros(tablero);
            controlador.buscarGanadorLongevidad();
            controlador.buscarGanadorClonaciones();
            controlador.buscarGanadorReproducciones();
            controlador.buscarGanadorAgua();
            controlador.actualizarLabels();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBotonArbolIndividuos(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton arbol individuos en VentanaEleccionEstadisticasController");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaArboles.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 750);
            stage.setTitle("Arboles Individuos");
            stage.setScene(scene);
            VentanaArboles controlador=fxmlLoader.getController();
            controlador.setStage(stage);
            controlador.setParametros(tablero);
            controlador.rellenarTreeView();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBotonFinalizar(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton salir en VentanaEleccionEstadisticasController");
        Stage escenario = (Stage) botonFinalizar.getScene().getWindow();
        escenario.close();
    }

    public void setParametros(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero) {
        this.tablero=tablero;
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }
}
