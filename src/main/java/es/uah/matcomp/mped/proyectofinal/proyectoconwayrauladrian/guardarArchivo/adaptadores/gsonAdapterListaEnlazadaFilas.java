package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoListaColumnasLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;

import java.lang.reflect.Type;


public class gsonAdapterListaEnlazadaFilas<T> implements JsonSerializer<ListaEnlazadaFilas<T>>, JsonDeserializer<ListaEnlazadaFilas<T>> {

    @Override
    public ListaEnlazadaFilas deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ListaEnlazadaFilas listaEnlazadaFilasDada, Type type, JsonSerializationContext contextoDeSerializacionListaEnlazadaFilas) {
        JsonObject listaJson = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i=0; i<listaEnlazadaFilasDada.getNumeroFilas();i++) {
            ElementoListaColumnasLE<ListaEnlazadaColumnas<Casilla>> elemento = listaEnlazadaFilasDada.getElemento(i);
            jsonArray.add(contextoDeSerializacionListaEnlazadaFilas.serialize(elemento).getAsJsonObject());
        }
        listaJson.addProperty("datos", jsonArray.toString());
        return listaJson;
    }
}
