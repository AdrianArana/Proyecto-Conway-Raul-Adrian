package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NombreController implements Initializable {
    NombreGuardado nombreGuardado = new NombreGuardado();
    @FXML
    public Button continuarButton;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private Button VolverButton;

    //Construimos un modelo para los datos compartidos con las clases ParametrosIndividuo y ParametrosIndividuoModelProperties
    public ParametrosIndividuo parametrosData = new ParametrosIndividuo(20, 20, 20,20);
    private ParametrosIndividuoModelProperties modeloParaGUICompartidoIndividuo = new ParametrosIndividuoModelProperties(parametrosData);

    private ParametrosEntorno parametrosDataEntorno= new ParametrosEntorno(20,20,20,20,20,20);
    private ParametrosEntornoModelProperties modeloGuiCompartidoEntorno = new ParametrosEntornoModelProperties(parametrosDataEntorno);

    private ParametrosCasillas parametrosDataCasillas = new ParametrosCasillas(20,10);
    private ParametrosCasillasModelProperties modeloGUICompartidoTablero = new ParametrosCasillasModelProperties(parametrosDataCasillas);
    @FXML
    protected void onSiguienteButtonClick() {
        nombreGuardado.setNombre(textFieldNombre.getText());
        System.out.println("Nombre Guardado: " + nombreGuardado.getNombre());
        Stage stageAnterior = (Stage) continuarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaConfiguracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Configuración de parámetros");
            stage.setScene(scene);
            //Aqui creamos el controlador de la ventana de configuracion y le guardamos la Data
            ConfiguracionController configuracionController = fxmlLoader.getController();
            configuracionController.loadUserData(modeloParaGUICompartidoIndividuo,modeloGuiCompartidoEntorno,modeloGUICompartidoTablero);

            configuracionController.setStage(stage);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onVolverButton() {
        Stage stageAntiguo = (Stage) VolverButton.getScene().getWindow();
        stageAntiguo.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaInicial.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Conway - El juego de la vida");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}