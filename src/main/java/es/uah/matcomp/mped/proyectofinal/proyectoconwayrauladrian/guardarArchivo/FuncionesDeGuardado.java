package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.ListaEnlazada;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FuncionesDeGuardado {
    public static final Logger log = LogManager.getLogger();

    public static <T> void guardar(String nombreArchivo, T modeloDatosFinal) {
        log.info("Guardando archivo " + nombreArchivo);
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .registerTypeAdapter(Entorno.class, new gsonAdapterRecurso())
                .registerTypeAdapter(Agua.class, new gsonAdapterAgua())
                .registerTypeAdapter(Biblioteca.class, new gsonAdapterBiblioteca())
                .registerTypeAdapter(Montaña.class, new gsonAdapterMontaña())
                .registerTypeAdapter(Pozo.class, new gsonAdapterPozo())
                .registerTypeAdapter(Tesoro.class, new gsonAdapterTesoro())
                .registerTypeAdapter(Comida.class, new gsonAdapterComida())
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(modeloDatosFinal.class, new gsonAdapterModeloDatosFinal())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(modeloDatosFinal, writer);
        } catch (IOException e) {
            log.error("La ruta para guardar el archivo no existe");
        }
    }

    public static modeloDatosFinal cargar(String nombreArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(Entorno.class, new gsonAdapterRecurso())
                .registerTypeAdapter(Agua.class, new gsonAdapterAgua())
                .registerTypeAdapter(Biblioteca.class, new gsonAdapterBiblioteca())
                .registerTypeAdapter(Montaña.class, new gsonAdapterMontaña())
                .registerTypeAdapter(Pozo.class, new gsonAdapterPozo())
                .registerTypeAdapter(Tesoro.class, new gsonAdapterTesoro())
                .registerTypeAdapter(Comida.class, new gsonAdapterComida())
                .registerTypeAdapter(modeloDatosFinal.class, new gsonAdapterModeloDatosFinal())
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        try (FileReader reader = new FileReader(nombreArchivo)) {
            return gson.fromJson(reader, modeloDatosFinal.class);
        } catch (IOException e) {
            System.out.println("La ruta al archivo especificada no es correcta");
            return null;
        }
    }


    public static void guardarPartidasGuardadas(String nombreArchivo, ListaEnlazada<String> partidasGuardadas) {
        log.info("Guardando archivo " + nombreArchivo);
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .registerTypeAdapter(modeloDatosFinal.class, new gsonAdapterModeloDatosFinal())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(partidasGuardadas, writer);
        } catch (IOException e) {
            log.error("La ruta para guardar el archivo no existe");
        }
    }

    public static ListaEnlazada<String> cargarPartidasGuardadas() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        try (FileReader reader = new FileReader("partidasGuardadas.json")) {
            return gson.fromJson(reader, ListaEnlazada.class);
        } catch (IOException e) {
            System.out.println("La ruta al archivo especificada no es correcta");
            return null;
        }
    }
}
