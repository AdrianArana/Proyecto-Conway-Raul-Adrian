package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaJuegoController extends FuncionesBucle implements Initializable {
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaJuego;
    int turnoActual;
    //Creamos el tablero vacío, que se generará despues
    ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = new ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>();

    public void setParametros(ParametrosIndividuoModelProperties parametrosIndividuo, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        this.parametrosEntorno = parametrosEntorno;
        this.parametrosIndividuo = parametrosIndividuo;
        this.parametrosCasillas = parametrosCasillas;
    }

    public void setStage(Stage escenaDada) {
        this.escenaJuego = escenaDada;
    }

    @FXML
    public GridPane gridPane;
    @FXML
    public Button finalizarButton;
    @FXML
    public Button pauseButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ponerle algo
        System.out.println("initi");
    }

    //ACCION AL TOCAR UN BOTON
    @FXML
    protected void onBotonCelda(int i, int j) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));
        try {
            //Casilla accesible, para poder mostrar sus datos
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
            stage.setTitle("Propiedades de la celda: (" + tablero.getElemento(j).getData().getElemento(i).getData().getCoordenadaX() + "," + tablero.getElemento(j).getData().getElemento(i).getData().getCoordenadaY() + ")");
            stage.setScene(scene);
            VentanaCasillaController ventanaCasillaController = fxmlLoader.getController();
            ventanaCasillaController.setStage(stage);

            //Le mandamos al controlador los parametros deseados
            ventanaCasillaController.setParametros(tablero.getElemento(j).getData().getElemento(i).getData(), parametrosIndividuo, parametrosEntorno, turnoActual);//TODO-> Generar id coger el anterior para que no haya ids repetidos
            ventanaCasillaController.cogerValoresIniciales();
            ventanaCasillaController.mostrarInfo();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void rellenarGridpane() {
        int x = parametrosCasillas.x().getValue().intValue();
        int y = parametrosCasillas.y().getValue().intValue();
        for (int i = y; i > 0; i--) {
            for (int j = 0; j < x; j++) {
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 600 / x, (double) 600 / y);
                celdaButton.setMaxSize((double) 600 / x, (double) 600 / y);
                celdaButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                int finalI = j;
                int finalJ = i;
                celdaButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        onBotonCelda(finalI, finalJ-1);
                    }
                });
                gridPane.add(celdaButton, finalI, finalJ-1);
                tablero.getElemento(finalJ-1).getData().getElemento(finalI).getData().setBoton(celdaButton);
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
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
            stage.setTitle("Fin del Juego");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onPauseButton() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaConfiguracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
            stage.setTitle("Configuración de parámetros");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onPlayButton() {
        turnoActual++;
        recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal());
    }

    public void crearTableroDeJuego() {
        for (int y = 0; y < parametrosCasillas.y().getValue().intValue(); y++) {
            ListaEnlazadaColumnas<Casilla> filaCompleta = new ListaEnlazadaColumnas<Casilla>();
            for (int x = 1; x <= parametrosCasillas.x().getValue().intValue(); x++) {
                ElementoCasillaLE<Casilla> casillaNueva = new ElementoCasillaLE<Casilla>(new Casilla(x, parametrosCasillas.y().getValue().intValue() - y));


                //ElementoLE<Individuo> individuoActual=new ElementoLE<Individuo>(new Individuo());
                //ListaEnlazada<Individuo> individuos= new ListaEnlazada<Individuo>(individuoActual);
                //casillaNueva.getData().setIndividuos(individuos);
                filaCompleta.add(casillaNueva);

            }
            tablero.add(new ElementoListaColumnasLE<ListaEnlazadaColumnas<Casilla>>(filaCompleta));
        }
    }

    //EjecutarBucle() al tocar un boton
    //checkear
    //matar
    //clonar
    //Se ejecuta el bucle de la clase simoplemente llamando a este
}
