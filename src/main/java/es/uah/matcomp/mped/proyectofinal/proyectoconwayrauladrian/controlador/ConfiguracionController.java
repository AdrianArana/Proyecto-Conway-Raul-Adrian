package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguracionController implements Initializable {
    @FXML
    private Slider sliderTurnosDeVidaRestantes;
    private Slider sliderProbabilidadDeMuerte;
    private Slider sliderProbabilidadDeClonacion;
    private Slider sliderProbabilidadDeReproduccion;
    //Creamos un modelo de la clase observable
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private Stage escenaParametros;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    protected void updateGUIwithModel() {
        sliderTurnosDeVidaRestantes.valueProperty().bindBidirectional(parametrosIndividuo.turnosVidaRestantesProperty());
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
    protected void onBotonCerrarClick(){
        parametrosIndividuo.close();
    }
    public void loadUserData(ParametrosIndividuoModelProperties parametrosIndividuoDados) {
        this.parametrosIndividuo = parametrosIndividuoDados;
        this.updateGUIwithModel();
    }

    public void setStage(Stage escenaDada){
        this.escenaParametros = escenaDada;
    }

}