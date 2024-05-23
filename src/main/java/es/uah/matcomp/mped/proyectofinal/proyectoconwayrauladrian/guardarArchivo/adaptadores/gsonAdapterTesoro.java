package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Comida;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Tesoro;

import java.lang.reflect.Type;

public class gsonAdapterTesoro implements JsonSerializer<Tesoro>, JsonDeserializer<Tesoro> {

    @Override
    public JsonElement serialize(Tesoro recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        recurso.addProperty("tipo", recursoDado.getClass().getSimpleName());
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Tesoro deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        Tesoro recurso= new Tesoro(coordenadaX, coordenadaY, tiempoAparicion);
        return recurso;
    }
}
