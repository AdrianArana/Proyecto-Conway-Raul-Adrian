package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoListaColumnasLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;

import java.lang.reflect.Type;


public class gsonAdapterListaEnlazadaFilas<T> implements JsonDeserializer<ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>> {

    @Override
    public ListaEnlazadaFilas deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> listaEnlazadaFilas = new ListaEnlazadaFilas<>();

        for (JsonElement element : jsonArray) {
            ListaEnlazadaColumnas<Casilla> listaEnlazadaColumnas = context.deserialize(element, new TypeToken<ListaEnlazadaColumnas<Casilla>>() {
            }.getType());
            listaEnlazadaFilas.add(new ElementoListaColumnasLE<>(listaEnlazadaColumnas));
        }

        return listaEnlazadaFilas;
    }

}
