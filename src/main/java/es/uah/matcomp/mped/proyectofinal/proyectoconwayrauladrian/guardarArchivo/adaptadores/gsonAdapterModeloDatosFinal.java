package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillasModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntornoModelProperties;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuoModelProperties;

import java.lang.reflect.Type;

public class gsonAdapterModeloDatosFinal implements JsonSerializer<modeloDatosFinal>, JsonDeserializer<modeloDatosFinal> {
    @Override
    public modeloDatosFinal deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject modeloJson = jsonElement.getAsJsonObject();

        ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = jsonDeserializationContext.deserialize(modeloJson.get("tablero"), ListaEnlazadaFilas.class);

        System.out.println("modeloJson: " + tablero.getNumeroFilas()+"casillas"+x);
        modeloDatosFinal modeloDeserializado = new modeloDatosFinal(tablero, parametrosIndividuoModelProperties, parametrosEntornoModelProperties, parametrosCasillasModelProperties);

        return modeloDeserializado;
    }

    @Override
    public JsonElement serialize(modeloDatosFinal modeloDatosFinal, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject modeloDatosFinalJson = new JsonObject();

        modeloDatosFinalJson.add("tablero", jsonSerializationContext.serialize(modeloDatosFinal.getTablero()));
        modeloDatosFinalJson.add("parametrosIndividuos", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosIndividuos()));
        modeloDatosFinalJson.add("parametrosEntorno", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosEntorno()));
        modeloDatosFinalJson.add("parametrosCasillas", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosCasillas()));

        return modeloDatosFinalJson;
    }
}
