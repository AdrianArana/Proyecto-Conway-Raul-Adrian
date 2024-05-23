package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;
import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Comida;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Montaña;

import java.lang.reflect.Type;

public class gsonAdapterMontaña implements JsonSerializer<Montaña>, JsonDeserializer<Montaña> {

    @Override
    public JsonElement serialize(Montaña recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Montaña deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        Montaña recurso= new Montaña(coordenadaX, coordenadaY, tiempoAparicion);
        return recurso;
    }
}
