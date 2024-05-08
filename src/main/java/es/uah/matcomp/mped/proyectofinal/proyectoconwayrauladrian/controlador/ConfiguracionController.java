package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

    protected IntegerProperty medida = new SimpleIntegerProperty(0);

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
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(medida);
        labelValorSliderTurnosDeVidaRestantes.textProperty().bind(medida.asString());
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


}