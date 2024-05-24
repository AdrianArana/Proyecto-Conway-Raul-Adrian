package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.VistaPrincipal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.arbol.ArbolAVL;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.NombreGuardado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.guardar;

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
    public Label labelTurnoActual;
    private ParametrosEntornoModelProperties parametrosEntorno;
    private ParametrosIndividuoModelProperties parametrosIndividuo;
    private ParametrosCasillasModelProperties parametrosCasillas;
    private Stage escenaJuego;
    int turnoActual;
    IntegerProperty turnoActualObservable = new SimpleIntegerProperty(0);
    boolean pintarIndividuos = false;
    int tipoPintar = 1;
    boolean pintarRecursos = false;
    int tipoPintarRecursos = 0;
    private NombreGuardado nombreGuardado;
    boolean guardado = false;
    //Creamos el tablero vacío, que se generará despues
    ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = new ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>();

    String colorBordes = "rgb(0, 0, 0)";
    String nombreGuardadoString;


    public void setParametros(String nombreGuardado, ParametrosIndividuoModelProperties parametrosIndividuo, ParametrosEntornoModelProperties parametrosEntorno, ParametrosCasillasModelProperties parametrosCasillas) {
        this.nombreGuardadoString = nombreGuardado;
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
        log.info ("Inicializacion VentanaJuegoController");
        sliderVelocidad.valueProperty().bindBidirectional(velocidadDeJuego);
        botonBasico.setStyle("-fx-background-color: #4a91fa;");
        botonPintar.setStyle("-fx-background-color: #ff0000;");
        botonPintar.setText("OFF");
        toolBoxIndividuos.setStyle("-fx-background-color: #4f5866;");
        toolBoxRecursos.setStyle("-fx-background-color: #4f5866;");
        botonPintarRecursos.setText("OFF");
        botonPintarRecursos.setStyle("-fx-background-color: #ff0000;");
        labelTurnoActual.textProperty().bind(turnoActualObservable.asString());
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
        log.info ("Entrando al método de elegirColorGridPane VentanaJuegoController");
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

        log.info ("Saliendo del método de elegirColorGridPane VentanaJuegoController");
    }

    private void actualizarCasillaRecurso(int i, int j, int numeroRecursos, Casilla casilla) {
        log.info ("Entrando al método de actualizarCasillaRecurso VentanaJuegoController");
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
        log.info ("Saliendo del método de actualizarCasillaRecurso VentanaJuegoController");
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
        log.info ("Se ha pulsado el boton celda en VentanaJuegoController");

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
                int xRecurso = i;
                int yRecurso = j;
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
                int xIndividuo = i;
                int yIndividuo = j;
                int id = generarID(tablero);
                int turnosDevidaRestantes = parametrosIndividuo.turnosVidaRestantesProperty().getValue().intValue();
                int turnoGeneracion = turnoActual;
                int probMuerte = parametrosIndividuo.probabilidadMuerteProperty().getValue().intValue();
                int probClonacion = parametrosIndividuo.probabilidadClonacionProperty().getValue().intValue();
                int probReproduccion = parametrosIndividuo.probabilidadReproduccionProperty().getValue().intValue();
                Individuo indivNuevo = new Individuo(xIndividuo, yIndividuo, id, tipoPintar, turnosDevidaRestantes, turnoGeneracion, probMuerte, probClonacion, probReproduccion);


                indivNuevo.setArbolDelIndividuo(new ArbolAVL(indivNuevo));


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
        log.info ("Entrando al método de pintarRecurso en la partida");
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
        log.info ("Saliendo del método de pintarRecurso en la partida");
    }

    public void hacerTablero() {
        log.info ("Entrando al método de hacerTablero VentanaJuegoController");
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
        log.info ("Saliendo del método de hacerTablero VentanaJuegoController");
    }

    @FXML
    protected void onFinalizarButton() {
        log.info ("Se ha pulsado el boton finalizar en VentanaJuegoController");

        log.info("Saliendo de la ventana de juego");
        Stage stageAnterior = (Stage) finalizarButton.getScene().getWindow();
        stageAnterior.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaFinalizarJuego.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1100, 900);
            stage.setTitle("Fin del Juego");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBotonAjustes() {
        log.info ("Se ha pulsado el boton ajustes en VentanaJuegoController");

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
        log.info ("Se ha pulsado el boton avanzar en VentanaJuegoController");

        turnoActual++;
        turnoActualObservable.set(turnoActual);
        recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal(), parametrosIndividuo.getOriginal(), colorBordes);
    }


    boolean parar = false;
    protected IntegerProperty velocidadDeJuego = new SimpleIntegerProperty(1);

    public static long getSegundos() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private void activarBucle() {
        log.info ("Entrando al método de activarBucle VentanaJuegoController");

        long tiempoInicio = getSegundos();
        //tem.out.println("Tiempo actuañ" + LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        while (!parar) {
            //System.out.println(getSegundos());
            if (getSegundos() > (tiempoInicio + 4)) {
                turnoActual++;
                //System.out.println("hola");
                recorrerCasillas(tablero, turnoActual, parametrosEntorno.getOriginal(), parametrosIndividuo.getOriginal(), colorBordes);

                tiempoInicio = getSegundos();

            }

        }
        log.info ("Saliendo del método de activarBucle VentanaJuegoController");
    }


    public void onBotonBasico(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton individuo basico en VentanaJuegoController");
        tipoPintar = 1;
        botonBasico.setStyle("-fx-background-color: #4a91fa;");
        botonIntermedio.setStyle(null);
        botonAvanzado.setStyle(null);
    }

    public void onBotonIntermedio(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton individuo intermedio en VentanaJuegoController");
        tipoPintar = 2;
        botonBasico.setStyle(null);
        botonIntermedio.setStyle("-fx-background-color: #4a91fa;");
        botonAvanzado.setStyle(null);
    }

    public void onBotonAvanzado(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton individuo avanzado en VentanaJuegoController");
        tipoPintar = 3;
        botonBasico.setStyle(null);
        botonIntermedio.setStyle(null);
        botonAvanzado.setStyle("-fx-background-color: #4a91fa;");
    }


    public void onPintarIndividuos(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarIndividuos en VentanaJuegoController");

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
        log.info ("Se ha pulsado el boton pintarRecursos en VentanaJuegoController");

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
        log.info ("Se ha pulsado el boton pintarPozo en VentanaJuegoController");
        tipoPintarRecursos = 1;
        labelRecursoSeleccionado.setText("Pozo");
    }

    public void onBotonPintarAgua(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarAgua en VentanaJuegoController");
        labelRecursoSeleccionado.setText("Agua");
        tipoPintarRecursos = 2;
    }

    public void onBotonPintarBiblioteca(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarBiblioteca en VentanaJuegoController");
        labelRecursoSeleccionado.setText("Biblioteca");
        tipoPintarRecursos = 3;
    }

    public void onBotonPintarComida(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarComida en VentanaJuegoController");
        labelRecursoSeleccionado.setText("Comida");
        tipoPintarRecursos = 4;
    }

    public void onBotonPintarMontaña(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarMontaña en VentanaJuegoController");
        labelRecursoSeleccionado.setText("Montaña");
        tipoPintarRecursos = 5;
    }

    public void onBotonPintarTesoro(ActionEvent actionEvent) {
        log.info ("Se ha pulsado el boton pintarTesoro en VentanaJuegoController");
        labelRecursoSeleccionado.setText("Tesoro");
        tipoPintarRecursos = 6;
    }

    public void onBotonPausar() {
        log.info ("Se ha pulsado el boton pausar en VentanaJuegoController");
        parar = true;
        botonAvance.setDisable(false);
        botonAjustes.setDisable(false);
        botonPausar.setDisable(true);
        botonReanudar.setDisable(false);
    }

    public void onBotonReanudar() {
        log.info ("Se ha pulsado el boton reanucar en VentanaJuegoController");
        parar = false;
        botonAvance.setDisable(true);
        botonAjustes.setDisable(true);
        botonReanudar.setDisable(true);
        botonPausar.setDisable(false);
    }

    public void onBotonGuardar() {
        log.info ("Se ha pulsado el boton guardar en VentanaJuegoController");
        guardado = true;
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(VistaPrincipal.class.getResource("ventanaGuardarPartida.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 850, 750);
            stage.setScene(scene);
            VentanaGuardarPartidaController controladorGuardarPartida = fxmlLoader.getController();
            controladorGuardarPartida.setModeloDatosFinal(new modeloDatosFinal(nombreGuardadoString, tablero, parametrosIndividuo.getOriginal(), parametrosEntorno.getOriginal(), parametrosCasillas.getOriginal()));
            controladorGuardarPartida.setStage(stage);
            stage.show();
            guardar("datosGuardados.json", new modeloDatosFinal(nombreGuardadoString, tablero, parametrosIndividuo.getOriginal(), parametrosEntorno.getOriginal(), parametrosCasillas.getOriginal()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTablero(ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tableroNoEstatico) {
        this.tablero = tableroNoEstatico;
    }

    public void setBotones() {
        log.info ("Entrando al método de setBotones VentanaJuegoController");

        int x = parametrosCasillas.x().getValue().intValue();
        int y = parametrosCasillas.y().getValue().intValue();
        //System.out.println("valores cargados"+ parametrosCasillas.x().getValue().intValue()+"casillas");
        //System.out.println("Valores cargados +"+tablero.getElemento(1).getData().getNumeroColumnas());
        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            for (int j = 0; j < tablero.getElemento(1).getData().getNumeroColumnas(); j++) {
                if (!tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().isVacia()) {
                    for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getNumeroElementos(); k++) {
                        //System.out.println(tablero.getElemento(i).getData().getElemento(j).getData().getIndividuos().getElemento(k).getData().toString());
                    }
                }
                if (!tablero.getElemento(i).getData().getElemento(j).getData().getRecursos().isVacia()) {
                    for (int k = 0; k < tablero.getElemento(i).getData().getElemento(j).getData().getRecursos().getNumeroElementos(); k++) {
                        System.out.println(tablero.getElemento(i).getData().getElemento(j).getData().getRecursos().getElemento(k).getData().toString());
                    }
                }
            }
        }
        //tem.out.println("Caisllas del ytabeñlrp"+tablero.getElemento(1).getData().getElemento(1).getData().getClass());
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Button celdaButton = new Button();
                celdaButton.setMinSize((double) 600 / x, (double) 600 / y);
                celdaButton.setMaxSize((double) 600 / x, (double) 600 / y);
                celdaButton.setStyle("-fx-border-color:" + colorBordes + "; -fx-text-alignment: center;");
                int finalI = i + 1;
                int finalJ = j + 1;
                celdaButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        onBotonCelda(finalI, finalJ);
                    }
                });
                gridPane.add(celdaButton, finalI, finalJ);
                tablero.getElemento(i).getData().getElemento(j).getData().setBoton(celdaButton);
            }
        }
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
        log.info ("Saliendo del método de setBotones VentanaJuegoController");
    }
}
