package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class ParametrosEntornoModelProperties {

    protected ParametrosEntorno valoresOriginales;

    private IntegerProperty probabilidadAgua = new SimpleIntegerProperty();
    private IntegerProperty probabilidadBiblioteca = new SimpleIntegerProperty();
    private IntegerProperty probabilidadComida = new SimpleIntegerProperty();
    private IntegerProperty probabilidadMontaña = new SimpleIntegerProperty();
    private IntegerProperty probabilidadPozo = new SimpleIntegerProperty();
    private IntegerProperty probabilidadTesoro = new SimpleIntegerProperty();


    public ParametrosEntornoModelProperties(ParametrosEntorno original){
        setOriginal(original);
    }

    public void commit(){
        valoresOriginales.setProbabilidadAgua(probabilidadAgua.get());
        valoresOriginales.setProbabilidadBiblioteca(probabilidadBiblioteca.get());
        valoresOriginales.setProbabilidadComida(probabilidadComida.get());
        valoresOriginales.setProbabilidadMontaña(probabilidadMontaña.get());
        valoresOriginales.setProbabilidadPozo(probabilidadPozo.get());
        valoresOriginales.setProbabilidadTesoro(probabilidadTesoro.get());
    }

    public void rollback(){
        probabilidadAgua.set(valoresOriginales.getProbabilidadAgua());
        probabilidadBiblioteca.set(valoresOriginales.getProbabilidadBiblioteca());
        probabilidadComida.set(valoresOriginales.getProbabilidadComida());
        probabilidadMontaña.set(valoresOriginales.getProbabilidadMontaña());
        probabilidadPozo.set(valoresOriginales.getProbabilidadPozo());
        probabilidadTesoro.set(valoresOriginales.getProbabilidadTesoro());
    }


    public void setOriginal(ParametrosEntorno original){
        this.valoresOriginales = original;
        rollback();
    }

    public ParametrosEntorno getOriginal() {
        return valoresOriginales;
    }

    public Property<Number> probabilidadAgua(){return probabilidadAgua;}
    public Property<Number> probabilidadBiblioteca(){return probabilidadBiblioteca;}
    public Property<Number> probabilidadComida(){return probabilidadComida;}
    public Property<Number> probabilidadMontaña(){return probabilidadMontaña;}
    public Property<Number> probabilidadPozo(){return probabilidadPozo;}
    public Property<Number> probabilidadTesoro(){return probabilidadTesoro;}
}
