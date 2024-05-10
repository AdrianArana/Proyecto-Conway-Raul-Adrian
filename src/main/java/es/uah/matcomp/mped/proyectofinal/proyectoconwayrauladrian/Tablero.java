package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tablero {
    private Casilla[][] casillas;
    int xTablero;
    int yTablero;
    int xVentana=800;
    int yVentana=800;
    //Constructor del tablero con la clase Casilla
    public Tablero(int x,int y) {
        this.xTablero = x;
        this.yTablero = y;
        casillas = new Casilla[x][y];
        // Inicializa las celdas
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                casillas[i][j] = new Casilla(i,j);
            }
        }
    }

    public int getxTablero() {
        return xTablero;
    }

    public int getyTablero() {
        return yTablero;
    }

    public void mostrarGridpane(Stage primaryStage) {
        GridPane mainGrid = new GridPane();
        for (int i = 0; i < this.getxTablero(); i++) {
            for (int j = 0; j < this.getyTablero(); j++) {
                Casilla celda = casillas[i][j];
                Button casilla = new Button(celda.getCoordenadaX()+","+celda.getCoordenadaY()); // Mostrar datos de la celda
                casilla.setMinSize((double) xVentana /this.getxTablero(), (double) yVentana /this.getyTablero());
                casilla.setMaxSize((double) xVentana /this.getxTablero(), (double) yVentana /this.getyTablero());
                casilla.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                casilla.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //todo -> funcion ventana casilla
                        System.out.println("Click en celda " + celda.getCoordenadaX() + "," + celda.getCoordenadaY());
                    }
                });
                mainGrid.add(casilla, i, j);

                // OJO!: Tal como está programado, pierdo la referencia a los labels...
                //       Si los quisiese usar después, debería guardarlos de alguna manera en algún sitio
                // Pista: los quieres guardar para poder cambiar lo que aparece en pantalla :)
            }
        }
        VBox layout = new VBox();
        layout.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
        mainGrid.add(layout,15,15);
        Scene scene = new Scene(mainGrid, xVentana, yVentana);
        primaryStage.setTitle("Grid de 10x10 con Componentes Personalizados");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
