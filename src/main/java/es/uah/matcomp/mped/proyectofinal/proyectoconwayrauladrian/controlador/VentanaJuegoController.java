package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Tablero;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle;
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
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class VentanaJuegoController extends FuncionesBucle implements Initializable {
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaJuego;

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
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 400 / x, (double) 400 / y);
                celdaButton.setMaxSize((double) 400 / x, (double) 400 / y);
                celdaButton.setStyle("-fx-border-color: #3385fa; -fx-text-alignment: center;");
                int finalI = i;
                int finalJ = j;
                celdaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));
                        try {
                            //Casilla accesible, para poder mostrar sus datos
                            Casilla casillaActual=tablero.getElemento(finalI).getData().getElemento(finalJ).getData();
                            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
                            stage.setTitle("Propiedades de la celda: (" + casillaActual.getCoordenadaX() + "," + casillaActual.getCoordenadaY() + ")");


                            VentanaCasillaController ventanaCasillActual = fxmlLoader.getController();
                            ventanaCasillActual.setLabel1("Probabilidad de clonacion: "+casillaActual.getIndividuos().getPrimero().getData().getProbabilidadClonacion() + "," + casillaActual.getCoordenadaY() + ")");


                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                gridPane.add(celdaButton, i, j);
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


    //TODO-> Borrar esta funcion, es solo temporal y valdra para probar metodos hasta que este todo finalizado
    public void onPlayButton() {
        recorrerIndividuos(tablero);
    }

    public void crearTableroDeJuego() {
        for (int x = 1; x <= parametrosCasillas.x().getValue().intValue(); x++) {
            ListaEnlazadaColumnas<Casilla> filaCompleta = new ListaEnlazadaColumnas<Casilla>();
            for (int y = 1; y <= parametrosCasillas.y().getValue().intValue(); y++) {
                ElementoCasillaLE<Casilla> casillaNueva = new ElementoCasillaLE<Casilla>(new Casilla(x, y));
                ElementoLE<Individuo> individuoActual=new ElementoLE<Individuo>(new Individuo());
                ListaEnlazada<Individuo> individuos= new ListaEnlazada<Individuo>(individuoActual);
                casillaNueva.getData().setIndividuos(individuos);
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
