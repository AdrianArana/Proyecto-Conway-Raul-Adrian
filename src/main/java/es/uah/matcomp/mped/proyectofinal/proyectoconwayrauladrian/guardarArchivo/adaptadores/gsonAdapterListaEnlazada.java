package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;

import java.lang.reflect.Type;

//2
public class gsonAdapterListaEnlazada implements JsonSerializer<ListaEnlazada>, JsonDeserializer<ListaEnlazada> {

    @Override
    public ListaEnlazada deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(ListaEnlazada listaEnlazadaDada, Type type, JsonSerializationContext contextoDeSerializacionListaEnlazada) {
        JsonObject listaJson = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        if (listaEnlazadaDada.getNumeroElementos() != 0) {
            for (int i=0; i<listaEnlazadaDada.getNumeroElementos();i++) {
                ElementoLE elemento = listaEnlazadaDada.getElemento(i);
                jsonArray.add(contextoDeSerializacionListaEnlazada.serialize(elemento).getAsJsonObject());
            }
            listaJson.addProperty("datos", jsonArray.getAsString());
            return listaJson;
        }else{
            return null;
        }



    }
}
