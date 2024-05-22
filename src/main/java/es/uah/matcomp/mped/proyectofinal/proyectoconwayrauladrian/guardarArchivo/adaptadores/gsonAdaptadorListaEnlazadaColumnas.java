package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoCasillaLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;

import java.lang.reflect.Type;
public class gsonAdaptadorListaEnlazadaColumnas implements JsonSerializer<ListaEnlazadaColumnas<Casilla>>, JsonDeserializer<ListaEnlazadaColumnas<Casilla>> {

    @Override
    public ListaEnlazadaColumnas<Casilla> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ListaEnlazadaColumnas ListaEnlazadaColumnasDada, Type type, JsonSerializationContext contextoDeSerializacionListaEnlazadaFilas) {
        JsonObject listaJson = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < ListaEnlazadaColumnasDada.getNumeroColumnas(); i++) {
            ElementoCasillaLE casilla = ListaEnlazadaColumnasDada.getElemento(i);
            jsonArray.add(contextoDeSerializacionListaEnlazadaFilas.serialize(casilla).getAsJsonObject());
        }
        listaJson.addProperty("fila", jsonArray.toString());
        return listaJson;
    }
}