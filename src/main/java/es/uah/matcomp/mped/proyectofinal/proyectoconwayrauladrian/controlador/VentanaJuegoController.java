package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import java.time.LocalDateTime;
import java.time.ZoneId;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static java.time.LocalTime.now;

public class VentanaJuegoController extends FuncionesBucle implements Initializable {
    public Slider sliderVelocidad;
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
        sliderVelocidad.valueProperty().bindBidirectional(velocidadDeJuego);
    }

    //ACCION AL TOCAR UN BOTON
    @FXML
    protected void onBotonCelda(int i, int j) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));
        try {
            //Casilla accesible, para poder mostrar sus datos
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
            stage.setTitle("Propiedades de la celda: (" + tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getCoordenadaX() + "," + tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getCoordenadaY() + ")");
            stage.setScene(scene);
            VentanaCasillaController ventanaCasillaController = fxmlLoader.getController();
            ventanaCasillaController.setStage(stage);

            //Le mandamos al controlador los parametros deseados
            ventanaCasillaController.setParametros(tablero,tablero.getElemento(i - 1).getData().getElemento(j - 1).getData(), parametrosIndividuo, parametrosEntorno, turnoActual);//TODO-> Generar id coger el anterior para que no haya ids repetidos
            ventanaCasillaController.cogerValoresIniciales();
            ventanaCasillaController.mostrarInfo();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean parar = false;
    protected IntegerProperty velocidadDeJuego = new SimpleIntegerProperty(1);

    public static long getSegundos() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private void activarBucle() {
        long tiempoInicio = getSegundos();
        System.out.println("Tiempo actuañ" + LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());

        while (!parar) {
            System.out.println(getSegundos());
            if (getSegundos() > (tiempoInicio + 4)) {
                turnoActual++;
                System.out.println("hola");
                recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal());

                tiempoInicio = getSegundos();

            }

        }
    }



    public void hacerTablero() {
        int x = parametrosCasillas.x().getValue().intValue();
        int y = parametrosCasillas.y().getValue().intValue();
        for (int i = 1; i <= x; i++) {
            ListaEnlazadaColumnas<Casilla> filaCompleta = new ListaEnlazadaColumnas<>();
            for (int j = 1; j <= y; j++) {
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 600 / x, (double) 600 / y);
                celdaButton.setMaxSize((double) 600 / x, (double) 600 / y);
                celdaButton.setStyle("-fx-border-color: #000000; -fx-text-alignment: center;");
                int finalI = i;
                int finalJ = j;
                celdaButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        onBotonCelda(finalI, finalJ);
                    }
                });
                gridPane.add(celdaButton, finalI, finalJ);
                ElementoCasillaLE<Casilla> casillaNueva = new ElementoCasillaLE<>(new Casilla(finalI, finalJ));
                casillaNueva.getData().setBoton(celdaButton);

                //ElementoLE<Individuo> individuoActual=new ElementoLE<Individuo>(new Individuo());
                //ListaEnlazada<Individuo> individuos= new ListaEnlazada<Individuo>(individuoActual);
                //casillaNueva.getData().setIndividuos(individuos);
                filaCompleta.add(casillaNueva);

            }
            tablero.add(new ElementoListaColumnasLE<ListaEnlazadaColumnas<Casilla>>(filaCompleta));
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
        parar = true;
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
        recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal());
    }

    public void onBotonComenzar(ActionEvent actionEvent) {
        //activarBucle();
    }


    //EjecutarBucle() al tocar un boton
    //checkear
    //matar
    //clonar
    //Se ejecuta el bucle de la clase simoplemente llamando a este
}
