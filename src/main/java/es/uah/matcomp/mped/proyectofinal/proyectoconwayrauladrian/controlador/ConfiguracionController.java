package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
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
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class ConfiguracionController implements Initializable {
    public Slider sliderCasillasX;
    public Slider sliderCasillasY;
    public Label labelValorSliderCasillasX;
    public Label labelValorSliderCasillasY;
    public Slider sliderProbabilidadGeneral;
    public Slider sliderTiempoAparicion;
    public Label labelTiempoAparicion;
    public Tab tabTablero;


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

    //Individuo, variables observables para los parámetros de los individuos
    protected IntegerProperty medidaTurnosDeVida = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaProbabilidadMuerte = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaprobabilidadClonacion = new SimpleIntegerProperty(0);
    protected IntegerProperty medidaprobabilidadReproduccion = new SimpleIntegerProperty(0);

    //Entorno, variables observables para recursos
    protected IntegerProperty medidatiempoAparicion = new SimpleIntegerProperty(0);

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
    private ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero;
    private String nombreGuardadoString;


    protected void updateGUIwithModel() {
        //Bindeamos los sliders del individuo
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(parametrosIndividuo.turnosVidaRestantesProperty());
        sliderProbabilidadMuerte.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadMuerteProperty());
        sliderProbabilidadClonacion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadClonacionProperty());
        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadReproduccionProperty());
        //Bindeamos los sliders de del entorno
        sliderTiempoAparicion.valueProperty().bindBidirectional(parametrosEntorno.tiempoAparicion());
        sliderProbabilidadGeneral.valueProperty().bindBidirectional(parametrosEntorno.probabilidadGeneral());
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
        log.info("Inicialización en ejecución del controlador de parámetros");
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
        sliderTiempoAparicion.valueProperty().bindBidirectional(medidatiempoAparicion);
        labelTiempoAparicion.textProperty().bind(medidatiempoAparicion.asString());

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
        log.info ("Saliendo del initialize del configuracionController");
    }

    @FXML
    protected void onBotonGuardarClick() {
        log.info ("Se ha pulsado el boton guardar en ConfiguracionController");
        parametrosIndividuo.commit();
        parametrosEntorno.commit();
        parametrosCasillas.commit();
    }

    @FXML
    protected void onBotonReiniciarClick() {
        log.info ("Se ha pulsado el boton reiniciar en ConfiguracionController");
        parametrosIndividuo.rollback();
    }

    @FXML
    protected void onBotonReiniciarEntornoClick() {
        log.info ("Se ha pulsado el boton reiniciar en ConfiguracionController");
        parametrosEntorno.rollback();
    }

    @FXML
    public void onBotonReiniciarCasillasClick() {
        log.info ("Se ha pulsado el boton reiniciar en ConfiguracionController");
        parametrosCasillas.rollback();
    }

    @FXML
    protected void onBotonCerrarEntornoClick() {
        log.info ("Se ha pulsado el boton cerrar en ConfiguracionController");
        escenaParametros.close();
    }


    @FXML
    protected void onBotonCerrarClick() {
        log.info ("Se ha pulsado el boton cerrar en ConfiguracionController");
        escenaParametros.close();
    }

    public void loadUserData(String nombreGuardadoDado, ParametrosIndividuoModelProperties parametrosIndividuoDados, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        this.nombreGuardadoString=nombreGuardadoDado;
        this.parametrosIndividuo = parametrosIndividuoDados;
        this.parametrosEntorno = parametrosEntorno;
        this.parametrosCasillas = parametrosCasillas;
        this.updateGUIwithModel();
    }

    //Para pasarlos a la siguiente ventana


    public void setStage(Stage escenaDada) {
        this.escenaParametros = escenaDada;
    }

    public void onIniciarPartidaButtonClick() {
        log.info ("Se ha pulsado el boton iniciar partida en ConfiguracionController");
        ParametrosCasillas original = parametrosCasillas.getOriginal();
        ParametrosCasillasModelProperties modeloParaGUICompartidoTablero = new ParametrosCasillasModelProperties(original);
        ParametrosEntorno EntornoOriginal = parametrosEntorno.getOriginal();
        ParametrosEntornoModelProperties modeloParaGUICompartidoEntorno = new ParametrosEntornoModelProperties(EntornoOriginal);
        ParametrosIndividuo IndividuoOriginal = parametrosIndividuo.getOriginal();
        ParametrosIndividuoModelProperties modeloParaGUICompartidoIndividuo = new ParametrosIndividuoModelProperties(IndividuoOriginal);

        System.out.println(parametrosCasillas.x().getValue().intValue());
        //Cerramos la ventana anterior
        Stage stageAnterior = (Stage) botonIniciarPartida.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaJuego.fxml"));
        try {

            Scene scene = new Scene(fxmlLoader.load(), 1100, 800);//Aquí se hace el initialize
            stage.setScene(scene);
            VentanaJuegoController ventanaJuegoController = fxmlLoader.getController();
            //Le mandamos los parámetros al controlador de la siguiente ventana
            ventanaJuegoController.setParametros(nombreGuardadoString,modeloParaGUICompartidoIndividuo, modeloParaGUICompartidoEntorno, modeloParaGUICompartidoTablero);
            //Ahora se crea el tablero con la lista de listas, y se rellena el gridPane previamente vacío
            ventanaJuegoController.hacerTablero();
            ventanaJuegoController.setStage(stage);
            ventanaJuegoController.elegirColorGridPane();
            ventanaJuegoController.setGrafica();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosInGame(VentanaJuegoController controlador, ParametrosIndividuoModelProperties parametrosIndividuoDados, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        log.info ("Entrando al método de cargarDatos en la partida");
        this.parametrosEntorno = parametrosEntorno;
        this.parametrosIndividuo = parametrosIndividuoDados;
        this.parametrosCasillas = parametrosCasillas;
        tabTablero.setDisable(true);
        this.updateGUIwithModel();
        botonIniciarPartida.setText("Reanudar");
        botonIniciarPartida.setStyle("-fx-alignment: center;");
        botonIniciarPartida.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                onBotonReanudarClick(controlador);
            }
        });
    }

    private void onBotonReanudarClick(VentanaJuegoController ventanaJuegoController) {
        log.info ("Se ha pulsado el boton reanudar en ConfiguracionController");
        ventanaJuegoController.setParametros(nombreGuardadoString, parametrosIndividuo,parametrosEntorno,parametrosCasillas);
        Stage stageAnterior = (Stage) botonIniciarPartida.getScene().getWindow();
        stageAnterior.close();
    }


}