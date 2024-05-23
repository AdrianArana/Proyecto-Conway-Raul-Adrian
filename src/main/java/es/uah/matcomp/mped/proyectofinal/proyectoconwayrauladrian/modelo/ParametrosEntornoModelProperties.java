package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import com.google.gson.annotations.Expose;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class ParametrosEntornoModelProperties {
    @Expose
    public ParametrosEntorno valoresOriginales;
    private IntegerProperty tiempoAparicion = new SimpleIntegerProperty();
    private IntegerProperty probabilidadAgua = new SimpleIntegerProperty();
    private IntegerProperty probabilidadBiblioteca = new SimpleIntegerProperty();
    private IntegerProperty probabilidadComida = new SimpleIntegerProperty();
    private IntegerProperty probabilidadMontaña = new SimpleIntegerProperty();
    private IntegerProperty probabilidadPozo = new SimpleIntegerProperty();
    private IntegerProperty probabilidadTesoro = new SimpleIntegerProperty();
    private IntegerProperty probabilidadGeneral=new SimpleIntegerProperty();

    public ParametrosEntornoModelProperties(ParametrosEntorno original){
        setOriginal(original);
    }

    public void commit(){
        valoresOriginales.setTiempoAparicion(tiempoAparicion.get());
        valoresOriginales.setProbabilidadAgua(probabilidadAgua.get());
        valoresOriginales.setProbabilidadBiblioteca(probabilidadBiblioteca.get());
        valoresOriginales.setProbabilidadComida(probabilidadComida.get());
        valoresOriginales.setProbabilidadMontaña(probabilidadMontaña.get());
        valoresOriginales.setProbabilidadPozo(probabilidadPozo.get());
        valoresOriginales.setProbabilidadTesoro(probabilidadTesoro.get());
        valoresOriginales.setProbabilidadGeneral(probabilidadGeneral.get());
    }

    public void rollback(){
        tiempoAparicion.set(valoresOriginales.getTiempoAparicion());
        probabilidadAgua.set(valoresOriginales.getProbabilidadAgua());
        probabilidadBiblioteca.set(valoresOriginales.getProbabilidadBiblioteca());
        probabilidadComida.set(valoresOriginales.getProbabilidadComida());
        probabilidadMontaña.set(valoresOriginales.getProbabilidadMontaña());
        probabilidadPozo.set(valoresOriginales.getProbabilidadPozo());
        probabilidadTesoro.set(valoresOriginales.getProbabilidadTesoro());
        probabilidadGeneral.set(valoresOriginales.getProbabilidadGeneral());
    }


    public void setOriginal(ParametrosEntorno original){
        this.valoresOriginales = original;
        rollback();
    }

    public ParametrosEntorno getOriginal() {
        return valoresOriginales;
    }

    public Property<Number> tiempoAparicion(){return tiempoAparicion;}
    public Property<Number> probabilidadAgua(){return probabilidadAgua;}
    public Property<Number> probabilidadBiblioteca(){return probabilidadBiblioteca;}
    public Property<Number> probabilidadComida(){return probabilidadComida;}
    public Property<Number> probabilidadMontaña(){return probabilidadMontaña;}
    public Property<Number> probabilidadPozo(){return probabilidadPozo;}
    public Property<Number> probabilidadTesoro(){return probabilidadTesoro;}
    public Property<Number> probabilidadGeneral() {return probabilidadGeneral;}
}
