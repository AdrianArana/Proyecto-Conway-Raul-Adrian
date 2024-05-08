package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.NombreGuardado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
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

    //Construimos un modelo para los datos compartidos con las clases ParametrosIndividuo y ParametrosIndividuoModelProperties
    private ParametrosIndividuo parametrosData = new ParametrosIndividuo(7, 10, 10,30);
    private ParametrosIndividuoModelProperties modeloParaGUICompartido = new ParametrosIndividuoModelProperties(parametrosData);


    @FXML
    protected void onSiguienteButtonClick() {
        nombreGuardado.setNombre(textFieldNombre.getText());
        System.out.println("Nombre Guardado: " + nombreGuardado.getNombre());
        Stage stageAnterior = (Stage) continuarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaConfiguracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Configuración de parámetros");
            stage.setScene(scene);
            //Aqui creamos el controlador de la ventana de configuracion y le guardamos la Data
            ConfiguracionController configuracionController = fxmlLoader.getController();
            configuracionController.loadUserData(this.modeloParaGUICompartido);
            configuracionController.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}