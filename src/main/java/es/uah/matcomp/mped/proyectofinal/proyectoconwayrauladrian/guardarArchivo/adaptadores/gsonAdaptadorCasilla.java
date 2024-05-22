package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;

import java.lang.reflect.Type;

public class gsonAdaptadorCasilla implements JsonSerializer<Casilla>, JsonDeserializer<Casilla> {
    @Override
    public Casilla deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(Casilla casillaDada, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject casillaJson = new JsonObject();
        casillaJson.addProperty("coordenadaX", casillaDada.getCoordenadaX());
        casillaJson.addProperty("coordenadaY", casillaDada.getCoordenadaY());
        if (!casillaDada.getIndividuos().isVacia()) {
            casillaJson.add("individuos", jsonSerializationContext.serialize(casillaDada.getIndividuos()));
        } else {
            casillaJson.addProperty("individuos", "none");
        } if (!casillaDada.getRecursos().isVacia()){
            casillaJson.add("recursos", jsonSerializationContext.serialize(casillaDada.getRecursos()));
        } else{
            casillaJson.addProperty("recursos", "none");
        }
        return casillaJson;
    }
}
