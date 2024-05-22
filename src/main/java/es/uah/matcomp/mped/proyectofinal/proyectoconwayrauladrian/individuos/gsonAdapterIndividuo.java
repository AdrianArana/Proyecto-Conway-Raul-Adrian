package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;

import com.google.gson.*;

import java.lang.reflect.Type;

public class gsonAdapterIndividuo implements JsonSerializer<Individuo>, JsonDeserializer<Individuo> {
    @Override
    public JsonElement serialize(Individuo individuoDado, Type typeOfSrc, JsonSerializationContext contextoDeSerializacion) {
        JsonObject individuo = new JsonObject();
/*
        ListaSimple<Individuo> padres = individuoDado.getPadres();

        Para cuando tengamos los padres del individuo

        if (padres.getPrimero() != null) {
            JsonArray padresJson = new JsonArray(2);
            padresJson.add(serialize(padres.getPrimero().getData(), typeOfSrc, context));
            padresJson.add(serialize(padres.getElemento(1).getData(), typeOfSrc, context));
            individuo.add("padres", padresJson);
        }
*/


        //Primero el tipo (1,2 ó 3)
        //individuo.addProperty("tipo", individuoDado.getTipo());
        //Después sus propiedades, sus variables
        if (individuoDado.getId() != 0) {
            individuo.add("propiedades", contextoDeSerializacion.serialize(individuoDado));
        }
        return individuo;
    }

    @Override
    public Individuo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        /*log.trace(STR."Empezando deserialización de individuo \{json.getAsJsonObject().get("tipo").getAsString()} a Json");
        JsonObject jsonIndividuo = json.getAsJsonObject();

        String tipo = jsonIndividuo.get("tipo").getAsString();

        Class<?> individuoClase = switch (tipo) {
            case "individuoBasico" -> individuoBasico.class;
            case "individuoNormal" -> individuoNormal.class;
            case "individuoAvanzado" -> individuoAvanzado.class;
            default -> throw new tipoInvalidoException(individuo.class.getSimpleName(), tipo);
        };

        individuo individuo = context.deserialize(jsonIndividuo.get("propiedades").getAsJsonObject(), individuoClase);
        ListaSimple<individuo> lista = new ListaSimple<>(2);

        if (jsonIndividuo.has("padres")) {
            JsonArray padres = jsonIndividuo.get("padres").getAsJsonArray();
            for (int i = 0; i != 2; i++) {
                individuo padre = deserialize(padres.get(i).getAsJsonObject(), typeOfT, context);
                ElementoLS<individuo> elementoLS = new ElementoLS<>(padre);
                lista.add(elementoLS);
            }
            individuo.setPadres(lista);
        }
        return individuo;*/
        return null;
    }
}
