package es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.errorprone.annotations.Modifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.entorno.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.*;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos.Individuo;
import es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.guardarArchivo.adaptadores.gsonAdapterIndividuo;
import org.apache.commons.logging.Log;

import static es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.bucle.FuncionesBucle.log;

public class FuncionesDeGuardado {
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
            //this.setGuardado(true);
            gson.toJson(modeloDatosFinal, writer);
        } catch (IOException e) {
            log.error("La ruta para guardar el archivo no existe");
        }
    }

    public static modeloDatosFinal cargar (String nombreArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
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
                .setPrettyPrinting()
                .create();
        try (FileReader reader = new FileReader(nombreArchivo)) {
            return gson.fromJson(reader, modeloDatosFinal.class);
        } catch (IOException e) {
            System.out.println("La ruta al archivo especificada no es correcta");
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
