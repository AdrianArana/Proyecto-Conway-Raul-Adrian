package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.gsonAdaptadorCasilla;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.gsonAdaptadorListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.gsonAdaptadorListaEnlazadaColumnas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.gsonAdaptadorListaEnlazadaFilas;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.gsonAdapterIndividuo;

public class FuncionesDeGuardado {
    public static <T> void guardar(String nombreArchivo, T tablero) {
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .registerTypeAdapter(ListaEnlazada.class, new gsonAdaptadorListaEnlazada())
                .registerTypeAdapter(ListaEnlazadaColumnas.class, new gsonAdaptadorListaEnlazadaColumnas())
                .registerTypeAdapter(ListaEnlazadaFilas.class, new gsonAdaptadorListaEnlazadaFilas())
                .registerTypeAdapter(Casilla.class, new gsonAdaptadorCasilla())
                .registerTypeAdapter(Entorno.class, new gsonAdapterRecurso())
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .create();
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            //this.setGuardado(true);
            gson.toJson(tablero, writer);
        } catch (IOException e) {
            //log.error("La ruta para guardar el archivo no existe");
        }
    }

    /*public static DataModel cargar (String nombreArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(recurso.class, new gsonAdapterRecurso())
                .registerTypeAdapter(Cola.class, new gsonAdapterCola())
                .registerTypeAdapter(ElementoLS[].class, new gsonAdapterListaSimple())
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .setPrettyPrinting()
                .create();
        try (FileReader reader = new FileReader(STR."archivosDePartida/\{nombreArchivo}")) {
            DataModel model = gson.fromJson(reader, DataModel.class);
            return model;
        } catch (IOException e) {
            log.error("La ruta para cargar el archivo no existe");
            System.out.println("La ruta al archivo especificado no existe");
            return null;
        }
    }


    /*public static <T> void guardarObjetoEnArchivo(String rutaArchivo, T objeto) {
        GsonBuilder gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation(FuncionesDeGuardado);
        try (FileWriter writer = new FileWriter(rutaArchivo)) { //Constructor para la variable writer, de tipo FileWriter
            gson.toJson(objeto, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para cargar un objeto desde un archivo JSON
    public static <T> T cargarObjetoDesdeArchivo(String rutaArchivo, Class<T> clase) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, clase);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
