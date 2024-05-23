package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;

import java.lang.reflect.Type;

public class gsonAdapterElementoLE<T> implements JsonSerializer<ElementoLE<T>>, JsonDeserializer<ElementoLE> {

    @Override
    public ElementoLE deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ElementoLE elementoLEDado, Type type, JsonSerializationContext contextoDeSerializacionElementoLE) {
        JsonObject elementoLE = new JsonObject();
        elementoLE.add("propiedades", contextoDeSerializacionElementoLE.serialize(elementoLEDado.getData()));
        return elementoLE;
    }
}
