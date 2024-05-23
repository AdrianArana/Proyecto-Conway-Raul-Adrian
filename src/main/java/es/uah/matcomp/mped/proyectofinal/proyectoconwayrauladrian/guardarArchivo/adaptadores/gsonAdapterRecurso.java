package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;

import java.lang.reflect.Type;
import java.util.Objects;

public class gsonAdapterRecurso implements JsonSerializer<Entorno>, JsonDeserializer<Entorno> {

    @Override
    public JsonElement serialize(Entorno recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        recurso.addProperty("tipo", recursoDado.getClass().getSimpleName());
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Entorno deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String claseRecurso = json.getAsJsonObject().get("tipo").getAsString();

        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        if (Objects.equals(claseRecurso, "Montaña")) {
            Montaña recurso = new Montaña(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else if (Objects.equals(claseRecurso, "Comida")) {
            Comida recurso = new Comida(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else if (Objects.equals(claseRecurso, "Biblioteca")) {
            Biblioteca recurso = new Biblioteca(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else if (Objects.equals(claseRecurso, "Agua")) {
            Agua recurso = new Agua(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else if (Objects.equals(claseRecurso, "Pozo")) {
            Pozo recurso = new Pozo(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else if (Objects.equals(claseRecurso, "Tesoro")) {
            Tesoro recurso = new Tesoro(coordenadaX, coordenadaY, tiempoAparicion);
            return recurso;
        } else {
            return new Agua(coordenadaX, coordenadaY, tiempoAparicion);
        }
    }
}

