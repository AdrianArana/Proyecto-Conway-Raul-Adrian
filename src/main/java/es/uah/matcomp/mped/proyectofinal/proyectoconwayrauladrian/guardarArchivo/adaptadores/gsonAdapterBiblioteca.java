package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Biblioteca;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.Comida;

import java.lang.reflect.Type;

public class gsonAdapterBiblioteca implements JsonSerializer<Biblioteca>, JsonDeserializer<Biblioteca> {

    @Override
    public JsonElement serialize(Biblioteca recursoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacionRecurso) {
        JsonObject recurso = new JsonObject();
        //Guardo como ObjetoJson el recurso, pero le pongo un 'tipo' antes, que es la clase a la que pertenece
        recurso.addProperty("tipo", recursoDado.getClass().getSimpleName());
        recurso.addProperty("coordenadaX", recursoDado.getCoordenadaX());
        recurso.addProperty("coordenadaY", recursoDado.getCoordenadaY());
        recurso.addProperty("tiempoAparicion", recursoDado.getTiempoAparicion());

        return recurso;
    }

    @Override
    public Biblioteca deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int coordenadaX = json.getAsJsonObject().get("coordenadaX").getAsInt();
        int coordenadaY = json.getAsJsonObject().get("coordenadaY").getAsInt();
        int tiempoAparicion = json.getAsJsonObject().get("tiempoAparicion").getAsInt();
        Biblioteca recurso= new Biblioteca(coordenadaX, coordenadaY, tiempoAparicion);
        return recurso;
    }
}
