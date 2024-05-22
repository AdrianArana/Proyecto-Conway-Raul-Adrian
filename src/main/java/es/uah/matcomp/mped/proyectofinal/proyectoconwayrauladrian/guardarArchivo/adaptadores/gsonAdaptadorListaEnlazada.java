package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;

import java.lang.reflect.Type;

public class gsonAdaptadorListaEnlazada implements JsonSerializer<ListaEnlazada<Object>>,JsonDeserializer<ListaEnlazada<Object>> {

    @Override
    public ListaEnlazada<Object> deserialize (JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize (ListaEnlazada<Object> listaEnlazadaDada, Type type, JsonSerializationContext contextoDeSerializacionListaEnlazada){
        JsonObject listaJson = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        if (listaEnlazadaDada.getNumeroElementos() != 0) {
            for (int i = 0; i < listaEnlazadaDada.getNumeroElementos(); i++) {
                ElementoLE elemento = (ElementoLE) listaEnlazadaDada.getElemento(i);
                System.out.println("Elemento: " + elemento.getData().toString());
                jsonArray.add(contextoDeSerializacionListaEnlazada.serialize(elemento));
                System.out.println(jsonArray);
            }
            listaJson.add("elementos", jsonArray);
            return listaJson;
        } else {
            return null;
        }
    }
}
