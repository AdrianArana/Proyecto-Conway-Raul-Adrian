package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.NombreGuardado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.cargar;

public class PrincipalController implements Initializable {

    @FXML
    private Label welcomeText;
    private Stage scene;
    public Button volverButton;
    public Button nuevaPartidaButton;
    public Button salirButton;
    String nombreGuardadoString;


    @FXML
    protected void onMiBotonNuevaPartidaButtonClick() {
        Stage stageAntiguo = (Stage) nuevaPartidaButton.getScene().getWindow();
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

    public void onSalirButtonClick() {
        Stage escenario = (Stage) salirButton.getScene().getWindow();
        escenario.close();
    }

    public void onCreditosButtonClick() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("creditos.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 750, 500);
            stage.setTitle("Créditos");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onVolverButtonClick() {
        Stage stage = (Stage) volverButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador\n");
    }

    public void onBotonCargarPartida(ActionEvent actionEvent) {
        String nombreArchivo = "datosGuardados.json";
        modeloDatosFinal datosCargados = cargar(nombreArchivo);
        //System.out.println();
        Stage stageAntiguo = (Stage) nuevaPartidaButton.getScene().getWindow();
        stageAntiguo.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaJuego.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
            stage.setTitle("Juego - La vida de Conway: PARTIDA DE "+datosCargados.getNombreGuardadoString().toUpperCase()+" CARGADA");
            stage.setScene(scene);
            VentanaJuegoController juegoController= fxmlLoader.getController();
            //System.out.println(datosCargados.getTablero().getElemento(1).getData().getElemento(1).getData().getIndividuos().getElemento(1).getData().toString());
            juegoController.setTablero(datosCargados.getTablero());
            System.out.println("nombre guardado+"+datosCargados);
            juegoController.setParametros(datosCargados.getNombreGuardadoString(), new ParametrosIndividuoModelProperties(datosCargados.getParametrosGuardadosIndividuos()),new ParametrosEntornoModelProperties(datosCargados.getParametrosGuardadosEntorno()),new ParametrosCasillasModelProperties(datosCargados.getParametrosGuardadosCasillas()));
            juegoController.setBotones();
            juegoController.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}