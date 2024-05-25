package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.NombreGuardado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.cargar;
import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class PrincipalController implements Initializable {

    @FXML
    private Label welcomeText;
    private Stage scene;
    public Button volverButton;
    public Button nuevaPartidaButton;
    public Button salirButton;
    String nombreGuardadoString;


    @FXML
    protected void onMiBotonNuevaPartidaButtonClick() {
        log.info ("Se ha pulsado el boton nueva partida en PrincipalController");
        Stage stageAntiguo = (Stage) nuevaPartidaButton.getScene().getWindow();
        stageAntiguo.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("nombre.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Nombre");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSalirButtonClick() {
        log.info ("Se ha pulsado el boton salir en PrincipalController");
        Stage escenario = (Stage) salirButton.getScene().getWindow();
        escenario.close();
    }

    public void onCreditosButtonClick() {
        log.info ("Se ha pulsado el boton creditos en PrincipalController");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("creditos.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Créditos");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onVolverButtonClick() {
        log.info ("Se ha pulsado el boton volver en PrincipalController");
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.info ("Inicializacion PrincipalController");
    }

    public void onBotonCargarPartida(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton cargar partida en PrincipalController");
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaSeleccionCargarArchivo.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            VentanaSeleccionCargarArchivoController ventanaSeleccionCargarArchivoController = fxmlLoader.getController();
            stage.setScene(scene);
            stage.setTitle("Cargar partida desde archivo");
            ventanaSeleccionCargarArchivoController.hacerPagination();
            ventanaSeleccionCargarArchivoController.setStage(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
