package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;

import java.lang.reflect.Type;

public class gsonAdapterCasilla implements JsonSerializer<Casilla>, JsonDeserializer<Casilla> {
    @Override
    public Casilla deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Casilla casillaDada, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject casilla = new JsonObject();
        casilla.addProperty("coordenadaX", casillaDada.getCoordenadaX());
        casilla.addProperty("coordenadaY", casillaDada.getCoordenadaY());
        casilla.add("individuos", jsonSerializationContext.serialize(casillaDada.getIndividuos()));
        casilla.add("recursos", jsonSerializationContext.serialize(casillaDada.getRecursos()));
        return casilla;
    }
}
