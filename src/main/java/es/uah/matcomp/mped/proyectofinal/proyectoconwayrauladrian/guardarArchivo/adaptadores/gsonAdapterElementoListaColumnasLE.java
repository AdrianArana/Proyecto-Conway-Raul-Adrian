package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoListaColumnasLE;

import java.lang.reflect.Type;

public class gsonAdapterElementoListaColumnasLE<T> implements JsonSerializer<ElementoListaColumnasLE<T>>, JsonDeserializer<ElementoListaColumnasLE<T>> {

    @Override
    public ElementoListaColumnasLE deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ElementoListaColumnasLE elementoListaColumnasLEDado, Type type, JsonSerializationContext contextoDeSerializacionElementoColumnasLE) {
        JsonObject elementoListaColumnasLE= new JsonObject();
        elementoListaColumnasLE.add("propiedades",contextoDeSerializacionElementoColumnasLE.serialize(elementoListaColumnasLEDado.getData()));
        return elementoListaColumnasLE;
    }
}
