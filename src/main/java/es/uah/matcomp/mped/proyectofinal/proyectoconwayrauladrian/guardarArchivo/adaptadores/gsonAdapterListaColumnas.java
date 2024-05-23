package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;

import java.lang.reflect.Type;

public class gsonAdapterListaColumnas implements JsonSerializer<ListaEnlazadaColumnas<Casilla>>, JsonDeserializer<ListaEnlazadaColumnas<Casilla>> {

    @Override
    public ListaEnlazadaColumnas<Casilla> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ListaEnlazadaColumnas<Casilla> listaEnlazadaColumnasDada, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject listaColumnasJson = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i=0; i<listaEnlazadaColumnasDada.getNumeroColumnas();i++) {
            Casilla casilla = listaEnlazadaColumnasDada.getElemento(i).getData();
            jsonArray.add(jsonSerializationContext.serialize(casilla).getAsJsonObject());
        }
        listaColumnasJson.addProperty("datos", jsonArray.toString());
        return listaColumnasJson;
    }
}
