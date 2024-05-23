package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Agua;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Comida;

import java.lang.reflect.Type;

public class gsonAdapterAgua implements JsonSerializer<Agua>, JsonDeserializer<Agua> {

    @Override
    public JsonElement serialize(Agua recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        //Guardo como ObjetoJson el recurso, pero le pongo un 'tipo' antes, que es la clase a la que pertenece
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Agua deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        Agua recurso= new Agua(coordenadaX, coordenadaY, tiempoAparicion);
        return recurso;
    }
}