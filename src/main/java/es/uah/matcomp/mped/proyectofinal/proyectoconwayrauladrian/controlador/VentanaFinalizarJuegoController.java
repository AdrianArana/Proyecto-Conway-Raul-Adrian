package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class VentanaFinalizarJuegoController {

    @FXML
    private Button noButton;

    @FXML
    protected void onNoButton() {
        log.info ("Se ha pulsado el boton 'Salir' en VentanaFinalizarJuegoController");
        System.exit(0);
    }

    public void onBotonVolver() {
        Stage stageAnterior = (Stage) noButton.getScene().getWindow();
        stageAnterior.close();
    }
}
