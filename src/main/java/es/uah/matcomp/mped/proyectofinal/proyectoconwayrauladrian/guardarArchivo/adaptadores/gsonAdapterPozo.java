package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;
import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Comida;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Pozo;

import java.lang.reflect.Type;

public class gsonAdapterPozo implements JsonSerializer<Pozo>, JsonDeserializer<Pozo> {

    @Override
    public JsonElement serialize(Pozo recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Pozo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        Pozo recurso= new Pozo(coordenadaX, coordenadaY, tiempoAparicion);
        return recurso;
    }
}
