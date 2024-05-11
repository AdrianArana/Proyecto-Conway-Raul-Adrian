package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Tablero;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController implements Initializable {
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaJuego;


    public void setParametros(ParametrosIndividuoModelProperties parametrosIndividuo, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        this.parametrosEntorno = parametrosEntorno;
        this.parametrosIndividuo = parametrosIndividuo;
        this.parametrosCasillas = parametrosCasillas;
    }

    public void setStage(Stage escenaDada) {
        this.escenaJuego = escenaDada;
    }

    @FXML
    public GridPane tableroFinal;

    private Casilla[][] hacerMatrtiz(int cantidadCasillasX, int cantidadCasillasY) {
        Tablero tablero = new Tablero(cantidadCasillasX, cantidadCasillasY);
        //Creamos una matriz para nuestro tablero
        return tablero.getCasillas();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ponerle algo
        System.out.println("initi");
    }

    public void crearMatriz() {
        int x = parametrosCasillas.x().getValue().intValue();
        int y = parametrosCasillas.y().getValue().intValue();
        Casilla[][] tableroMatriz = hacerMatrtiz(x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Casilla c = tableroMatriz[i][j];
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 400 / x, (double) 400 / y);
                celdaButton.setMaxSize((double) 400 / x, (double) 400 / y);
                celdaButton.setStyle("-fx-border-color: #3385fa; -fx-text-alignment: center;");
                celdaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));

                        try {
                            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
                            stage.setTitle("Propiedades de la celda (" + c.getCoordenadaX() + "," + c.getCoordenadaY() + ")");

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
}
