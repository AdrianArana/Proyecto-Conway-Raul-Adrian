package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterRecurso implements JsonSerializer<Entorno>, JsonDeserializer<Entorno> {

    @Override
    public JsonElement serialize(Entorno recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        //Guardo como ObjetoJson el recurso, pero le pongo un 'tipo' antes, que es la clase a la que pertenece
        recurso.addProperty("tipo", recursoDado.getClass().getSimpleName());
        recurso.add("propiedades", contextoDeSerializacionRecurso.serialize(recursoDado));
        return recurso;
    }

    @Override
    public Entorno deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}

