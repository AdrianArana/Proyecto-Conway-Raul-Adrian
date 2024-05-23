package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.Casilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ElementoListaColumnasLE;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.modeloDatosFinal;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.NombreGuardado;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosCasillas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosEntorno;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo.ParametrosIndividuo;

import java.lang.reflect.Type;

public class gsonAdapterModeloDatosFinal implements JsonSerializer<modeloDatosFinal>, JsonDeserializer<modeloDatosFinal> {
    @Override
    public modeloDatosFinal deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject modeloJson = jsonElement.getAsJsonObject();

        Type tableroType = new TypeToken<ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>>>() {}.getType();
        ListaEnlazadaFilas<ListaEnlazadaColumnas<Casilla>> tablero = jsonDeserializationContext.deserialize(modeloJson.get("tablero"), tableroType);

        for (int i = 0; i < tablero.getNumeroFilas(); i++) {
            ElementoListaColumnasLE<ListaEnlazadaColumnas<Casilla>> filaActualLE = tablero.getElemento(i);
            ListaEnlazadaColumnas<Casilla> filaActual = filaActualLE.getData();
        }

        ParametrosIndividuo parametrosIndividuo = jsonDeserializationContext.deserialize(modeloJson.get("parametrosIndividuos"), ParametrosIndividuo.class);
        ParametrosEntorno parametrosEntorno = jsonDeserializationContext.deserialize(modeloJson.get("parametrosEntorno"), ParametrosEntorno.class);
        ParametrosCasillas parametrosCasillas = jsonDeserializationContext.deserialize(modeloJson.get("parametrosCasillas"), ParametrosCasillas.class);

        String nombreGuardado= jsonDeserializationContext.deserialize(modeloJson.get("nombreGuardado"), String.class);
        modeloDatosFinal modeloDeserializado = new modeloDatosFinal(nombreGuardado,tablero, parametrosIndividuo, parametrosEntorno, parametrosCasillas);

        return modeloDeserializado;
    }

    @Override
    public JsonElement serialize(modeloDatosFinal modeloDatosFinal, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject modeloDatosFinalJson = new JsonObject();
        System.out.println("Guardando Partida a nombre de: "+modeloDatosFinal.getNombreGuardadoString());
        modeloDatosFinalJson.add("nombreGuardado",jsonSerializationContext.serialize(modeloDatosFinal.getNombreGuardadoString()));
        modeloDatosFinalJson.add("tablero", jsonSerializationContext.serialize(modeloDatosFinal.getTablero()));
        modeloDatosFinalJson.add("parametrosIndividuos", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosIndividuos()));
        modeloDatosFinalJson.add("parametrosEntorno", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosEntorno()));
        modeloDatosFinalJson.add("parametrosCasillas", jsonSerializationContext.serialize(modeloDatosFinal.getParametrosGuardadosCasillas()));
        return modeloDatosFinalJson;
    }
    }