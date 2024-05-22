package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import java.time.LocalDateTime;
import java.time.ZoneId;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.guardar;
import static java.time.LocalTime.now;

public class VentanaJuegoController extends FuncionesBucle implements Initializable {
    public Slider sliderVelocidad;
    public Button botonBasico;
    public Button botonIntermedio;
    public Button botonAvanzado;
    public Button botonPintar;
    public ToolBar toolBoxIndividuos;
    public ToolBar toolBoxRecursos;
    public ColorPicker colorPicker;
    public BarChart graficaPantallaJuego;
    public Button botonPintarRecursos;
    public Label labelRecursoSeleccionado;
    public Button botonPausar;
    public Button botonReanudar;
    public Button botonAjustes;
    public Button botonAvance;
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaJuego;
    int turnoActual;
    boolean pintarIndividuos = false;
    int tipoPintar = 1;
    boolean pintarRecursos = false;
    int tipoPintarRecursos = 0;
    //Creamos el tablero vacío, que se generará despues
    ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = new ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>();

    String colorBordes = "rgb(0, 0, 0)";

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

    private ObjectProperty<Color> colorElegido = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sliderVelocidad.valueProperty().bindBidirectional(velocidadDeJuego);
        botonBasico.setStyle("-fx-background-color: #4a91fa;");
        botonPintar.setStyle("-fx-background-color: #ff0000;");
        botonPintar.setText("OFF");
        toolBoxIndividuos.setStyle("-fx-background-color: #4f5866;");
        toolBoxRecursos.setStyle("-fx-background-color: #4f5866;");
        botonPintarRecursos.setText("OFF");
        botonPintarRecursos.setStyle("-fx-background-color: #ff0000;");
        onBotonPausar();

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data<>("Category 1", 50));
        series1.getData().add(new XYChart.Data<>("Category 2", 80));
        series1.getData().add(new XYChart.Data<>("Category 3", 30));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Series 2");
        series2.getData().add(new XYChart.Data<>("Category 1", 60));
        series2.getData().add(new XYChart.Data<>("Category 2", 90));
        series2.getData().add(new XYChart.Data<>("Category 3", 40));

        graficaPantallaJuego.getData().addAll(series1, series2);
    }

    public void elegirColorGridPane() {
        colorPicker.setOnAction(event -> {
            Color color = colorPicker.getValue();
            colorElegido.set(color);
            colorBordes = pasaraRGB(color);
            for (int i = 0; i < tablero.getNumeroFilas(); i++) {
                for (int j = 0; j < tablero.getPrimero().getData().getNumeroColumnas(); j++) {
                    int numeroIndividuos = tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos();
                    int numeroRecursos = tablero.getElemento(i).getData().getElemento(j).getData().getRecursos().getNumeroElementos();
                    Casilla casilla = tablero.getElemento(i).getData().getElemento(j).getData();
                    if (numeroIndividuos > 0) {
                        if (numeroIndividuos == 1) {
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setStyle("-fx-background-color: #ffae00;-fx-border-color: " + colorBordes + ";");
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setText("1");
                        } else if (numeroIndividuos == 2) {
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setStyle("-fx-background-color: #ff4d00;-fx-border-color: " + colorBordes + ";");
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setText("2");

                        } else if (numeroIndividuos == 3) {
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setStyle("-fx-background-color: #ff0000;-fx-border-color: " + colorBordes + ";");
                            tablero.getElemento(i).getData().getElemento(j).getData().getBoton().setText("3");
                        }
                    } else {
                        actualizarCasillaRecurso(i, j, numeroRecursos, casilla);
                    }
                    if (numeroIndividuos == 0 && numeroRecursos == 0) {
                        casilla.getBoton().setStyle("-fx-background-color:null;-fx-border-color: " + colorBordes + ";");
                        casilla.getBoton().setText(null);
                    }
                    if (numeroIndividuos == 1) {
                        casilla.getBoton().setText("1");
                    } else if (numeroIndividuos == 2) {
                        casilla.getBoton().setText("2");
                    } else if (numeroIndividuos == 3) {
                        casilla.getBoton().setText("3");
                    }
                }
            }
        });
    }

    private void actualizarCasillaRecurso(int i, int j, int numeroRecursos, Casilla casilla) {
        if (numeroRecursos > 0) {
            for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getRecursos().getNumeroElementos(); k++) {
                Class<? extends Entorno> clase = casilla.getRecursos().getElemento(k).getData().getClass();
                if (clase == Pozo.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#0c0c0c;-fx-border-color: " + colorBordes + ";");
                }
                if (clase == Agua.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a8cce;-fx-border-color: " + colorBordes + ";");
                }
                if (clase == Biblioteca.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#cb82de;-fx-border-color: " + colorBordes + ";");
                }
                if (clase == Comida.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#f5a76e;-fx-border-color: " + colorBordes + ";");
                }
                if (clase == Montaña.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#7a4716;-fx-border-color: " + colorBordes + ";");
                }
                if (clase == Tesoro.class) {
                    casilla.getBoton().setText(null);
                    casilla.getBoton().setStyle("-fx-background-color:#ffcf3d;-fx-border-color: " + colorBordes + ";");
                }
            }
        }
    }

    private String pasaraRGB(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }


    //ACCION AL TOCAR UN BOTON
    @FXML
    protected void onBotonCelda(int i, int j) {

        if (!pintarIndividuos && !pintarRecursos) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaCasilla.fxml"));
            try {
                //Casilla accesible, para poder mostrar sus datos
                Scene scene = new Scene(fxmlLoader.load(), 700, 650);
                stage.setTitle("Propiedades de la celda: (" + tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getCoordenadaX() + "," + tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getCoordenadaY() + ")");
                stage.setScene(scene);
                VentanaCasillaController ventanaCasillaController = fxmlLoader.getController();
                ventanaCasillaController.setStage(stage);

                //Le mandamos al controlador los parametros deseados
                ventanaCasillaController.setParametros(tablero, tablero.getElemento(i - 1).getData().getElemento(j - 1).getData(), parametrosIndividuo, parametrosEntorno, turnoActual, colorBordes);//TODO-> Generar id coger el anterior para que no haya ids repetidos
                ventanaCasillaController.cogerValoresIniciales();
                ventanaCasillaController.mostrarInfo();

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (pintarRecursos) {
            if (tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getRecursos().getNumeroElementos() < 3) {
                int xRecurso = i - 1;
                int yRecurso = j - 1;
                if (tipoPintarRecursos != 0) {
                    Entorno recurso = pintarRecurso(tipoPintarRecursos, xRecurso, yRecurso);
                    tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getRecursos().add(recurso);
                    int numeroRecursos = tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getRecursos().getNumeroElementos();
                    Casilla casilla = tablero.getElemento(i - 1).getData().getElemento(j - 1).getData();
                    actualizarCasillaRecurso(i - 1, j - 1, numeroRecursos, casilla);
                }
            }
        } else if (pintarIndividuos) {
            if (tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getIndividuos().getNumeroElementos() < 3) {
                int xIndividuo = i - 1;
                int yIndividuo = j - 1;
                int id = generarID(tablero);
                int turnosDevidaRestantes = parametrosIndividuo.turnosVidaRestantesProperty().getValue().intValue();
                int turnoGeneracion = turnoActual;
                int probMuerte = parametrosIndividuo.probabilidadMuerteProperty().getValue().intValue();
                int probClonacion = parametrosIndividuo.probabilidadClonacionProperty().getValue().intValue();
                int probReproduccion = parametrosIndividuo.probabilidadReproduccionProperty().getValue().intValue();
                Individuo indivNuevo = new Individuo(xIndividuo, yIndividuo, id, tipoPintar, turnosDevidaRestantes, turnoGeneracion, probMuerte, probClonacion, probReproduccion);
                tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getIndividuos().add(indivNuevo);
                int numeroIndividuos = tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getIndividuos().getNumeroElementos();

                if (numeroIndividuos > 0) {
                    if (numeroIndividuos == 1) {
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setStyle("-fx-background-color: #ffae00;-fx-border-color: " + colorBordes + ";");
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setText("1");
                    } else if (numeroIndividuos == 2) {
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setStyle("-fx-background-color: #ff4d00;-fx-border-color: " + colorBordes + ";");
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setText("2");

                    } else if (numeroIndividuos == 3) {
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setStyle("-fx-background-color: #ff0000;-fx-border-color: " + colorBordes + ";");
                        tablero.getElemento(i - 1).getData().getElemento(j - 1).getData().getBoton().setText("3");
                    }
                }
            }
        }
    }

    public Entorno pintarRecurso(int tipo, int x, int y) {
        int xRecurso = x;
        int yRecurso = y;
        if (tipo == 1) {
            return new Pozo(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        } else if (tipo == 2) {
            return new Agua(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        } else if (tipo == 3) {
            return new Biblioteca(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        } else if (tipo == 4) {
            return new Comida(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        } else if (tipo == 5) {
            return new Montaña(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        } else if (tipo == 6) {
            return new Tesoro(xRecurso, yRecurso, parametrosEntorno.tiempoAparicion().getValue().intValue());
        }
        return null;
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
                celdaButton.setStyle("-fx-border-color:" + colorBordes + "; -fx-text-alignment: center;");
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
    protected void onBotonAjustes() {
        parar = true;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaConfiguracion.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 650);
            stage.setTitle("Configuración de parámetros 'in-game'");
            stage.setScene(scene);
            ConfiguracionController configuracionController = fxmlLoader.getController();
            configuracionController.cargarDatosInGame(this, parametrosIndividuo, parametrosEntorno, parametrosCasillas);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onAvanceButton() {
        turnoActual++;
        recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal(), parametrosIndividuo.getOriginal(), colorBordes);
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
                recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal(), parametrosIndividuo.getOriginal(), colorBordes);

                tiempoInicio = getSegundos();

            }

        }
    }


    public void onBotonBasico(ActionEvent actionEvent) {
        tipoPintar = 1;
        botonBasico.setStyle("-fx-background-color: #4a91fa;");
        botonIntermedio.setStyle(null);
        botonAvanzado.setStyle(null);
    }

    public void onBotonIntermedio(ActionEvent actionEvent) {
        tipoPintar = 2;
        botonBasico.setStyle(null);
        botonIntermedio.setStyle("-fx-background-color: #4a91fa;");
        botonAvanzado.setStyle(null);
    }

    public void onBotonAvanzado(ActionEvent actionEvent) {
        tipoPintar = 3;
        botonBasico.setStyle(null);
        botonIntermedio.setStyle(null);
        botonAvanzado.setStyle("-fx-background-color: #4a91fa;");
    }


    public void onPintarIndividuos(ActionEvent actionEvent) {
        if (pintarIndividuos) {
            pintarIndividuos = false;
            botonPintar.setStyle("-fx-background-color: #ff0000;");
            botonPintar.setText("OFF");
        } else {
            pintarIndividuos = true;
            pintarRecursos = false;
            botonPintarRecursos.setStyle("-fx-background-color: #ff0000;");
            botonPintarRecursos.setText("OFF");
            botonPintar.setStyle("-fx-background-color: #178b00;");
            botonPintar.setText("ON");
        }
    }


    public void onPintarRecursos(ActionEvent actionEvent) {

        if (pintarRecursos) {
            pintarRecursos = false;
            botonPintarRecursos.setStyle("-fx-background-color: #ff0000;");
            botonPintarRecursos.setText("OFF");
        } else {
            pintarRecursos = true;
            pintarIndividuos = false;
            botonPintar.setStyle("-fx-background-color: #ff0000;");
            botonPintar.setText("OFF");
            botonPintarRecursos.setStyle("-fx-background-color: #178b00;");
            botonPintarRecursos.setText("ON");
        }
    }


    public void onBotonPintarPozo(ActionEvent actionEvent) {
        tipoPintarRecursos = 1;
        labelRecursoSeleccionado.setText("Pozo");
    }

    public void onBotonPintarAgua(ActionEvent actionEvent) {
        labelRecursoSeleccionado.setText("Agua");
        tipoPintarRecursos = 2;
    }

    public void onBotonPintarBiblioteca(ActionEvent actionEvent) {
        labelRecursoSeleccionado.setText("Biblioteca");
        tipoPintarRecursos = 3;
    }

    public void onBotonPintarComida(ActionEvent actionEvent) {
        labelRecursoSeleccionado.setText("Comida");
        tipoPintarRecursos = 4;
    }

    public void onBotonPintarMontaña(ActionEvent actionEvent) {
        labelRecursoSeleccionado.setText("Montaña");
        tipoPintarRecursos = 5;
    }

    public void onBotonPintarTesoro(ActionEvent actionEvent) {
        labelRecursoSeleccionado.setText("Tesoro");
        tipoPintarRecursos = 6;
    }

    public void onBotonPausar() {
        parar = true;
        botonAvance.setDisable(false);
        botonAjustes.setDisable(false);
        botonPausar.setDisable(true);
        botonReanudar.setDisable(false);
    }

    public void onBotonReanudar() {
        parar = false;
        botonAvance.setDisable(true);
        botonAjustes.setDisable(true);
        botonReanudar.setDisable(true);
        botonPausar.setDisable(false);
    }

    public void onBotonGuardar(){
        guardar("tablero.json",tablero);
    }
}