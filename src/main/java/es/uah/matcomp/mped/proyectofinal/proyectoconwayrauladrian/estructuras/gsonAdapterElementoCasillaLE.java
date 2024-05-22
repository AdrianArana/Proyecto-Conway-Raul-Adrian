package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterElementoCasillaLE implements JsonSerializer<ElementoCasillaLE>, JsonDeserializer<ElementoCasillaLE> {
    @Override
    public ElementoCasillaLE deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ElementoCasillaLE casillaDada, Type type, JsonSerializationContext contextoDeSerializacionCasilla) {
        JsonObject casilla = new JsonObject();
        casilla.add("propiedades", contextoDeSerializacionCasilla.serialize(casillaDada.getData()));
        return casilla;
    }
}
