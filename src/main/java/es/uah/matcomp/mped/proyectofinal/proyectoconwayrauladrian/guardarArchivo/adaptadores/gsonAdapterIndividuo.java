package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import java.lang.reflect.Type;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.FuncionesDeGuardado.log;


public class gsonAdapterIndividuo implements JsonSerializer<Individuo>, JsonDeserializer<Individuo> {
    @Override
    public JsonElement serialize(Individuo individuoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacion) {
        JsonObject individuo = new JsonObject();
        individuo.addProperty("tipo", individuoDado.getTipo());
        individuo.addProperty("id", individuoDado.getId());
        individuo.addProperty("coordenadaX",individuoDado.getCoordenadaX());
        individuo.addProperty("coordenadaY", individuoDado.getCoordenadaY());
        individuo.addProperty("turnoGeneracion", individuoDado.getTurnoGeneracion());
        individuo.addProperty("probabilidadMuerte",individuoDado.getProbabilidadMuerte());
        individuo.addProperty("probabilidadClonacion", individuoDado.getProbabilidadClonacion());
        individuo.addProperty("probabilidadReproduccion", individuoDado.getProbabilidadReproduccion());
        individuo.addProperty("turnosVidaRestantes", individuoDado.getTurnosVidaRestantes());
        return individuo;
    }

    @Override
    public Individuo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonIndividuo = json.getAsJsonObject();

        int tipo = jsonIndividuo.get("tipo").getAsInt();
        int id = jsonIndividuo.get("id").getAsInt();
        int coordenadaX = jsonIndividuo.get("coordenadaX").getAsInt();
        int coordenadaY = jsonIndividuo.get("coordenadaY").getAsInt();
        int turnoGeneracion = jsonIndividuo.get("turnoGeneracion").getAsInt();
        int probabilidadMuerte = jsonIndividuo.get("probabilidadMuerte").getAsInt();
        int probabilidadClonacion = jsonIndividuo.get("probabilidadClonacion").getAsInt();
        int probabilidadReproduccion = jsonIndividuo.get("probabilidadReproduccion").getAsInt();
        int turnosVidaRestantes = jsonIndividuo.get("turnosVidaRestantes").getAsInt();

        Individuo individuo=new Individuo(coordenadaX,coordenadaY,id,tipo,turnosVidaRestantes,turnoGeneracion,probabilidadMuerte,probabilidadClonacion,probabilidadReproduccion);
        log.info("Individuo deserializado correctamente");
        return individuo;
    }
}
