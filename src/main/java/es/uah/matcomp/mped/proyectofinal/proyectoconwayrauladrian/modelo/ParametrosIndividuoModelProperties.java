package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;

import com.google.gson.annotations.Expose;
import javafx.beans.property.*;

public class ParametrosIndividuoModelProperties {
    @Expose
    public ParametrosIndividuo valoresOriginales;
    private IntegerProperty turnosVidaRestantes = new SimpleIntegerProperty();
    private IntegerProperty probabilidadMuerte = new SimpleIntegerProperty();
    private IntegerProperty probabilidadClonacion = new SimpleIntegerProperty();
    private IntegerProperty probabilidadReproduccion = new SimpleIntegerProperty();


    public ParametrosIndividuoModelProperties(ParametrosIndividuo original){
        setOriginal(original);
    }

    public void commit(){
        valoresOriginales.setTurnosVidaRestantes(turnosVidaRestantes.get());
        valoresOriginales.setProbabilidadMuerte(probabilidadMuerte.get());
        valoresOriginales.setProbabilidadClonacion(probabilidadClonacion.get());
        valoresOriginales.setProbabilidadReproduccion(probabilidadReproduccion.get());

    }

    public void rollback(){
        turnosVidaRestantes.set(valoresOriginales.getTurnosVidaRestantes());
        probabilidadMuerte.set(valoresOriginales.getProbabilidadMuerte());
        probabilidadClonacion.set(valoresOriginales.getProbabilidadClonacion());
        probabilidadReproduccion.set(valoresOriginales.getProbabilidadReproduccion());
    }

    public void setOriginal(ParametrosIndividuo original){
        this.valoresOriginales = original;
        rollback();
    }

    public Property<Number> turnosVidaRestantesProperty(){return turnosVidaRestantes;}
    public Property<Number> probabilidadMuerteProperty(){return probabilidadMuerte;}
    public Property<Number> probabilidadClonacionProperty(){return probabilidadClonacion;}
    public Property<Number> probabilidadReproduccionProperty(){return probabilidadReproduccion;}

    public ParametrosIndividuo getOriginal() {
        return valoresOriginales;
    }
}
