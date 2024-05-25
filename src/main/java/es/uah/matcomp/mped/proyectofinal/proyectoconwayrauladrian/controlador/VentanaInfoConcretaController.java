package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;

import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import javafx.scene.control.Label;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;

public class VentanaInfoConcretaController {


    public Label label1;
    public Label label2;
    public Label label3;
    public Label labelCasillaElegida;

    public void setIndividuos(ListaEnlazada<Individuo> individuos, int x, int y) {
        log.info ("Entrando al método de setIndividuos VentanaConcretaController");
        labelCasillaElegida.setText("Individuos de la casilla: "+x+","+y);
        if (!individuos.isVacia()) {
            if (individuos.getElemento(0) != null) {
                label1.setText(individuos.getElemento(0).getData().toString());
            }
            if (individuos.getElemento(1) != null) {
                label2.setText(individuos.getElemento(1).getData().toString());
            }
            if (individuos.getElemento(2) != null) {
                label3.setText(individuos.getElemento(2).getData().toString());
            }
        }
    }
}
