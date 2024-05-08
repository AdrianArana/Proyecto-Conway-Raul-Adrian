package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import javafx.beans.property.*;

public class ParametrosIndividuoModelProperties {
    //Modelo de datos original
    protected ParametrosIndividuo valoresOriginales;

    private IntegerProperty turnosVidaRestantes = new SimpleIntegerProperty();
    private IntegerProperty probabilidadMuerte = new SimpleIntegerProperty();
    private IntegerProperty probabilidadClonacion = new SimpleIntegerProperty();
    private IntegerProperty probabilidadReproduccion = new SimpleIntegerProperty();


    public ParametrosIndividuoModelProperties(ParametrosIndividuo original){
        setOriginal(original);
    }

    public void commit(){
        valoresOriginales.setProbabilidadClonacion(turnosVidaRestantes.get());
        valoresOriginales.setProbabilidadReproduccion(probabilidadMuerte.get());
        valoresOriginales.setProbabilidadMuerte(probabilidadClonacion.get());
        valoresOriginales.setTurnosVidaRestantes(probabilidadReproduccion.get());
    }

    public void rollback(){
        turnosVidaRestantes.set(valoresOriginales.getTurnosVidaRestantes());
        probabilidadMuerte.set(valoresOriginales.getProbabilidadMuerte());
        probabilidadClonacion.set(valoresOriginales.getProbabilidadMuerte());
        probabilidadReproduccion.set(valoresOriginales.getProbabilidadReproduccion());
    }

    public ParametrosIndividuo getOriginal(){
        return valoresOriginales;
    }

    public void setOriginal(ParametrosIndividuo original){
        this.valoresOriginales = original;
        rollback();
    }

    public Property<Number> turnosVidaRestantesProperty(){return turnosVidaRestantes;}
    public Property<Number> probabilidadMuerteProperty(){return probabilidadMuerte;}
    public Property<Number> probabilidadClonacionProperty(){return probabilidadClonacion;}
    public Property<Number> probabilidadReproduccionProperty(){return probabilidadReproduccion;}
}
