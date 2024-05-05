package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NombreController implements Initializable {
    NombreGuardado nombreGuardado = new NombreGuardado();
    @FXML
    public Button continuarButton;
    @FXML
    private TextField textFieldNombre;

    @FXML
    protected void onSiguienteButtonClick() {
        nombreGuardado.setNombre(textFieldNombre.getText());
        System.out.println("Nombre Guardado: " + nombreGuardado.getNombre());
        Stage stageAnterior = (Stage) continuarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configuracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Configuración de parámetros");
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