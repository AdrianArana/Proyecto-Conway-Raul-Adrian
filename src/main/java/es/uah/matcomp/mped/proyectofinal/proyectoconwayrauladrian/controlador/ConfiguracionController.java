package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Tablero;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.MatrixType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguracionController implements Initializable {
    public Slider sliderCasillasX;
    public Slider sliderCasillasY;
    public Label labelValorSliderCasillasX;
    public Label labelValorSliderCasillasY;


    @FXML
    private Label labelValorSliderTurnosDeVidaRestantes;
    @FXML
    private Label labelValorSliderProbabilidadMuerte;
    @FXML
    private Label laberValorSliderProbabilidadClonacion;
    @FXML
    private Label laborValorSliderProbabilidadReproduccion;
    @FXML
    private Label labelAgua;
    @FXML
    private Label labelBiblioteca;
    @FXML
    private Label labelComida;
    @FXML
    private Label labelMontaña;
    @FXML
    private Label labelPozo;
    @FXML
    private Label labelTesoro;
    @FXML
    private Slider sliderTurnosDeVidaRestantes;
    @FXML
    private Slider sliderProbabilidadMuerte;
    @FXML
    private Slider sliderProbabilidadClonacion;
    @FXML
    private Slider sliderProbabilidadReproduccion;
    @FXML
    private Slider sliderAgua;
    @FXML
    private Slider sliderBiblioteca;
    @FXML
    private Slider sliderMontaña;
    @FXML
    private Slider sliderComida;
    @FXML
    private Slider sliderPozo;
    @FXML
    private Slider sliderTesoro;
    @FXML
    private Button botonIniciarPartida;
    @FXML
    private Button botonVolver;

    //Individuo, variables observables para los parámetros de los individuos
    protected IntegerProperty medidaTurnosDeVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaProbabilidadMuerte = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaprobabilidadClonacion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaprobabilidadReproduccion = new SimpleIntegerProperty(0);

    //Entorno, variables observables para recursos
    protected IntegerProperty medidaAgua = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaBiblioteca = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaComida = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaMontaña = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaPozo = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaTesoro = new SimpleIntegerProperty(0);
    //Tablero, variables observables
    protected IntegerProperty medidaCasillasX = new SimpleIntegerProperty(0);

    protected IntegerProperty medidaCasillasY = new SimpleIntegerProperty(0);


    //Creamos un modelo de la clase observable
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaParametros;


    protected void updateGUIwithModel() {
        //Bindeamos los sliders del individuo
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(parametrosIndividuo.turnosVidaRestantesProperty());
        sliderProbabilidadMuerte.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadMuerteProperty());
        sliderProbabilidadClonacion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadClonacionProperty());
        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadReproduccionProperty());
        //Bindeamos los sliders de del entorno
        sliderAgua.valueProperty().bindBidirectional(parametrosEntorno.probabilidadAgua());
        sliderBiblioteca.valueProperty().bindBidirectional(parametrosEntorno.probabilidadBiblioteca());
        sliderComida.valueProperty().bindBidirectional(parametrosEntorno.probabilidadComida());
        sliderMontaña.valueProperty().bindBidirectional(parametrosEntorno.probabilidadMontaña());
        sliderPozo.valueProperty().bindBidirectional(parametrosEntorno.probabilidadPozo());
        sliderTesoro.valueProperty().bindBidirectional(parametrosEntorno.probabilidadTesoro());
        //Bindeamos los sliders del tablero
        sliderCasillasX.valueProperty().bindBidirectional(parametrosCasillas.x());
        sliderCasillasY.valueProperty().bindBidirectional(parametrosCasillas.y());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");
        //Sliders de los parámetros del individuo tipo
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(medidaTurnosDeVida);
        labelValorSliderTurnosDeVidaRestantes.textProperty().bind(medidaTurnosDeVida.asString());

        sliderProbabilidadMuerte.valueProperty().bindBidirectional(medidaProbabilidadMuerte);
        labelValorSliderProbabilidadMuerte.textProperty().bind(medidaProbabilidadMuerte.asString());

        sliderProbabilidadClonacion.valueProperty().bindBidirectional(medidaprobabilidadClonacion);
        laberValorSliderProbabilidadClonacion.textProperty().bind(medidaprobabilidadClonacion.asString());

        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(medidaprobabilidadReproduccion);
        laborValorSliderProbabilidadReproduccion.textProperty().bind(medidaprobabilidadReproduccion.asString());

        //Sliders para los recursos del entorno
        sliderAgua.valueProperty().bindBidirectional(medidaAgua);
        labelAgua.textProperty().bind(medidaAgua.asString());

        sliderBiblioteca.valueProperty().bindBidirectional(medidaBiblioteca);
        labelBiblioteca.textProperty().bind(medidaBiblioteca.asString());


        sliderComida.valueProperty().bindBidirectional(medidaComida);
        labelComida.textProperty().bind(medidaComida.asString());

        sliderMontaña.valueProperty().bindBidirectional(medidaMontaña);
        labelMontaña.textProperty().bind(medidaMontaña.asString());


        sliderPozo.valueProperty().bindBidirectional(medidaPozo);
        labelPozo.textProperty().bind(medidaPozo.asString());


        sliderTesoro.valueProperty().bindBidirectional(medidaTesoro);
        labelTesoro.textProperty().bind(medidaTesoro.asString());

        //Sliders de las casillas para el tablero, bindeamos las labels
        sliderCasillasX.valueProperty().bindBidirectional(medidaCasillasX);
        labelValorSliderCasillasX.textProperty().bind(medidaCasillasX.asString());

        sliderCasillasY.valueProperty().bindBidirectional(medidaCasillasY);
        labelValorSliderCasillasY.textProperty().bind(medidaCasillasY.asString());

        if (parametrosIndividuo != null && parametrosEntorno != null) {
            this.updateGUIwithModel();
        }

    }

    @FXML
    protected void onBotonGuardarClick() {
        parametrosIndividuo.commit();
    }

    @FXML
    protected void onBotonReiniciarClick() {
        parametrosIndividuo.rollback();
    }

    @FXML
    protected void onBotonReiniciarEntornoClick() {
        parametrosEntorno.rollback();
    }

    @FXML
    public void onBotonReiniciarCasillasClick() {
        parametrosCasillas.rollback();
    }

    @FXML

    protected void onBotonGuardarEntornoClick() {
        parametrosEntorno.commit();
    }

    public void onBotonGuardarCasillasClick() {
        parametrosCasillas.commit();
    }

    @FXML
    protected void onBotonCerrarEntornoClick() {
        escenaParametros.close();
    }


    @FXML
    protected void onBotonCerrarClick() {
        escenaParametros.close();
    }

    public void loadUserData(ParametrosIndividuoModelProperties parametrosIndividuoDados, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        this.parametrosIndividuo = parametrosIndividuoDados;
        this.parametrosEntorno = parametrosEntorno;
        this.parametrosCasillas = parametrosCasillas;
        this.updateGUIwithModel();
    }

    @FXML
    public void onVolver() {
        Stage stageAntiguo = (Stage) botonVolver.getScene().getWindow();
        stageAntiguo.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("nombre.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Nombre");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setStage(Stage escenaDada) {
        this.escenaParametros = escenaDada;
    }

    public void onIniciarPartidaButtonClick() {
        //Cerramos la ventana anterior
        Stage stageAnterior = (Stage) botonIniciarPartida.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaJuego.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), parametrosCasillas.x().getValue().byteValue()*10, 500);
            stage.setTitle("Juego - La vida de Conway");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}