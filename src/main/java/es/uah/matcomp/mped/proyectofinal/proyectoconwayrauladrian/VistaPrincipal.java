package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class VistaPrincipal extends Application {
    @Override
    public void start(Stage stagePrincipal) throws IOException {
        log.info ("Comienza el programa:");
        FXMLLoader FXMLCargadoPrincipal = new FXMLLoader(VistaPrincipal.class.getResource("ventanaInicial.fxml"));
        Scene escenaPrincipal = new Scene(FXMLCargadoPrincipal.load(), 750, 500);
        stagePrincipal.setTitle("Conway - El juego de la vida");

        stagePrincipal.setScene(escenaPrincipal);
        stagePrincipal.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
