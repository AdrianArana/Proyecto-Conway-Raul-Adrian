package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.*;

public class VentanaGuardarPartidaController implements Initializable {

    public Label labelError;
    public Button estadisticasButton;
    String nombreArchivoGuardarPartida;
    public Button guardarPartidaButton;
    public TextField textField;
    public Label labelEscribaNombreArchivo;
    Stage stage;
    modeloDatosFinal modeloGuardar;

    public void setModeloDatosFinal(modeloDatosFinal modeloDatosFinal) {
        this.modeloGuardar = modeloDatosFinal;
        labelEscribaNombreArchivo.setText("Escribe el nombre del archivo, " + modeloGuardar.getNombreGuardadoString() + ":");

    }

    public void setStage(Stage stageDado) {
        this.stage = stageDado;
    }

    public void onGuardarPartidaButton(ActionEvent actionEvent) {
        log.info("Se ha pulsado el boton guardar partida en VentanaGuardarPartidaController");

        nombreArchivoGuardarPartida = (textField.getText());
        if (Objects.equals(nombreArchivoGuardarPartida, "")) {
            labelError.setText("El nombre no puede ser vacío");
        } else {
            guardar(nombreArchivoGuardarPartida + "_Nombre_" + modeloGuardar.getNombreGuardadoString() + ".json", modeloGuardar);

            ListaEnlazada<String> listaPartidasGuardadas = cargarPartidasGuardadas();
            if (listaPartidasGuardadas != null) {
                listaPartidasGuardadas.add(nombreArchivoGuardarPartida + "_Nombre_" + modeloGuardar.getNombreGuardadoString() + ".json");

            } else {
                listaPartidasGuardadas = new ListaEnlazada<>(new ElementoLE<String>(nombreArchivoGuardarPartida + "_Nombre_" + modeloGuardar.getNombreGuardadoString() + ".json"));
            }
            guardarPartidasGuardadas("partidasGuardadas.json", listaPartidasGuardadas);
            System.out.println("Partida guardada corrctamente a nombre de: " + nombreArchivoGuardarPartida + "_Nombre_" + modeloGuardar.getNombreGuardadoString() + ".json");
            estadisticasButton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info("Inicializaacion VentanaGuardarPartidaController");
        estadisticasButton.setDisable(true);
    }

    public void onEstadisticasButtonClick(ActionEvent actionEvent) {
        Stage stageAnterior = (Stage) guardarPartidaButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaEleccionDeVistaResultados.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 600);
            stage.setTitle("Resultados de la partida");
            stage.setScene(scene);
            VentanaEleccionEstadisticasController controladorPantallaEstadisticas = fxmlLoader.getController();
            //Mandamos el tablero a la última pantalla
            controladorPantallaEstadisticas.setParametros(modeloGuardar.getTablero());
            controladorPantallaEstadisticas.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
