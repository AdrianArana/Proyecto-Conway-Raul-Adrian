package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.Tablero;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguracionController implements Initializable {
    @FXML
    private Label labelValorSliderTurnosDeVidaRestantes;
    @FXML
    private Slider sliderTurnosDeVidaRestantes;
    @FXML
    private Slider sliderProbabilidadMuerte;
    @FXML
    private Slider sliderProbabilidadClonacion;
    @FXML
    private Slider sliderProbabilidadReproduccion;
    @FXML
    private Button botonIniciarPartida;

    //todo

    //Creamos un modelo de la clase observable
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private Stage escenaParametros;

    protected void updateGUIwithModel() {
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(parametrosIndividuo.turnosVidaRestantesProperty());
        sliderProbabilidadMuerte.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadMuerteProperty());
        sliderProbabilidadClonacion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadClonacionProperty());
        sliderProbabilidadReproduccion.valueProperty().bindBidirectional(parametrosIndividuo.probabilidadReproduccionProperty());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");
        //sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(medida);
        //todo sliderProbabilidadMuerte.valueProperty().bindBidirectional(medidaMuerte);
        //labelValorSliderTurnosDeVidaRestantes.textProperty().bind(medida.asString());
        if (parametrosIndividuo != null) {
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
    protected void onBotonCerrarClick() {
        escenaParametros.close();
    }

    public void loadUserData(ParametrosIndividuoModelProperties parametrosIndividuoDados) {
        this.parametrosIndividuo = parametrosIndividuoDados;
        this.updateGUIwithModel();
    }

    public void setStage(Stage escenaDada) {
        this.escenaParametros = escenaDada;
    }

    public void onIniciarPartidaButtonClick(ActionEvent actionEvent) {
        Stage stageAnterior = (Stage) botonIniciarPartida.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaJuego.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Juego - La vida de Conway");
            stage.setScene(scene);
            Tablero tablero = new Tablero(20,10);
            tablero.mostrarGridpane(stage);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}