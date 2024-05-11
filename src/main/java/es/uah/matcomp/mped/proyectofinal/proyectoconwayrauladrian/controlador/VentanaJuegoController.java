package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Tablero;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController implements Initializable {
    @FXML
    public GridPane tableroFinal;
    @FXML
    public Button finalizarButton;

    int cantidadCasillasX = 10;
    int cantidadCasillasY = 10;//todo COMO HAGO PARA USAR LOS VALORES DEL CONFIRGURACIONCONTROLLER AQUI, Y ASI PARA TODO LOS MODELS

    private Casilla[][] hacerMatrtiz(int cantidadCasillasX, int cantidadCasillasY) {
        Tablero tablero = new Tablero(cantidadCasillasX, cantidadCasillasY);
        //Creamos una matriz para nuestro tablero
        return tablero.getCasillas();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Casilla[][] tableroMatriz = hacerMatrtiz(cantidadCasillasX, cantidadCasillasY);
        for (int i = 0; i < cantidadCasillasX; i++) {
            for (int j = 0; j < cantidadCasillasY; j++) {
                Casilla c = tableroMatriz[i][j];
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 400 / cantidadCasillasX, (double) 400 / cantidadCasillasY);
                celdaButton.setMaxSize((double) 400 / cantidadCasillasX, (double) 400 / cantidadCasillasY);
                celdaButton.setStyle("-fx-border-color: #3385fa; -fx-text-alignment: center;");
                celdaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));

                        try {
                            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
                            stage.setTitle("Propiedades de la celda ("+c.getCoordenadaX()+","+c.getCoordenadaY()+")");

                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                tableroFinal.add(celdaButton, i, j);
            }
        }
    }


    @FXML
    protected void onFinalizarButton() {
        Stage stageAnterior = (Stage) finalizarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaFinalizarJuego.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Fin del Juego");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}