package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NombreController implements Initializable {
    @FXML
    public Button continuarButton;
    public Label labelError;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private Button VolverButton;

    Stage stage;
    String nombreGuardadoString;


    //Construimos un modelo para los datos compartidos con las clases ParametrosIndividuo y ParametrosIndividuoModelProperties
    private ParametrosIndividuo parametrosData = new ParametrosIndividuo(40, 3, 90, 90);
    private ParametrosIndividuoModelProperties modeloParaGUICompartidoIndividuo = new ParametrosIndividuoModelProperties(parametrosData);

    private ParametrosEntorno parametrosDataEntorno = new ParametrosEntorno(20, 1, 20, 20, 20, 20, 20, 20);
    private ParametrosEntornoModelProperties modeloGuiCompartidoEntorno = new ParametrosEntornoModelProperties(parametrosDataEntorno);

    private ParametrosCasillas parametrosDataCasillas = new ParametrosCasillas(20, 20);
    private ParametrosCasillasModelProperties modeloGUICompartidoTablero = new ParametrosCasillasModelProperties(parametrosDataCasillas);


    @FXML
    protected void onSiguienteButtonClick() {
        nombreGuardadoString = (textFieldNombre.getText());
        if (nombreGuardadoString.equals("")) {
            labelError.setText("El nombre no puede ser vacío");
        } else {
            System.out.println("Nombre Guardado: " + nombreGuardadoString);
            Stage stageAnterior = (Stage) continuarButton.getScene().getWindow();
            stageAnterior.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaConfiguracion.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 800, 650);
                stage.setTitle("Configuración de parámetros");
                stage.setScene(scene);
                //Aqui creamos el controlador de la ventana de configuracion y le guardamos la Data
                ConfiguracionController configuracionController = fxmlLoader.getController();
                configuracionController.loadUserData(nombreGuardadoString, modeloParaGUICompartidoIndividuo, modeloGuiCompartidoEntorno, modeloGUICompartidoTablero);
                configuracionController.setStage(stage);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void onVolverButton() {
        Stage stageAntiguo = (Stage) VolverButton.getScene().getWindow();
        stageAntiguo.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaInicial.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
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