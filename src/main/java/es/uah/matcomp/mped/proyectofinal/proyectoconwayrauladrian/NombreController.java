package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

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

public class NombreController implements Initializable {
    public Button continuarButton;

    @FXML
    protected void onSiguienteButtonClick() {
        Stage stageAnterior = (Stage) continuarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configuracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("Configuración de parámetros");
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}