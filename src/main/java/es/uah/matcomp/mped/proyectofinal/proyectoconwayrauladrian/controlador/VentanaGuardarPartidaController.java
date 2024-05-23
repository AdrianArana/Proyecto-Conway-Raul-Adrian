package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.guardar;

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
        labelEscribaNombreArchivo.setText("Escribe el nombre del archivo, "+modeloGuardar.getNombreGuardadoString()+":");

    }

    public void setStage(Stage stageDado) {
        this.stage = stageDado;
    }

    public void onGuardarPartidaButton(ActionEvent actionEvent) {

        nombreArchivoGuardarPartida = (textField.getText());
        if (Objects.equals(nombreArchivoGuardarPartida, "")) {
            labelError.setText("El nombre no puede ser vacío");
        }else {
            System.out.println("Nombre de Archivo Guardado: " + nombreArchivoGuardarPartida);
            guardar(nombreArchivoGuardarPartida+"_Nombre_"+modeloGuardar.getNombreGuardadoString()+".json", modeloGuardar);
            System.out.println("Partida guardad corrctamente a nombre de: "+nombreArchivoGuardarPartida+"_Nombre_"+modeloGuardar.getNombreGuardadoString()+".json");
            estadisticasButton.setDisable(false);
        }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadisticasButton.setDisable(true);
    }
    public void onEstadisticasButtonClick(ActionEvent actionEvent) {

    }
}
