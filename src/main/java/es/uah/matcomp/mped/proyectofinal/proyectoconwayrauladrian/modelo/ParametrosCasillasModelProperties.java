package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class ParametrosCasillasModelProperties {
    //Parametros del tablero ahora terminados
    protected ParametrosCasillas valoresOriginales;

    private IntegerProperty x = new SimpleIntegerProperty();
    private IntegerProperty y = new SimpleIntegerProperty();

    //Constructor
    public ParametrosCasillasModelProperties(ParametrosCasillas original){
        setOriginal(original);
    }

    public void commit(){
        valoresOriginales.setX(x.get());
        valoresOriginales.setY(y.get());
    }

    public void rollback(){
        x.set(valoresOriginales.getX());
        y.set(valoresOriginales.getY());

    }

    public ParametrosCasillas getOriginal(){return valoresOriginales;}

    public void setOriginal(ParametrosCasillas original){
        this.valoresOriginales = original;
        rollback();
    }

    public Property<Number> x(){return x;}
    public Property<Number> y(){return y;}
}
